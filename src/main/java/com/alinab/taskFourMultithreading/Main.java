package com.alinab.taskFourMultithreading;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        MyThread thread = new MyThread();

        Thread thread1 = new Thread(thread);
        thread1.start();

        Thread thread2 = new Thread(thread);
        thread2.start();

        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        singleExecutor.submit(thread);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService fixedExecutor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            fixedExecutor.execute(thread);
        }

        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            cachedExecutor.execute(thread);
        }

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);
        for(int i = 0; i < 2; i++) {
            scheduledExecutor.schedule(thread, 1, TimeUnit.SECONDS);
        }

        try {
            Thread.sleep(4000);
            singleExecutor.shutdown();
            fixedExecutor.shutdown();
            cachedExecutor.shutdown();
            scheduledExecutor.shutdown();

            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Integer> entry : thread.getMap().entrySet()) {
            System.out.println(entry.getKey() + ", resource data: " + entry.getValue());
        }
        System.out.println();

        System.out.println(thread);
    }
}
