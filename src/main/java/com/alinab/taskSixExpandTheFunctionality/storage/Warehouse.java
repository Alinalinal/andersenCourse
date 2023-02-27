package com.alinab.taskSixExpandTheFunctionality.storage;

import com.alinab.taskSixExpandTheFunctionality.model.product.Product;

import java.util.Map;

public interface Warehouse {

    Map<Product, Integer> getAllProducts();

    Product getByUuid(int uuid);

    boolean addProduct(Product product, int quantity);

    boolean removeProduct(Product product, int quantity);
}
