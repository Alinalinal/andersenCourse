package com.alinab.taskOneGOFpatterns.structural.adapter.languages;

public class CanadianFrenchLanguage implements FrenchLanguage {

    @Override
    public void greet() {
        System.out.println("Bon après-midi, monsieur!");
    }
}
