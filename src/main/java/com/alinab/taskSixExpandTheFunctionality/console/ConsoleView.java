package com.alinab.taskSixExpandTheFunctionality.console;

import com.alinab.taskSixExpandTheFunctionality.controller.OrdersController;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Log4j
public class ConsoleView {

    static final String SHOW_PRODUCTS = "0";
    static final String ADD_TO_BUCKET = "1";
    static final String SHOW_BUCKET = "2";
    static final String DELETE_FROM_BUCKET = "3";
    static final String CLEAR_BUCKET = "4";
    static final String STOP_1 = "exit";
    static final String STOP_2 = "finish";

    public static void run() {
        log.info("App started execution");
        OrdersController orderController = new OrdersController();
        orderController.uploadOrder();

        try (Scanner reader = new Scanner(System.in)) {
            orderController.showMainMenu();

            String action;

            while (true) {
                action = reader.next().trim();

                switch (action) {
                    case SHOW_PRODUCTS:
                        orderController.showAllProducts();
                        break;
                    case ADD_TO_BUCKET:
                        orderController.showAddProcess(reader);
                        break;
                    case SHOW_BUCKET:
                        orderController.showBucket();
                        break;
                    case DELETE_FROM_BUCKET:
                        orderController.showDeleteProcess(reader);
                        break;
                    case CLEAR_BUCKET:
                        orderController.clearBucket();
                        break;
                    case STOP_1:
                    case STOP_2:
                        orderController.saveOrder();
                        log.info("App finished execution");
                        System.exit(0);
                        break;
                    default:
                        orderController.showWrongActionText("action");
                }
            }
        }
    }
}
