package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubtractNumbersHandler implements Handler {

    static final String SUBTRACT = "-";

    @Setter
    Handler nextHandler;

    @Override
    public void calculate(Numbers request) {
        if (SUBTRACT.equals(request.getCalculationRequest())) {
            System.out.println(request.getNumberOne() + " - " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() - request.getNumberTwo()));
        } else {
            nextHandler.calculate(request);
        }
    }
}
