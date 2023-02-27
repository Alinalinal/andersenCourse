package com.alinab.taskFiveStructure.dao.orderDAO;

import com.alinab.taskFiveStructure.model.product.Product;

import java.util.List;

public interface OrderDAO {

    boolean addProduct(int uuid);

    boolean deleteProduct(int uuid);

    List<Product> getAllProducts();

    void clear();

    int bucketSize();
}
