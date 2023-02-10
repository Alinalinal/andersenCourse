package com.alinab.taskOneGOFpatterns.structural.adapter;

import com.alinab.taskOneGOFpatterns.structural.adapter.languages.EnglishLanguage;
import com.alinab.taskOneGOFpatterns.structural.adapter.languages.FrenchLanguage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Translator implements FrenchLanguage {

    private EnglishLanguage englishLanguage;

    @Override
    public void greet() {
        englishLanguage.greet();
    }
}
