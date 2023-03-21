package com.alinab.taskOneGOFpatterns.behavioral.command.commands;

import com.alinab.taskOneGOFpatterns.behavioral.command.Computer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurnOnCommand implements Command {

    private final Computer computer;

    @Override
    public void execute() {
        computer.turnOn();
    }
}
