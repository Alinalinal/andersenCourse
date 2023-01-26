package com.alinab.taskOneGOFpatterns.behavioral.memento;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Application {

    String version;
    Date date;

    public void setVersionAndDate(String version) {
        this.version = version;
        this.date = new Date();
    }

    public Save save() {
        return new Save(version);
    }

    public void load(Save save) {
        version = save.getVersion();
        date = save.getDate();
    }
}
