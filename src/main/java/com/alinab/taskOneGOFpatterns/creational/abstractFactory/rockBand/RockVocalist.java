package com.alinab.taskOneGOFpatterns.creational.abstractFactory.rockBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Vocalist;

public class RockVocalist implements Vocalist {

    @Override
    public void sing() {
        System.out.println("A vocalist is singing rock ballad...");
    }
}
