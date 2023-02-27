package com.alinab.taskFiveStructure.storage;

import com.alinab.taskFiveStructure.model.product.Food;
import com.alinab.taskFiveStructure.model.product.NotFood;
import com.alinab.taskFiveStructure.model.product.Product;
import lombok.Getter;

import java.util.*;

public class ProductStorage {

    @Getter
    private final List<Product> products = new ArrayList<>();

    {
        products.add(new Food(1, "Cucumber"));
        products.add(new Food(2, "Chicken"));
        products.add(new Food(3, "Rice"));

        products.add(new NotFood(4, "Laptop"));
        products.add(new NotFood(5, "Coffee machine"));
        products.add(new NotFood(6, "Smartphone"));
    }
}
