package com.alinab.taskOneGOFpatterns.creational.abstractFactory.rockBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.GuitarPlayer;

public class RockGuitarPlayer implements GuitarPlayer {

    @Override
    public void playGuitar() {
        System.out.println("A guitar player is playing his Gibson...");
    }
}
