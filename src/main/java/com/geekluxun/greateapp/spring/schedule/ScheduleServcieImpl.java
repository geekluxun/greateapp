package com.geekluxun.greateapp.spring.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 14:11
 * Description:
 */
@Service(value = "ScheduleServcie")
public class ScheduleServcieImpl implements ScheduleServcie {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleServcieImpl.class);

    /**
     * 这个方法固定每隔5秒钟执行一次，是依据时间间隔，不管前一个任务是否执行结束
     */
//    @Scheduled(initialDelay = 1000, fixedRate=5000)
//    public void doSomething() {
//        logger.info("======= doSomething111 !!! =========" + Thread.currentThread().getId());
////        try {
////            TimeUnit.SECONDS.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }


    /**
     * 这个方法固定每隔5秒钟执行一次，是依据前一次执行结束后5秒
     */
//    @Scheduled(fixedDelay=5000)
//    public void doSomething2() {
//        logger.info("======= doSomething222 !!! =========" + + Thread.currentThread().getId());
////        try {
////            TimeUnit.SECONDS.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }


    /**
     * cron表达式表示的调度
     */
//    @Scheduled(cron = "0/8 * * * * ?")
//    public void doSomething3() {
//        logger.info("======= doSomething333 !!! =========" + + Thread.currentThread().getId());
////        try {
////            TimeUnit.SECONDS.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }
    @Async
    public Future<String> doSomething4(String argc) {
        logger.info("======= doSomething4 !!! =========" + +Thread.currentThread().getId());
        Random random = new Random();
        System.out.println("开始做任务4");
        long start = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务4，耗时：" + (end - start) + "毫秒");

        return new AsyncResult<String>("完成任务4");
    }


    @Async
    public Future<String> doSomething5(String argc) {
        logger.info("======= doSomething5 !!! =========" + +Thread.currentThread().getId());
        Random random = new Random();
        System.out.println("开始做任务5");
        long start = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成任务5，耗时：" + (end - start) + "毫秒");

        return new AsyncResult<String>("完成任务5");
    }
}
