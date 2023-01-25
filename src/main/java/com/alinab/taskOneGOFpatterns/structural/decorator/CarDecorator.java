package com.alinab.taskOneGOFpatterns.structural.decorator;

public class CarDecorator implements Car {

    private Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public String assemble() {
        return car.assemble();
    }
}
