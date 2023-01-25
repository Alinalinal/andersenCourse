package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public class SubtractNumbersHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void calculate(Numbers request) {
        if (request.getCalculationRequest().equals("sub")) {
            System.out.println(request.getNumberOne() + " - " + request.getNumberTwo() + " = "
                    + (request.getNumberOne() - request.getNumberTwo()));
        } else {
            nextHandler.calculate(request);
        }
    }
}
