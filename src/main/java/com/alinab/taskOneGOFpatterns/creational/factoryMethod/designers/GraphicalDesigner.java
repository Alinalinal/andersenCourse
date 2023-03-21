package com.alinab.taskOneGOFpatterns.creational.factoryMethod.designers;

public class GraphicalDesigner implements Designer {

    @Override
    public void createSketch() {
        System.out.println("Graphical designer is drawing a sketch...");
    }
}
