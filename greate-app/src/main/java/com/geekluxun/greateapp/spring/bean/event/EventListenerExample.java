package com.geekluxun.greateapp.spring.bean.event;

import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-23 9:48
 * @Description: 这个事件监听器是对容器的生命周期进行监听
 * @Other:
 */
@Component
public class EventListenerExample {

    /**
     * 容器开始
     *
     * @param startedEvent
     */
    @EventListener({ContextStartedEvent.class})
    public void handleStartEvent(ContextStartedEvent startedEvent) {
        System.out.println("容器开始event发生时间:" + startedEvent.getTimestamp());
    }

    /**
     * 容器刷新（所有单例bean都被创建完后）
     *
     * @param refreshedEvent
     */
    @EventListener({ContextRefreshedEvent.class})
    public void handleRefreshEvent(ContextRefreshedEvent refreshedEvent) {
        System.out.println("容器refresh event发生时间:" + refreshedEvent.getTimestamp());
    }

    /**
     * 容器停止(此时所有bean收到close信号,开始准备销毁)
     *
     * @param stoppedEvent
     */
    @EventListener({ContextStoppedEvent.class})
    public void handleStopEvent(ContextStoppedEvent stoppedEvent) {
        System.out.println("容器refresh event发生时间:" + stoppedEvent.getTimestamp());
    }


    /**
     * 容器关闭（此时所有bean都被销毁）
     *
     * @param closedEvent
     */
    @EventListener({ContextClosedEvent.class})
    public void handleCloseEvent(ContextClosedEvent closedEvent) {
        System.out.println("容器close event发生时间:" + closedEvent.getTimestamp());
    }
} 
