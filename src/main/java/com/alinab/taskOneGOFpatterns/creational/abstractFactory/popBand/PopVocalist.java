package com.alinab.taskOneGOFpatterns.creational.abstractFactory.popBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Vocalist;

public class PopVocalist implements Vocalist {

    @Override
    public void sing() {
        System.out.println("A vocalist is singing popular song...");
    }
}
