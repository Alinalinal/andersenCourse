package com.alinab.taskFourMultithreading;

import java.util.concurrent.*;

public class Main {

    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {

        MyThread thread = new MyThread();

        thread.run();

        Thread thread1 = new Thread(thread);
        thread1.start();

        Callable<Boolean> myTask = new MyThread();

        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        singleExecutor.submit(myTask);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService fixedExecutor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            fixedExecutor.submit(myTask);
        }

        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            cachedExecutor.submit(myTask);
        }

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);
        for(int i = 1; i <= NUMBER_OF_THREADS; i++) {
            scheduledExecutor.schedule(myTask, 1, TimeUnit.SECONDS);
        }

        try {
            Thread.sleep(4000);

            singleExecutor.shutdown();
            fixedExecutor.shutdown();
            cachedExecutor.shutdown();
            scheduledExecutor.shutdown();

            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread);
    }
}
