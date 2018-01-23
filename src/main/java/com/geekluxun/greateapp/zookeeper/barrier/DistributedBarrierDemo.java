package com.geekluxun.greateapp.zookeeper.barrier;

import com.geekluxun.greateapp.zookeeper.ZkClientService;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by luxun on 2018/1/23.
 */
@Service
public class DistributedBarrierDemo {

    private static final Logger logger = LoggerFactory.getLogger(DistributedBarrierDemo.class);

    @Autowired
    ZkClientService zkClientService;



    public void test(){
        testBarrier();
        testDoubleBarrier();
    }


    public void testBarrier() {
        DistributedBarrier barrier = new DistributedBarrier(zkClientService.getClient(), "/barrier");
        try {
            //设置栅栏
            barrier.setBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++)
            executorService.execute(new Task1(barrier));


        try {
            //10秒后移除栅栏
            TimeUnit.SECONDS.sleep(10);
            barrier.removeBarrier();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("======barrieer=========");
    }


    public void testDoubleBarrier() {


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(zkClientService.getClient(), "/barrier2", 5);
            executorService.execute(new Task2(barrier));
        }


        logger.info("======barrieer=========");
    }

    private class Task1 implements Runnable {
        private DistributedBarrier barrier;

        public Task1(DistributedBarrier barrier) {
            this.barrier = barrier;
        }


        @Override
        public void run() {
            try {
                //等待栅栏移除开始任务
                barrier.waitOnBarrier();
                //模拟任务执行
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

            System.out.println("任务完成：" + Thread.currentThread().getId());
        }
    }


    private class Task2 implements Runnable {
        private DistributedDoubleBarrier barrier;

        public Task2(DistributedDoubleBarrier barrier) {
            this.barrier = barrier;
        }


        @Override
        public void run() {
            try {
                //等待所有任务开始，否则阻塞
                barrier.enter();
                //模拟任务执行
                TimeUnit.SECONDS.sleep(5);
                //等待所有任务结束，否则阻塞
                barrier.leave();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

            System.out.println("任务完成：" + Thread.currentThread().getId());
        }
    }

}
