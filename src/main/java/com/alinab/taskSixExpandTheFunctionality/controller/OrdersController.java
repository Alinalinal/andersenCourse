package com.alinab.taskSixExpandTheFunctionality.controller;

import com.alinab.taskSixExpandTheFunctionality.dao.orderDAO.OrderDAO;
import com.alinab.taskSixExpandTheFunctionality.dao.orderDAO.OrderDAOImpl;
import com.alinab.taskSixExpandTheFunctionality.model.order.Order;
import com.alinab.taskSixExpandTheFunctionality.model.product.Product;
import com.alinab.taskSixExpandTheFunctionality.storage.Warehouse;
import com.alinab.taskSixExpandTheFunctionality.storage.WarehouseImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrdersController {

    int orderUuid = 12345;
    Order order = new Order(orderUuid);
    @Getter
    OrderDAO orderDAO = new OrderDAOImpl(order);
    Warehouse warehouse = new WarehouseImpl();

    public void showMainMenu() {
        System.out.println("Select an action from the list below:");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("If you want to ...");
        System.out.println("    ... see a list of available products PRESS 0");
        System.out.println("    ... add a product to the cart PRESS 1");
        System.out.println("    ... open a cart PRESS 2");
        System.out.println("    ... remove a product from your shopping cart PRESS 3");
        System.out.println("    ... clear your cart PRESS 4");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Note: If you want to finish your shopping type \"exit\" or \"finish\"");
    }

    public void showAllProducts() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("    The list of available products:");
        System.out.println("       id   name               price                quantity");
        System.out.println("    ----------------------------------------------------------");
        Map<Product, Integer> products = warehouse.getAllProducts();

        for (Map.Entry<Product, Integer> pair : products.entrySet()) {
            System.out.println("        " + pair.getKey() + "            " + pair.getValue());
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showWrongActionText(String mistake) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("    You have selected a non-existent " + mistake + " OR");
        System.out.println("    ... we have not enough of this product in stock,");
        System.out.println("    ... please try less or another one! :)");
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showAddProcess(Scanner scanner) {
        int uuid;
        int quantity;

        while (true) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("    In order to add a product to the cart, enter its ID number");
            System.out.println("        OR");
            System.out.println("    PRESS 0 if you have finished...");
            System.out.println("-----------------------------------------------------------------------");
            try {
                uuid = Integer.parseInt(scanner.next());
                if (uuid == 0) break;
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("    Now enter quantity of product...");
                System.out.println("-----------------------------------------------------------------------");

                quantity = Integer.parseInt(scanner.next());

                if (!orderDAO.addProduct(uuid, quantity)) {
                    showWrongActionText("product number");
                    break;
                } else {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("Great choice! The product has been added to the cart!");
                    System.out.println("-----------------------------------------------------------------------");
                    showBucket();
                }
            } catch (NumberFormatException e) {
                showWrongActionText("product number");
            }
        }
        showMainMenu();
    }

    public void showBucket() {
        System.out.println("-----------------------------------------------------------------------");
        Map<Product, Integer> products = orderDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("    Your cart is empty now!");
        } else {
            System.out.println("    In your cart now:");

            for (Map.Entry<Product, Integer> pair : products.entrySet()) {
                System.out.println("       " + pair.getKey() + "     Quantity: " + pair.getValue());
            }

            System.out.println("    Total quantity: " + orderDAO.bucketSize());
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showDeleteProcess(Scanner scanner) {
        int uuid;
        int quantity;

        while (orderDAO.bucketSize() > 0) {
            showBucket();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("    In order to remove a product from the cart, enter its ID number");
            System.out.println("        OR");
            System.out.println("    PRESS 0 if you have finished...");
            System.out.println("-----------------------------------------------------------------------");

            try {
                uuid = Integer.parseInt(scanner.next());
                if (uuid == 0) break;
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("    Now enter quantity of product...");
                System.out.println("-----------------------------------------------------------------------");

                quantity = Integer.parseInt(scanner.next());

                if (!orderDAO.deleteProduct(uuid, quantity)) {
                    showWrongActionText("product number");
                    break;
                } else {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("The product has been removed from the cart!");
                    System.out.println("-----------------------------------------------------------------------");
                }
            } catch (NumberFormatException e) {
                showWrongActionText("product number");
            }
        }
        showMainMenu();
    }

    public void clearBucket() {
        orderDAO.clear();
        showBucket();
    }

    public void saveOrder() {
        orderDAO.saveOrder();
    }

    public void uploadOrder() {
        orderDAO.uploadOrder();
    }
}
