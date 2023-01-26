package com.alinab.taskOneGOFpatterns.creational.builder;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@ToString
public class Building {

    String address;
    BuildingType buildingType;
    int floors;
}
