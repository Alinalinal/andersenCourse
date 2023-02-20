package com.alinab.taskSixExpandTheFunctionality.model.product;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ShelfLife {

    int shelfLifeDays() default 0;
}
