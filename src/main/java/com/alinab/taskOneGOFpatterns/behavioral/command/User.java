package com.alinab.taskOneGOFpatterns.behavioral.command;

import com.alinab.taskOneGOFpatterns.behavioral.command.commands.Command;

public class User {

    private final Command turnOn;
    private final Command turnOff;
    private final Command restart;

    public User(Command turnOn, Command turnOff, Command restart) {
        this.turnOn = turnOn;
        this.turnOff = turnOff;
        this.restart = restart;
    }

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
