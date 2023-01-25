package com.alinab.taskOneGOFpatterns.creational.abstractFactory;

public interface BandFactory {

    Vocalist getVocalist();

    GuitarPlayer getGuitarPlayer();

    Drummer getDrummer();
}
