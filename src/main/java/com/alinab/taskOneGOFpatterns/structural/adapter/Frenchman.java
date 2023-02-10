package com.alinab.taskOneGOFpatterns.structural.adapter;

import com.alinab.taskOneGOFpatterns.structural.adapter.languages.FrenchLanguage;

public class Frenchman {

    public void sayHello(FrenchLanguage frenchLanguage) {
        frenchLanguage.greet();
    }
}
