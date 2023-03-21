package com.alinab.taskSixExpandTheFunctionality.model.order;

import com.alinab.taskSixExpandTheFunctionality.model.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Order implements Serializable {

    final int orderUuid;
    final Map<Product, Integer> bucket;
    @Setter
    int bucketSize;

    public Order(int orderUuid) {
        this.orderUuid = orderUuid;
        this.bucket = new HashMap<>();
        this.bucketSize = 0;
    }
}
