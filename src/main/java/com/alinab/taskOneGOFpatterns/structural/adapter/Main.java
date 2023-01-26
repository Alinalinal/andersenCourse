package com.alinab.taskOneGOFpatterns.structural.adapter;

import com.alinab.taskOneGOFpatterns.structural.adapter.languages.BritishEnglishLanguage;
import com.alinab.taskOneGOFpatterns.structural.adapter.languages.CanadianFrenchLanguage;
import com.alinab.taskOneGOFpatterns.structural.adapter.languages.EnglishLanguage;

public class Main {

    public static void main(String[] args) {
        Frenchman human = new Frenchman();

        EnglishLanguage britishEnglish = new BritishEnglishLanguage();
        human.sayHello(new Translator(britishEnglish));
    }
}
