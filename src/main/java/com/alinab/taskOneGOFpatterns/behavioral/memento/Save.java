package com.alinab.taskOneGOFpatterns.behavioral.memento;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Save {

    final String version;
    final Date date;

    public Save(String version) {
        this.version = version;
        this.date = new Date();
    }
}
