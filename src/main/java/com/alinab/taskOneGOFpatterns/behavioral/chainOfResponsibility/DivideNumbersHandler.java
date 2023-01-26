package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivideNumbersHandler implements Handler {

    static final String DIVIDE = "/";

    @Setter
    Handler nextHandler;

    @Override
    public void calculate(Numbers request) {
        if (DIVIDE.equals(request.getCalculationRequest())) {
            System.out.println(request.getNumberOne() + " / " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() / request.getNumberTwo()));
        } else {
            System.out.println("My calculator works only for +, -, * & /!");
        }
    }
}
