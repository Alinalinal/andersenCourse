package com.alinab.taskOneGOFpatterns.structural.adapter;

import com.alinab.taskOneGOFpatterns.structural.adapter.languages.EnglishLanguage;
import com.alinab.taskOneGOFpatterns.structural.adapter.languages.FrenchLanguage;

public class Translator implements FrenchLanguage {

    private EnglishLanguage englishLanguage;

    public Translator(EnglishLanguage englishLanguage) {
        this.englishLanguage = englishLanguage;
    }

    @Override
    public void greet() {
        englishLanguage.greet();
    }
}
