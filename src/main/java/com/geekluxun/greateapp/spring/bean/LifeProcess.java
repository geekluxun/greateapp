package com.geekluxun.greateapp.spring.bean;

import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-21 16:30
 * @Description:
 * @Other:
 */
@Component
public class LifeProcess implements SmartLifecycle {
    private boolean isRunning = false;


    @Override
    public void start() {
        System.out.println("======start======");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("======stop======");

    }

    @Override
    public boolean isRunning() {
        System.out.println("======isRunning======");
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        System.out.println("======isRunning======");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("======stop callback======");
        callback.run();
        isRunning = false;

    }

    @Override
    public int getPhase() {
        System.out.println("======getPhase======");
        return 0;
    }
}
