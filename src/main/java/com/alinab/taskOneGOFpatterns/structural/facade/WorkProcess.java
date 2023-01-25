package com.alinab.taskOneGOFpatterns.structural.facade;

public class WorkProcess {

    public void solveTask() {
        Work work = new Work();
        work.doWork();
        TaskTracker taskTracker = new TaskTracker();
        taskTracker.startTask();
        Employee employee = new Employee();
        employee.doTask(taskTracker);
    }
}
