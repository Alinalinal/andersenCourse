package com.alinab.taskOneGOFpatterns.creational.abstractFactory.popBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.GuitarPlayer;

public class PopGuitarPlayer implements GuitarPlayer {

    @Override
    public void playGuitar() {
        System.out.println("A guitar player is playing guitar and dancing...");
    }
}
