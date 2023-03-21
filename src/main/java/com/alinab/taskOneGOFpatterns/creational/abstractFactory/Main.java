package com.alinab.taskOneGOFpatterns.creational.abstractFactory;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.popBand.PopBandFactory;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.rockBand.RockBandFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("Woodstock Festival...");
        BandFactory rockBandFactory = new RockBandFactory();
        Vocalist rockVocalist = rockBandFactory.getVocalist();
        GuitarPlayer rockGuitarPlayer = rockBandFactory.getGuitarPlayer();
        Drummer rockDrummer = rockBandFactory.getDrummer();

        rockVocalist.sing();
        rockGuitarPlayer.playGuitar();
        rockDrummer.playDrums();

        System.out.println("\nMonterey POP-festival...");
        BandFactory popBandFactory = new PopBandFactory();
        Vocalist popVocalist = popBandFactory.getVocalist();
        GuitarPlayer popGuitarPlayer = popBandFactory.getGuitarPlayer();
        Drummer popDrummer = popBandFactory.getDrummer();

        popVocalist.sing();
        popGuitarPlayer.playGuitar();
        popDrummer.playDrums();
    }

}
