package com.alinab.taskOneGOFpatterns.behavioral.mediator;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.User;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyTextChat implements TextChat {

    final List<User> users = new ArrayList<>();

    @Setter
    User administrator;

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
