package com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers;

public class InteriorDesigner implements Designer {

    @Override
    public void createSketch() {
        System.out.println("Interior designer is drawing a sketch...");
    }
}
