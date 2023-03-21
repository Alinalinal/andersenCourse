package com.alinab.taskOneGOFpatterns.behavioral.command;

import com.alinab.taskOneGOFpatterns.behavioral.command.commands.Command;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class User {

    Command turnOn;
    Command turnOff;
    Command restart;

    public void turnOnComputer() {
        turnOn.execute();
    }

    public void turnOffComputer() {
        turnOff.execute();
    }

    public void restartComputer() {
        restart.execute();
    }
}
