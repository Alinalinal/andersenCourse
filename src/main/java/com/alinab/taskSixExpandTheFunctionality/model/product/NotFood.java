package com.alinab.taskSixExpandTheFunctionality.model.product;

import com.alinab.taskSixExpandTheFunctionality.model.product.currency.CurrencyCode;

import java.math.BigDecimal;

public class NotFood extends Product {

    public NotFood(int uuid, String name, CurrencyCode currencyCode, BigDecimal purchasePrice,
                   int producedYear, int producedMonth, int producedDay) {
        super(uuid, name, currencyCode, purchasePrice, producedYear, producedMonth, producedDay);
    }
}
