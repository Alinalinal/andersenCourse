package com.alinab.taskOneGOFpatterns.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Director {

    @Setter
    @Getter
    private BuildingBuilder builder;

    Building buildBuilding() {
        builder.createBuilding();
        builder.buildAddress();
        builder.buildBuildingType();
        builder.buildFloors();

        return builder.getBuilding();
    }
}
