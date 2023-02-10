package com.alinab.taskOneGOFpatterns.behavioral.mediator;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.User;

public interface TextChat {

    void sendMessage(String textMessage, User user);
}
