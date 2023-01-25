package com.alinab.taskOneGOFpatterns.creational.builder;

public class Building {

    private String address;
    private BuildingType buildingType;
    private int floors;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHouseType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "address='" + address + '\'' +
                ", buildingType=" + buildingType +
                ", floors=" + floors +
                '}';
    }
}
