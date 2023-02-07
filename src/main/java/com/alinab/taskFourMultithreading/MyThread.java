package com.alinab.taskFourMultithreading;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyThread implements Runnable {

    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ms");

    @Getter
    Map<String, Integer> map = new ConcurrentHashMap<>();

    List<List<String>> threadsInfo = new ArrayList<>();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        List<String> threadList = new ArrayList<>();
        threadList.add(threadName + " started its work at " + LocalDateTime.now().format(FORMATTER) + "...");
        map.put(threadName, Data.incrementAndGetResourceValue());
        threadList.add(threadName + " finished its work at " + LocalDateTime.now().format(FORMATTER) + "!");
        threadsInfo.add(threadList);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<String> threadInfo : threadsInfo) {
            for (String info : threadInfo) {
                sb.append(info).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @ToString
    static class Data {

        static AtomicInteger dataCounter = new AtomicInteger(0);

        public static int incrementAndGetResourceValue() {
            return dataCounter.incrementAndGet();
        }
    }
}
