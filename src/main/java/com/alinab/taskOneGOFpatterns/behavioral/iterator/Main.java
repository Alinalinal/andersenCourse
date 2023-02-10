package com.alinab.taskOneGOFpatterns.behavioral.iterator;

public class Main {

    public static void main(String[] args) {
        String[] articles = {"Editor's letter", "Street fashion", "How to create something"};
        Magazine magazine = new Magazine("Fashion M", articles);

        Iterator iterator = magazine.getIterator();
        System.out.println("This month in " + magazine.getTitle() + " you can read the following articles:");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
