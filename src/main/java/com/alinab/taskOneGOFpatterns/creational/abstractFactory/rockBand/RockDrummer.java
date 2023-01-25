package com.alinab.taskOneGOFpatterns.creational.abstractFactory.rockBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Drummer;

public class RockDrummer implements Drummer {

    @Override
    public void playDrums() {
        System.out.println("A drummer is plaing his Tama drums...");
    }
}
