package com.geekluxun.greateapp.spring.bean.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-21 16:30
 * @Description:
 * @Other:
 */
@Component
public class Foo2 implements SmartLifecycle {
    private boolean isRunning = false;


    public Foo2() {
        System.out.println("======Foo2 is create======");
    }

    @PostConstruct
    public void init() {
        System.out.println("======Foo2 is init======");
    }

    @PreDestroy
    public void destory() {
        System.out.println("======Foo2 is destory======");
    }

    @Override
    public void start() {
        System.out.println("Foo2======start======");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("Foo2======stop======");

    }

    @Override
    public boolean isRunning() {
        System.out.println("Foo2======isRunning======");
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        System.out.println("Foo2======isAutoStartup======");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("Foo2======stop callback======");
        callback.run();
        isRunning = false;

    }

    /**
     * 相位决定bean之间的start顺序,按照0,1,2,3...顺序
     *
     * @return
     */
    //@Override
    public int getPhase() {
        System.out.println("Foo2======getPhase======");
        return 0;
    }
}
