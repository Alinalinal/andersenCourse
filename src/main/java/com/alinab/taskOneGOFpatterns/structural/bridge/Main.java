package com.alinab.taskOneGOFpatterns.structural.bridge;

import com.alinab.taskOneGOFpatterns.structural.bridge.themes.DarkTheme;
import com.alinab.taskOneGOFpatterns.structural.bridge.themes.LightTheme;
import com.alinab.taskOneGOFpatterns.structural.bridge.themes.Theme;
import com.alinab.taskOneGOFpatterns.structural.bridge.webpages.AboutPage;
import com.alinab.taskOneGOFpatterns.structural.bridge.webpages.CareersPage;
import com.alinab.taskOneGOFpatterns.structural.bridge.webpages.WebPage;

public class Main {

    public static void main(String[] args) {
        Theme darkTheme = new DarkTheme();
        WebPage about = new AboutPage(darkTheme);

        Theme lightTheme = new LightTheme();
        WebPage careers = new CareersPage(lightTheme);

        about.showWebPage();
        careers.showWebPage();
    }
}
