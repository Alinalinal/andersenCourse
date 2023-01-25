package com.alinab.taskOneGOFpatterns.behavioral.command.commands;

import com.alinab.taskOneGOFpatterns.behavioral.command.Computer;

public class TurnOffCommand implements Command {

    private final Computer computer;

    public TurnOffCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.turnOff();
    }
}
