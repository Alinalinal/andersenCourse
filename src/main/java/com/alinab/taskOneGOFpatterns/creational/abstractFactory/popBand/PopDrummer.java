package com.alinab.taskOneGOFpatterns.creational.abstractFactory.popBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Drummer;

public class PopDrummer implements Drummer {

    @Override
    public void playDrums() {
        System.out.println("A drummer is doing some pop stuff...");
    }
}
