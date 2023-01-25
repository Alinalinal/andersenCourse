package com.alinab.taskOneGOFpatterns.behavioral.command.commands;

import com.alinab.taskOneGOFpatterns.behavioral.command.Computer;

public class TurnOnCommand implements Command {

    private final Computer computer;

    public TurnOnCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.turnOn();
    }
}
