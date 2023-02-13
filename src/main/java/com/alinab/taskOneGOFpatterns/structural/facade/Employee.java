package com.alinab.taskOneGOFpatterns.structural.facade;

public class Employee {

    public void doTask(TaskTracker taskTracker) {
        if (taskTracker.isActiveTask()) {
            System.out.println("Employee is doing task...");
        } else {
            System.out.println("Employee is looking out the window...");
        }
    }
}
