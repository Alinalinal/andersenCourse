package com.alinab.taskFiveStructure.model.product;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode
public abstract class Product {

    int uuid;
    String name;

    public Product(int uuid, String name) {
        Objects.requireNonNull(name, "name must not be null");
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public String toString() {
        return uuid + "   " + name;
    }
}
