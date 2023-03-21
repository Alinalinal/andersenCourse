package com.alinab.taskOneGOFpatterns.structural.adapter.languages;

public class BritishEnglishLanguage implements EnglishLanguage {

    @Override
    public void greet() {
        System.out.println("Hello, sir!");
    }
}
