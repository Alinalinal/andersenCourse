package com.alinab.taskSevenMySQLIntegration.service;

import com.alinab.taskSevenMySQLIntegration.models.Order;
import com.alinab.taskSevenMySQLIntegration.models.Product;
import com.alinab.taskSevenMySQLIntegration.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void save(Order order) {
        ordersRepository.save(order);
    }

    public Order getByIdWithProducts(int id) {
        Optional<Order> order = ordersRepository.findByIdWithProducts(id);

        return order.orElse(null);
    }

    @Transactional
    public void addProductToOrder(int orderId, Product product) {
        Order order = getByIdWithProducts(orderId);
        order.getProducts().add(product);
        ordersRepository.save(order);
    }

    @Transactional
    public void removeProductFromOrder(int orderId, Product product) {
        Order order = getByIdWithProducts(orderId);
        order.getProducts().remove(product);
        ordersRepository.save(order);
    }

    @Transactional
    public int deleteOrder(int orderId) {
        Order order = ordersRepository.findByIdWithProducts(orderId).
                orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + orderId));
        int userOwnerId = order.getOrderOwner().getId();
        ordersRepository.delete(order);

        return userOwnerId;
    }

    @Transactional
    public Order confirmOrder(int orderId) {
        Order order = ordersRepository.findByIdWithProducts(orderId).
                orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + orderId));
        order.setConfirmed(true);
        ordersRepository.save(order);

        return order;
    }

    public BigDecimal countTotalPrice(Order order) {
        List<Product> products = order.getProducts();

        return products.stream().map(Product::getSellingPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
