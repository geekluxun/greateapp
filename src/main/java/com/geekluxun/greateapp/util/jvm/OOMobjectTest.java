package com.geekluxun.greateapp.util.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxun on 2017/12/2.
 */
public class OOMobjectTest {


    public static void main(String[] argc) {

        //testHeap();
        //testWaitLock();
        testDeadLcok();

    }

    /**
     * 在堆上分配
     */
    public static class OOMobject {
        public byte[] palceloader = new byte[64 * 1024];

    }


    public static void fillHeap(int num) throws InterruptedException {
        List<OOMobject> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            //稍作延时，使变化的曲线更加明显
            Thread.sleep(50);
            list.add(new OOMobject());
        }

        System.gc();

    }

    /**
     * 线程死循环显示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, "testBusyThread");
        thread.start();
    }


    /**
     * 同步等待（活锁）
     * @param lock
     */
    public static void createLcokwaitThread(final Object lock) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockwaitThread");

        thread.start();
    }


    static void testHeap(){
        try {
            fillHeap(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void testWaitLock(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
            createBusyThread();
            br.readLine();

            Object o = new Object();
            createLcokwaitThread(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 死锁等待
     */
    static class SynAddRunable implements Runnable{
        int a, b;

        public SynAddRunable(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                //如果两个同步块之间发生线程切换 会死锁
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }


    static void testDeadLcok(){
        for (int i = 0; i < 100; i++){
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }
    }

}
