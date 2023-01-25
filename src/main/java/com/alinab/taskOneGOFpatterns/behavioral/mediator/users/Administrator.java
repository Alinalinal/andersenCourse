package com.alinab.taskOneGOFpatterns.behavioral.mediator.users;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.TextChat;

public class Administrator implements User {

    private final TextChat textChat;
    private String name;

    public Administrator(TextChat textChat, String name) {
        this.textChat = textChat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sendMessage(String textMessage) {
        textChat.sendMessage(textMessage, this);
    }

    @Override
    public void getMessage(String textMessage) {
        System.out.println("Text message to: " + this.name);
        System.out.println("Message: " + textMessage);
    }
}
