package com.alinab.taskOneGOFpatterns.structural.bridge.webpages;

import com.alinab.taskOneGOFpatterns.structural.bridge.themes.Theme;

public abstract class WebPage {

    protected Theme theme;

    protected WebPage(Theme theme) {
        this.theme = theme;
    }

    public abstract void showWebPage();
}
