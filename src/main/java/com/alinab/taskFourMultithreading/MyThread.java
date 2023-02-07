package com.alinab.taskFourMultithreading;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyThread implements Runnable, Callable<Boolean> {

    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ms");
    static AtomicInteger COUNTER = new AtomicInteger(0);
    static Map<String, Integer> MAP = new ConcurrentHashMap<>();

    @Override
    public void run() {
        doTask();
    }

    @Override
    public Boolean call() {
        doTask();
        return true;
    }

    private void doTask() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " started its work at " + LocalDateTime.now().format(FORMATTER) + "...");
        MAP.put(threadName, incrementAndGetCounter());
        System.out.println(threadName + " finished its work at " + LocalDateTime.now().format(FORMATTER) + "!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int incrementAndGetCounter() {
        return COUNTER.incrementAndGet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : MAP.entrySet()) {
            sb.append(entry.getKey()).append(", resource data: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
