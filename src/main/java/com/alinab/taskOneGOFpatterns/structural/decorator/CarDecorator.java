package com.alinab.taskOneGOFpatterns.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarDecorator implements Car {

    private Car car;

    @Override
    public String assemble() {
        return car.assemble();
    }
}
