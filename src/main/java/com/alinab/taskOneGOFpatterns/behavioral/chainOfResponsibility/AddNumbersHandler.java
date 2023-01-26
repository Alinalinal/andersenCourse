package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddNumbersHandler implements Handler {

    static final String ADD = "+";

    @Setter
    private Handler nextHandler;

    @Override
    public void calculate(Numbers request) {
        if (ADD.equals(request.getCalculationRequest())) {
            System.out.println(request.getNumberOne() + " + " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() + request.getNumberTwo()));
        } else {
            nextHandler.calculate(request);
        }
    }
}
