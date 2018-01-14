package com.geekluxun.greateapp.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by luxun on 2018/1/13.
 */
public class CountDownLatchDemo {
    List<Future<Boolean>> resultList = new ArrayList<>();

    public static void main(String[] argc){
        CountDownLatchDemo downLatchDemo = new CountDownLatchDemo();
        downLatchDemo.exec();

    }

    private void exec(){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        resultList.add(executorService.submit(new Task1(latch)));
        resultList.add(executorService.submit(new Task2(latch)));
        resultList.add(executorService.submit(new Task3(latch)));
        executorService.submit(new Task4(latch));

    }

    class Task1 implements Callable<Boolean> {
        private CountDownLatch countDownLatch;

        public Task1(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() throws Exception {
            System.out.println("任务1开始执行");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("任务1执行完成");
            countDownLatch.countDown();
            return true;
        }
    }


    class Task2 implements Callable<Boolean> {

        private CountDownLatch countDownLatch;

        public Task2(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("任务2开始执行");
                TimeUnit.SECONDS.sleep(6);
                System.out.println("任务2执行完成");
                countDownLatch.countDown();
                return false;
            }finally {
                System.out.println("一定执行！！！");
            }

        }
    }

    class Task3 implements Callable<Boolean> {

        private CountDownLatch countDownLatch;

        public Task3(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() throws Exception {

            System.out.println("任务3开始执行");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("任务3执行完成");
            countDownLatch.countDown();
            return true;
        }
    }


    class Task4 implements Callable<Boolean> {

        private CountDownLatch countDownLatch;

        public Task4(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() throws Exception {
            System.out.println("等待3个任务执行完毕...");
            countDownLatch.await();
            for (Future<Boolean> result: resultList){
                System.out.println("任务执行结果："+ result.get());
            }
            return true;
        }
    }
}
