package com.alinab.taskOneGOFpatterns.creational.builder;

public class Main {

    public static void main(String[] args) {
        BuildingBuilder buildingBuilder = new PrivateBuildingBuilder();
        Director director = new Director(buildingBuilder);

        Building privateBuilding = director.buildBuilding();
        System.out.println(privateBuilding);

        buildingBuilder = new OfficeBuildingBuilder();
        director.setBuilder(buildingBuilder);

        Building officeBuilding = director.buildBuilding();
        System.out.println(officeBuilding);
    }
}
