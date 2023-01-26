package com.alinab.taskOneGOFpatterns.structural.decorator;

public class SportCar extends CarDecorator {

    public SportCar(Car car) {
        super(car);
    }

    @Override
    public String assemble() {
        String info = super.assemble();
        return info + "and we add new engine and a lot of magic... ";
    }
}
