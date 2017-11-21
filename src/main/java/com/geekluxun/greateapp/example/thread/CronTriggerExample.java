package com.geekluxun.greateapp.example.thread;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalTime;

/**
 * Created by luxun on 2017/11/21.
 */
public class CronTriggerExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program starts at : " + LocalTime.now());
        ThreadPoolTaskScheduler s = new ThreadPoolTaskScheduler();
        s.setPoolSize(5);
        s.initialize();
        for (int i = 0; i < 2; i++) {
            s.schedule(getTask(), new CronTrigger("0 45 22 * * ?"));
        }
        s.getScheduledThreadPoolExecutor().shutdown();
    }

    public static Runnable getTask() {
        return () -> System.out.printf("Task: %s, Time: %s:%n",
                Thread.currentThread().getName(),
                LocalTime.now());
    }
}
