package com.alinab.taskOneGOFpatterns.structural.decorator;

public class BasicCar implements Car {

    @Override
    public String assemble() {
        return "This is basic car...";
    }
}
