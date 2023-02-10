package com.alinab.taskOneGOFpatterns.creational.prototype;

public class Main {

    public static void main(String[] args) {
        Plane prototype = new Plane("Ан-225", 1998);
        System.out.println(prototype);

        Plane copy = prototype.copy();
        System.out.println(copy);

        System.out.println(prototype == copy);
        System.out.println(prototype.equals(copy));
    }
}
