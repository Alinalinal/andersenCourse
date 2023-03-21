package com.alinab.taskFiveStructure.dao.productDAO;

import com.alinab.taskFiveStructure.model.product.Product;
import com.alinab.taskFiveStructure.storage.ProductStorage;

import java.util.List;
import java.util.Objects;

public class ProductListDAO {

    private final ProductStorage productStorage = new ProductStorage();

    public List<Product> getAllProducts() {
        return productStorage.getProducts();
    }

    public Product get(int uuid) {
        for (Product product : productStorage.getProducts()) {
            if (product.getUuid() == uuid) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product);
        if (!productStorage.getProducts().contains(product)) {
            productStorage.getProducts().add(product);
        }
    }
}
