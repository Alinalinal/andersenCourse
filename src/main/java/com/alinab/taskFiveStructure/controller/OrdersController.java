package com.alinab.taskFiveStructure.controller;

import com.alinab.taskFiveStructure.dao.orderDAO.OrderDAO;
import com.alinab.taskFiveStructure.dao.orderDAO.OrderListDAO;
import com.alinab.taskFiveStructure.dao.productDAO.ProductListDAO;
import com.alinab.taskFiveStructure.model.product.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Scanner;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrdersController {

    OrderDAO orderDAO = new OrderListDAO();
    ProductListDAO productListDAO = new ProductListDAO();

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
        for (Product product : productListDAO.getAllProducts()) {
            System.out.println("        " + product);
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showWrongActionText(String mistake) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("    You have selected a non-existent " + mistake + ", please try again :)");
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showAddProcess(Scanner scanner) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("    In order to add a product to the cart, enter its ID number");
        System.out.println("        OR");
        System.out.println("    PRESS 0 if you have finished...");
        System.out.println("-----------------------------------------------------------------------");
        int uuid;

        while (true) {
            try {
                uuid = Integer.parseInt(scanner.next());
                if (uuid == 0) break;
                if (!orderDAO.addProduct(uuid)) {
                    showWrongActionText("product number");
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
        List<Product> products = orderDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("    Your cart is empty now!");
        } else {
            System.out.println("    In your cart now:");
            for (Product product : products) {
                System.out.println("       " + product);
            }
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void showDeleteProcess(Scanner scanner) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("    In order to remove a product from the cart, enter its ID number");
        System.out.println("        OR");
        System.out.println("    PRESS 0 if you have finished...");
        System.out.println("-----------------------------------------------------------------------");
        showBucket();
        int uuid;

        while (orderDAO.bucketSize() > 0) {
            try {
                uuid = Integer.parseInt(scanner.next());
                if (uuid == 0) break;
                if (!orderDAO.deleteProduct(uuid)) {
                    showWrongActionText("product number");
                } else {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("The product has been removed from the cart!");
                    System.out.println("-----------------------------------------------------------------------");
                    showBucket();
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
}
