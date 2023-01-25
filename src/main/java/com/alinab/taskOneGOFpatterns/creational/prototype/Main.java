package com.alinab.taskOneGOFpatterns.creational.prototype;

public class Main {

    public static void main(String[] args) {
        Plane prototype = new Plane("Ан-225", 1998);
        Plane copy = prototype.copy();
    }
}
