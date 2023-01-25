package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public class DivideNumbersHandler implements Handler {

    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {
        if (request.getCalculationRequest().equals("div")) {
            System.out.println(request.getNumberOne() + " / " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() / request.getNumberTwo()));
        } else {
            System.out.println("My calculator works only for add, sub, mult & div!");
        }
    }
}
