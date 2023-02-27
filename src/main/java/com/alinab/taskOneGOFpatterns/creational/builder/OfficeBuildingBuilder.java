package com.alinab.taskOneGOFpatterns.creational.builder;

public class OfficeBuildingBuilder extends BuildingBuilder {

    @Override
    void buildAddress() {
        building.setAddress("Some office address");
    }

    @Override
    void buildBuildingType() {
        building.setBuildingType(BuildingType.OFFICE);
    }

    @Override
    void buildFloors() {
        building.setFloors(20);
    }
}
