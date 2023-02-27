package com.alinab.taskSixExpandTheFunctionality.dao.orderDAO;

import com.alinab.taskSixExpandTheFunctionality.model.product.Product;

import java.util.Map;

public interface OrderDAO {

    boolean addProduct(int uuid, int quantity);

    boolean deleteProduct(int uuid, int quantity);

    Map<Product, Integer> getAllProducts();

    void clear();

    int bucketSize();

    Product getByUuid(int uuid);

    void saveOrder();

    void uploadOrder();
}
