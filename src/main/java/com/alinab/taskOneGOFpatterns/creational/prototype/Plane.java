package com.alinab.taskOneGOFpatterns.creational.prototype;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Plane implements Copyable {

    String model;
    int yearOfProduction;

    @Override
    public Plane copy() {
        return new Plane(this.model, this.yearOfProduction);
    }
}
