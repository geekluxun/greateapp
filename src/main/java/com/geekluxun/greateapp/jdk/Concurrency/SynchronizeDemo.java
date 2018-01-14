package com.geekluxun.greateapp.jdk.Concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by luxun on 2018/1/14.
 */
public class SynchronizeDemo {
    public static void main(String[] argc){
        SynchronizeDemo demo  = new SynchronizeDemo();

        demo.f1();
    }


    private void f1(){
        Test test = new Test();
        test.f1();
        test.f2();
    }

    private class Test{

        public synchronized void f1(){
            System.out.println("f1 start");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("f1 end");
            }
        }

        public  void f2()
        {
            System.out.println("f2 start");
        }
    }
}
