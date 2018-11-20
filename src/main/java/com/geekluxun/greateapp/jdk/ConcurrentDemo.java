package com.geekluxun.greateapp.jdk;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by luxun on 2017/12/28.
 */
public class ConcurrentDemo {
    private static int number;

    public static void main(String[] argc) {
        ConcurrentDemo demo = new ConcurrentDemo();
//        demo.thread1();
//        demo.thread2();
        demo.init();
    }

    private int getNumber() {
        return number++;
    }

    private void init() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(new numberTask());
        }
    }

    private class numberTask implements Runnable {
        @Override
        public void run() {
            while (true)
                System.out.println(getNumber());
        }
    }

    private void thread1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println(getNumber());
            }
        }).start();
    }


    private void thread2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println(getNumber());
            }
        }).start();
    }

}
