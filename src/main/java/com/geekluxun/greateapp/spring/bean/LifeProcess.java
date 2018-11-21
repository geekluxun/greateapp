package com.geekluxun.greateapp.spring.bean;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.context.Lifecycle;
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
//@Component
public class LifeProcess implements Lifecycle {
    private boolean isRunning = false;

    public LifeProcess(){
        System.out.println("======LifeProcess is create======");
    }

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

    //@Override
    public boolean isAutoStartup() {
        System.out.println("======isAutoStartup======");
        return true;
    }

    //@Override
    public void stop(Runnable callback) {
        System.out.println("======stop callback======");
        callback.run();
        isRunning = false;

    }

    /**
     * 相位决定bean之间的start顺序,按照0,1,2,3...顺序
     * @return
     */
    //@Override
    public int getPhase() {
        System.out.println("======getPhase======");
        return 0;
    }

    //@Override
    public void onRefresh() {
        System.out.println("======onRefresh======");

    }

    //@Override
    public void onClose() {
        System.out.println("======onClose======");

    }
}
