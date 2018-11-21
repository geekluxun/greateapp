package com.geekluxun.greateapp.spring.bean.lifecycle;

import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: luxun
 * @Create: 2018-11-21 22:27
 * @Description:
 * @Other:
 */
@Component
public class Foo1 implements LifecycleProcessor {

    /**
     * 这个回调的调用时机：after all objects have been instantiated and initialized
     */
    @Override
    public void onRefresh() {
       System.out.println("======Foo1======onRefresh");
    }

    @Override
    public void onClose() {
        System.out.println("======Foo1======onClose");

    }

    @Override
    public void start() {
        System.out.println("======Foo1======start");

    }

    @Override
    public void stop() {
        System.out.println("======Foo1======stop");
    }

    /**
     * 检查当前bean是否在运行
     * @return
     */
    @Override
    public boolean isRunning() {
        System.out.println("======Foo1======isRunning");
        return false;
    }
}
