package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public class AddNumbersHandler implements Handler {

    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {
        if (request.getCalculationRequest().equals("add")) {
            System.out.println(request.getNumberOne() + " + " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() + request.getNumberTwo()));
        } else {
            nextHandler.calculate(request);
        }
    }
}
