package com.alinab.taskSixExpandTheFunctionality.storage;

import com.alinab.taskSixExpandTheFunctionality.model.product.Food;
import com.alinab.taskSixExpandTheFunctionality.model.product.NotFood;
import com.alinab.taskSixExpandTheFunctionality.model.product.Product;
import com.alinab.taskSixExpandTheFunctionality.model.product.ShelfLife;
import com.alinab.taskSixExpandTheFunctionality.model.product.currency.CurrencyCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j
public class WarehouseImpl implements Warehouse {

    static int MIN_QUANTITY = 1;
    static int FOOD_SHELF_LIFE = 5;
    static int NOT_FOOD_SHELF_LIFE = 365;

    @Getter
    Map<Product, Integer> products = new HashMap<>();

    {
        Product cucumber = new Food(1, "Cucumber 1kg", CurrencyCode.UAH, BigDecimal.valueOf(65.99),
                2023, 2, 14);
        addProduct(cucumber, 100);

        Product chicken = new Food(2, "Chicken 1kg", CurrencyCode.UAH, BigDecimal.valueOf(112.47),
                2023, 2, 14);
        addProduct(chicken, 50);

        Product rice = new Food(3, "Rice 900g", CurrencyCode.UAH, BigDecimal.valueOf(65.99),
                2023, 1, 20);
        addProduct(rice, 40);

        Product milk = new Food(4, "Milk 900ml", CurrencyCode.GEL, BigDecimal.valueOf(2.4),
                2023, 2, 14);
        addProduct(milk, 30);

        Product laptop = new NotFood(5, "Laptop", CurrencyCode.USD, BigDecimal.valueOf(2499.0),
                2022, 12, 17);
        addProduct(laptop, 5);

        Product smartphone = new NotFood(6, "Smartphone", CurrencyCode.USD, BigDecimal.valueOf(999.0),
                2022, 10, 3);
        addProduct(smartphone, 3);

        Product coffeeMachine = new NotFood(7, "Coffee machine", CurrencyCode.USD, BigDecimal.valueOf(149.25),
                2010, 11, 8);
        addProduct(coffeeMachine, 1);
    }

    public Map<Product, Integer> getAllProducts() {
        return products;
    }

    public Product getByUuid(int uuid) {
        Set<Product> products = getAllProducts().keySet();
        for (Product product : products) {
            if (product.getUuid() == uuid) {
                return product;
            }
        }
        return null;
    }

    public boolean addProduct(Product product, int quantity) {
        Objects.requireNonNull(product);
        log.info("Add a product(s) with ID " + product.getUuid() + " to the warehouse, quantity: " + quantity);

        if (quantity < MIN_QUANTITY) {
            return false;
        }

        checkIfRelateToExpiredProducts(product);

        if (products.containsKey(product)) {
            int oldQuantity = products.get(product);
            products.put(product, oldQuantity + quantity);
        } else {
            products.put(product, quantity);
        }

        return true;
    }

    public boolean removeProduct(Product product, int quantity) {
        Objects.requireNonNull(product);
        log.info("Remove a product(s) with ID " + product.getUuid() + " from the warehouse, quantity: " + quantity);

        int oldQuantity = products.get(product);

        if (quantity > oldQuantity || quantity < MIN_QUANTITY) {
            return false;
        } else if (quantity == oldQuantity) {
            products.remove(product);
        } else {
            products.put(product, oldQuantity - quantity);
        }

        return true;
    }

    public void checkIfRelateToExpiredProducts(Product product) {
        Field[] fields = product.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ShelfLife.class)) {
                field.setAccessible(true);
                if (product instanceof Food) {
                    product.setShelfLifeDays(FOOD_SHELF_LIFE);
                } else if (product instanceof NotFood) {
                    product.setShelfLifeDays(NOT_FOOD_SHELF_LIFE);
                }
            }
        }
    }
}
