package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public class Numbers {

    private final int numberOne;
    private final int numberTwo;
    private final String calculationRequest;

    public Numbers(int numberOne, int numberTwo, String calculationRequest) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.calculationRequest = calculationRequest;
    }

    public int getNumberOne() {
        return numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public String getCalculationRequest() {
        return calculationRequest;
    }
}
