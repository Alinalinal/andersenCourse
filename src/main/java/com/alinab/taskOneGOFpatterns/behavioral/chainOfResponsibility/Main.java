package com.alinab.taskOneGOFpatterns.behavioral.chainOfResponsibility;

public class Main {

    public static void main(String[] args) {
        Handler handler1 = new AddNumbersHandler();
        Handler handler2 = new SubtractNumbersHandler();
        Handler handler3 = new MultiplyNumbersHandler();
        Handler handler4 = new DivideNumbersHandler();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler3.setNextHandler(handler4);

        Numbers request1 = new Numbers(3, 2, "sub");
        handler1.calculate(request1);

        Numbers request2 = new Numbers(3, 2, "doThis");
        handler1.calculate(request2);
    }
}
