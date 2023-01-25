package com.alinab.taskOneGOFpatterns.creational.builder;

public class Director {

    private BuildingBuilder builder;

    public Director(BuildingBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(BuildingBuilder builder) {
        this.builder = builder;
    }

    public BuildingBuilder getBuilder() {
        return builder;
    }

    Building buildBuilding() {
        builder.createBuilding();
        builder.buildAddress();
        builder.buildBuildingType();
        builder.buildFloors();

        return builder.getBuilding();
    }
}
