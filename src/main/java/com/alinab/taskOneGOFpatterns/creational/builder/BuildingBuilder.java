package com.alinab.taskOneGOFpatterns.creational.builder;

import lombok.Getter;

public abstract class BuildingBuilder {

    @Getter
    Building building;

    void createBuilding() {
        building = new Building();
    }

    abstract void buildAddress();

    abstract void buildBuildingType();

    abstract void buildFloors();
}
