package com.alinab.taskOneGOFpatterns.behavioral.memento;

public class Main {

    public static void main(String[] args) {
        Application app = new Application();
        Repository repo = new Repository();

        app.setVersionAndDate("Version 1.0");
        System.out.println(app);

        repo.setSave(app.save());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.setVersionAndDate("Version 1.1");
        System.out.println(app);

        app.load(repo.getSave());
        System.out.println(app);
    }
}
