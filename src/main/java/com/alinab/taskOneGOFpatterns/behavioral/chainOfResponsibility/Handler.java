package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public interface Handler {

    void setNextHandler(Handler nextHandler);

    void calculate(Numbers request);
}
