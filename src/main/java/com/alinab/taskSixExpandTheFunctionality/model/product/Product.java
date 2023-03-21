package com.alinab.taskSixExpandTheFunctionality.model.product;

import com.alinab.taskSixExpandTheFunctionality.model.product.currency.Currency;
import com.alinab.taskSixExpandTheFunctionality.model.product.currency.CurrencyCode;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode
public abstract class Product implements Serializable {

    static long serialVersionUID = 1L;

    int uuid;
    String name;
    Currency currency;
    BigDecimal purchasePrice;
    BigDecimal sellingPrice;
    LocalDate producedOn;

    @NonFinal
    @Setter
    @Getter
    @ShelfLife
    int shelfLifeDays;

    public Product(int uuid, String name, CurrencyCode currencyCode, BigDecimal purchasePrice,
                   int producedYear, int producedMonth, int producedDay) {
        Objects.requireNonNull(name, "name must not be null");
        this.uuid = uuid;
        this.name = name;
        this.currency = new Currency(currencyCode);
        this.purchasePrice = purchasePrice;
        this.sellingPrice = calculateSellingPrice();
        this.producedOn = LocalDate.of(producedYear, producedMonth, producedDay);
    }

    private BigDecimal calculateSellingPrice() {
        return purchasePrice.multiply(BigDecimal.valueOf(currency.getExchangeRate())
                        .multiply(BigDecimal.valueOf(currency.getMultiplicationConst())))
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return uuid + "   " + name + "          " + sellingPrice + " " + CurrencyCode.UAH;
    }
}
