package com.geekluxun.greateapp.jdk.java8.lambda;

/**
 * Created by luxun on 2018/5/7
 * <p>
 * lambda表达式测试
 */
public class WorkerInterfaceTest {

    public static void main(String[] argcs) {

        //invoke doSomeWork using Annonymous class
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Anonymous class");
            }
        });

        //invoke doSomeWork using Lambda expression
        execute(() -> System.out.println("Worker invoked using Lambda expression"));
    }


    public static void execute(WorkerInterface worker) {
        worker.doSomeWork();
    }
}
