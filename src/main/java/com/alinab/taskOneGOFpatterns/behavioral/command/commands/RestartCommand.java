package com.alinab.taskOneGOFpatterns.behavioral.command.commands;

import com.alinab.taskOneGOFpatterns.behavioral.command.Computer;

public class RestartCommand implements Command {

    private final Computer computer;

    public RestartCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.restart();
    }
}
