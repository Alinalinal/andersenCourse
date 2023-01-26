package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MultiplyNumbersHandler implements Handler {

    static final String MULTIPLY = "*";

    @Setter
    Handler nextHandler;

    @Override
    public void calculate(Numbers request) {
        if (MULTIPLY.equals(request.getCalculationRequest())) {
            System.out.println(request.getNumberOne() + " * " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() * request.getNumberTwo()));
        } else {
            nextHandler.calculate(request);
        }
    }
}
