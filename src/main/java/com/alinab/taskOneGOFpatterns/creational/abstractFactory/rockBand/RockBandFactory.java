package com.alinab.taskOneGOFpatterns.creational.abstractFactory.rockBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.BandFactory;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Drummer;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.GuitarPlayer;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Vocalist;

public class RockBandFactory implements BandFactory {

    @Override
    public Vocalist getVocalist() {
        return new RockVocalist();
    }

    @Override
    public GuitarPlayer getGuitarPlayer() {
        return new RockGuitarPlayer();
    }

    @Override
    public Drummer getDrummer() {
        return new RockDrummer();
    }
}
