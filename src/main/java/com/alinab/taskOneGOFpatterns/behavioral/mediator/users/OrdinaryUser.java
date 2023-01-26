package com.alinab.taskOneGOFpatterns.behavioral.mediator.users;

import com.alinab.taskOneGOFpatterns.behavioral.mediator.TextChat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrdinaryUser implements User {

    final TextChat textChat;

    @Getter
    @Setter
    String name;

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
