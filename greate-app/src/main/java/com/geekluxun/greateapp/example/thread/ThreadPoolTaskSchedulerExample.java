package com.geekluxun.greateapp.example.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalTime;

/**
 * Created by luxun on 2017/11/21.
 */
public class ThreadPoolTaskSchedulerExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTaskScheduler s = new ThreadPoolTaskScheduler();

        s.setPoolSize(5);
        s.initialize();

        for (int i = 0; i < 2; i++) {
            s.scheduleAtFixedRate(
                    () -> System.out.printf("Task: %s, Time: %s:%n",
                            Thread.currentThread().getName(),
                            LocalTime.now()), 1000);
        }

        Thread.sleep(10000);
        System.out.println("shutting down after 10 sec");
        s.getScheduledThreadPoolExecutor().shutdownNow();
    }
}
