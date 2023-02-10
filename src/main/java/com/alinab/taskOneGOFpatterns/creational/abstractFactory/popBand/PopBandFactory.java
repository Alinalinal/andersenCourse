package com.alinab.taskOneGOFpatterns.creational.abstractFactory.popBand;

import com.alinab.taskOneGOFpatterns.creational.abstractFactory.BandFactory;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Drummer;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.GuitarPlayer;
import com.alinab.taskOneGOFpatterns.creational.abstractFactory.Vocalist;

public class PopBandFactory implements BandFactory {

    @Override
    public Vocalist getVocalist() {
        return new PopVocalist();
    }

    @Override
    public GuitarPlayer getGuitarPlayer() {
        return new PopGuitarPlayer();
    }

    @Override
    public Drummer getDrummer() {
        return new PopDrummer();
    }
}
