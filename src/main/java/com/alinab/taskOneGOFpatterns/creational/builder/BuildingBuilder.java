package com.alinab.taskOneGOFpatterns.creational.builder;

public abstract class BuildingBuilder {
    Building building;

    void createBuilding() {
        building = new Building();
    }

    abstract void buildAddress();

    abstract void buildBuildingType();

    abstract void buildFloors();

    Building getBuilding() {
        return building;
    }
}
