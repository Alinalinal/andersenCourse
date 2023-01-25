package com.alinab.taskOneGOFpatterns.behavioral.mediator;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.User;

import java.util.ArrayList;
import java.util.List;

public class MyTextChat implements TextChat {

    private final List<User> users = new ArrayList<>();
    private User administrator;

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public void addOrdinaryUserToChat(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String textMessage, User user) {
        for (User u : users) {
            if (u != user) {
                u.getMessage(textMessage);
            }
        }
        administrator.getMessage(textMessage);
    }
}
