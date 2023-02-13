package com.alinab.taskOneGOFpatterns.structural.bridge.webpages;

import com.alinab.taskOneGOFpatterns.structural.bridge.themes.Theme;

public class AboutPage extends WebPage {

    public AboutPage(Theme theme) {
        super(theme);
    }

    @Override
    public void showWebPage() {
        theme.getColor();
        System.out.println("This is About page...");
    }
}
