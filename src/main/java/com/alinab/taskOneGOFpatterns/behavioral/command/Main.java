package com.alinab.taskOneGOFpatterns.behavioral.command;

import com.alinab.taskOneGOFpatterns.behavioral.command.commands.RestartCommand;
import com.alinab.taskOneGOFpatterns.behavioral.command.commands.TurnOffCommand;
import com.alinab.taskOneGOFpatterns.behavioral.command.commands.TurnOnCommand;

public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        User user = new User(
                new TurnOnCommand(computer),
                new TurnOffCommand(computer),
                new RestartCommand(computer));

        user.turnOnComputer();
        user.restartComputer();
        user.turnOffComputer();
    }
}
