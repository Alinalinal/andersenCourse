package com.alinab.taskOneGOFpatterns.structural.facade;

public class TaskTracker {

    private boolean activeTask;

    public boolean isActiveTask() {
        return activeTask;
    }

    public void startTask() {
        System.out.println("Task in progress...");
        activeTask = true;
    }

    public void finishTask() {
        System.out.println("Task completed...");
        activeTask = false;
    }
}
