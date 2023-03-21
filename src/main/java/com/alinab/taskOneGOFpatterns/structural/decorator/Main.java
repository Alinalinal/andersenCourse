package com.alinab.taskOneGOFpatterns.structural.decorator;

public class Main {

    public static void main(String[] args) {
        Car sportCar = new SportCar(new BasicCar());
        System.out.println(sportCar.assemble());

        Car sportFamilyCar = new SportCar(new FamilyCar(new BasicCar()));
        System.out.println(sportFamilyCar.assemble());
    }
}
