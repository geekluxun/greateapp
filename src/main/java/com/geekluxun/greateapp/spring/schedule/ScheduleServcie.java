package com.geekluxun.greateapp.spring.schedule;

import java.util.concurrent.Future;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 14:11
 * Description:
 */
public interface ScheduleServcie {
    Future<String> doSomething4(String argc);
    Future<String> doSomething5(String argc);
}
