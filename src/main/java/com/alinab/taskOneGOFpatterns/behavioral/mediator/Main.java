package com.alinab.taskOneGOFpatterns.behavioral.mediator;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.Administrator;
import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.OrdinaryUser;
import com.alinab.taskOneGOFpatterns.behavioral.mediator.users.User;

public class Main {

    public static void main(String[] args) {
        MyTextChat myTextChat = new MyTextChat();

        User administrator = new Administrator(myTextChat, "Admin");
        User user1 = new OrdinaryUser(myTextChat, "Katy");
        User user2 = new OrdinaryUser(myTextChat, "Bob");

        myTextChat.setAdministrator(administrator);
        myTextChat.addOrdinaryUserToChat(user1);
        myTextChat.addOrdinaryUserToChat(user2);

        user1.sendMessage("Hi there! I'm User1...");
        System.out.println();
        administrator.sendMessage("Hi!");
    }
}
