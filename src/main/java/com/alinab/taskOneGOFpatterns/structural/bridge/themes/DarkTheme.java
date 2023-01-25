package com.alinab.taskOneGOFpatterns.structural.bridge.themes;

public class DarkTheme implements Theme {

    @Override
    public void getColor() {
        System.out.println("Theme is black as night");
    }
}
