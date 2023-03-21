package com.alinab.taskFiveStructure.model.order;

import com.alinab.taskFiveStructure.model.product.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @Getter
    private final List<Product> bucket;

    public Order() {
        this.bucket = new ArrayList<>();
    }
}
