package com.alinab.taskOneGOFpatterns.creational.builder;

public class PrivateBuildingBuilder extends BuildingBuilder {

    @Override
    void buildAddress() {
        building.setAddress("Some private address");
    }

    @Override
    void buildBuildingType() {
        building.setBuildingType(BuildingType.PRIVATE);
    }

    @Override
    void buildFloors() {
        building.setFloors(2);
    }
}
