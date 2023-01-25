package com.alinab.taskOneGOFpatterns.structural.decorator;

public class FamilyCar extends CarDecorator {
    public FamilyCar(Car car) {
        super(car);
    }

    @Override
    public String assemble() {
        String info = super.assemble();
        return info + "but we add new comfortable seats and a little magic... ";
    }
}
