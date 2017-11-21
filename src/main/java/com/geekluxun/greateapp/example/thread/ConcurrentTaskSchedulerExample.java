package com.geekluxun.greateapp.example.thread;

import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Created by luxun on 2017/11/21.
 */
public class ConcurrentTaskSchedulerExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentTaskScheduler s = new ConcurrentTaskScheduler(
                Executors.newScheduledThreadPool(5));
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            s.schedule(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                out.printf("task: %d. Thread: %s%n",
                        finalI,
                        Thread.currentThread().getName());
            }, new PeriodicTrigger(1000));
        }
        //shutting down after 3 sec
        Thread.sleep(3000);
        out.println("--- shutting down ----");
        ((ExecutorService) s.getConcurrentExecutor()).shutdown();
    }
}
