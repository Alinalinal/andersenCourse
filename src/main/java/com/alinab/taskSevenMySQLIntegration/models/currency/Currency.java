package com.alinab.taskSevenMySQLIntegration.models.currency;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Currency implements Serializable {

    static final long serialVersionUID = 1L;
    static final double UA_MARGIN = 1.2;
    static final double NOT_UA_MARGIN = 1.7;

    static final String HRYVNYA = "Hryvnya";
    static final String DOLLAR = "Dollar";
    static final String LARI = "Lari";

    static final double UAH_RATE = 1.0;
    static final double USD_RATE = 36.85;
    static final double GEL_RATE = 13.83;

    final String currencyCode;
    String name;
    double exchangeRate;
    double multiplicationConst;

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;

        switch (currencyCode) {
            case "UAH":
                this.name = HRYVNYA;
                this.exchangeRate = UAH_RATE;
                this.multiplicationConst = UA_MARGIN;
                break;
            case "USD":
                this.name = DOLLAR;
                this.exchangeRate = USD_RATE;
                this.multiplicationConst = NOT_UA_MARGIN;
                break;
            case "GEL":
                this.name = LARI;
                this.exchangeRate = GEL_RATE;
                this.multiplicationConst = NOT_UA_MARGIN;
                break;
        }
    }
}
