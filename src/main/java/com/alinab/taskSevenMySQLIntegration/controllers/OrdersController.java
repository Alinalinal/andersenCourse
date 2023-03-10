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

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        Order order = ordersService.getByIdWithProducts(id);
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        return "orders/show";
    }

    @GetMapping("/create/{userId}")
    public String askNewOrder(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("order", new Order());

        return "orders/askNew";
    }

    @PatchMapping("/{id}")
    public String editOrder(@PathVariable int id, Model model) {
        Order order = ordersService.getByIdWithProducts(id);
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        model.addAttribute("allProducts", productsService.findAll());

        return "orders/new";
    }

    @PostMapping("/create/users/{userId}")
    public String fillInOrder(Model model, @PathVariable("userId") int userId, @ModelAttribute("order") Order order) {
        order.setOrderOwner(usersService.findOne(userId));
        ordersService.save(order);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", ordersService.getByIdWithProducts(order.getId()));

        return "orders/new";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id, Model model) {
        int orderOwnerId = ordersService.deleteOrder(id);
        model.addAttribute("user", usersService.findOne(orderOwnerId));
        return "users/show";
    }

    @PostMapping("/{id}/confirm")
    public String confirmOrder(@PathVariable int id, Model model) {
        Order order = ordersService.confirmOrder(id);

        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));
        return "orders/show";
    }

    @PostMapping("/{id}/product")
    public String addToOrder(Model model, @PathVariable("id") int id, @RequestParam("productId") int productId) {
        Product product = productsService.getById(productId);

        if (product != null) {
            ordersService.addProductToOrder(id, product);
        }
        Order order = ordersService.getByIdWithProducts(id);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));

        return "orders/new";
    }

    @DeleteMapping("/{id}/product")
    public String deleteFromOrder(Model model, @PathVariable("id") int id, @RequestParam("productId") int productId) {
        Product product = productsService.getById(productId);

        if (product != null) {
            ordersService.removeProductFromOrder(id, product);
        }
        Order order = ordersService.getByIdWithProducts(id);
        model.addAttribute("allProducts", productsService.findAll());
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", ordersService.countTotalPrice(order));

        return "orders/new";
    }
}
