package com.alinab.taskSevenMySQLIntegration.controllers;

import com.alinab.taskSevenMySQLIntegration.models.Order;
import com.alinab.taskSevenMySQLIntegration.models.Product;
import com.alinab.taskSevenMySQLIntegration.service.OrdersService;
import com.alinab.taskSevenMySQLIntegration.service.ProductsService;
import com.alinab.taskSevenMySQLIntegration.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final ProductsService productsService;
    private final UsersService usersService;

    @Autowired
    public OrdersController(OrdersService ordersService, ProductsService productsService, UsersService usersService) {
        this.ordersService = ordersService;
        this.productsService = productsService;
        this.usersService = usersService;
    }

    @GetMapping("/showOrder/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        Order order = ordersService.getByIdWithProducts(id);
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        return "orders/show";
    }

    @GetMapping("/editOrder/{orderId}")
    public String showEditOrderForm(@PathVariable int orderId, Model model) {
        Order order = ordersService.getByIdWithProducts(orderId);
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        model.addAttribute("allProducts", productsService.findAll());

        return "orders/new";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", usersService.findOne(id));
        return "users/show";
    }

    @GetMapping("/new/{userId}")
    public String askNewOrder(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("order", new Order());

        return "orders/askNew";
    }

    @PostMapping("/create/{userId}")
    public String fillInOrder(Model model, @PathVariable("userId") int userId, @ModelAttribute("order") Order order) {
        order.setOrderOwner(usersService.findOne(userId));
        ordersService.save(order);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", ordersService.getByIdWithProducts(order.getId()));

        return "orders/new";
    }

    @PostMapping("/addProduct/{orderId}")
    public String addToOrder(Model model, @PathVariable("orderId") int orderId, @RequestParam("productId") int productId) {
        Product product = productsService.getById(productId);

        if (product != null) {
            ordersService.addProductToOrder(orderId, product);
        }
        Order order = ordersService.getByIdWithProducts(orderId);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));

        return "orders/new";
    }

    @DeleteMapping("/deleteProduct/{orderId}")
    public String deleteFromOrder(Model model, @PathVariable("orderId") int orderId, @RequestParam("productId") int productId) {
        Product product = productsService.getById(productId);

        if (product != null) {
            ordersService.removeProductFromOrder(orderId, product);
        }
        Order order = ordersService.getByIdWithProducts(orderId);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));

        return "orders/new";
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable int orderId, Model model) {
        int orderOwnerId = ordersService.deleteOrder(orderId);
        model.addAttribute("user", usersService.findOne(orderOwnerId));
        return "users/show";
    }

    @PostMapping("/confirmOrder/{orderId}")
    public String confirmOrder(@PathVariable int orderId, Model model) {
        Order order = ordersService.confirmOrder(orderId);

        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        return "orders/show";
    }
}
