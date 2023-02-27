package com.alinab.taskOneGOFpatterns.structural.bridge.themes;

public class LightTheme implements Theme {

    @Override
    public void getColor() {
        System.out.println("Theme is off white");
    }
}
