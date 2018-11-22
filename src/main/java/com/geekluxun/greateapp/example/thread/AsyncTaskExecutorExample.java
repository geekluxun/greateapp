package com.geekluxun.greateapp.example.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.*;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by luxun on 2017/11/21.
 */
public class AsyncTaskExecutorExample {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        MyBean bean = context.getBean(MyBean.class);
        bean.runTasks();
        bean.runTasks2();
        bean.runTask3();
        //bean.runTask4();
    }


    private static class MyBean {

        @Autowired
        private AsyncTaskExecutor executor;

        @Autowired
        private AsyncListenableTaskExecutor executor2;

        @Autowired
        @Qualifier(value = "taskExecutor3")
        private TaskExecutor executor3;


        @Autowired
        @Qualifier(value = "taskExecutor4")
        private TaskExecutor executor4;

        @Autowired
        private ListenableFutureCallback threadListenableCallback;


        public void runTasks() throws Exception {
            List<Future<?>> futureList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Future<?> future = executor.submit(getTask(i));
                futureList.add(future);
            }

            /**
             * 注意feature.get会阻塞，直到任务结束
             */
            for (Future<?> future : futureList) {
                System.out.println(future.get());
            }
        }

        /**
         * 带回调监听
         *
         * @throws Exception
         */
        public void runTasks2() throws Exception {
            for (int i = 0; i < 10; i++) {
                ListenableFuture<String> f = executor2.submitListenable(getTask(i));
                f.addCallback(threadListenableCallback);
            }
        }


        public void runTask3() throws Exception {
            for (int i = 0; i < 10; i++) {
                executor3.execute(getTask2(i));
            }
        }

        public void runTask4() throws Exception {
            for (int i = 0; i < 10; i++) {
                executor4.execute(getTask2(i));
            }
        }


        private Callable<String> getTask(int i) {
            return () -> {
                System.out.printf("running task %d. Thread: %s%n", i, Thread.currentThread().getId());
                return String.format("Task finished %d", i);
            };
        }

        private Runnable getTask2(int i) {
            return () ->
                    System.out.printf("running task %d. Thread: %s%n", i, Thread.currentThread().getId());

        }
    }

    @Configuration
    public static class MyConfig {

        @Bean
        MyBean myBean() {
            return new MyBean();
        }

        @Bean
        @Primary
        AsyncTaskExecutor taskExecutor() {
            SimpleAsyncTaskExecutor t = new SimpleAsyncTaskExecutor();
            t.setConcurrencyLimit(100); //设置并发线程数
            return t;
        }

        @Bean
        AsyncListenableTaskExecutor taskExecutor2() {
            SimpleAsyncTaskExecutor t = new SimpleAsyncTaskExecutor();
            t.setConcurrencyLimit(100);
            return t;
        }

        @Bean
        ListenableFutureCallback<String> taskCallback() {
            return new MyListenableFutureCallback();
        }

        /**
         * 这里创建了一个bean,同时用@Qualifier注解来指定名字，这样其他需要注入此bean就可以通过这个name来区别
         * @return
         */
        @Bean
        @Qualifier("taskExecutor3")
        TaskExecutor taskExecutor3() {
            ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
            t.setCorePoolSize(10);
            t.setMaxPoolSize(100);
            t.setQueueCapacity(50);
            t.setAllowCoreThreadTimeOut(true);
            t.setKeepAliveSeconds(120);
            return t;
        }

        @Bean
        @Qualifier("taskExecutor4")
        TaskExecutor taskExecutor4() {
            ConcurrentTaskExecutor t = new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3));
            t.setTaskDecorator(new TaskDecorator() {
                @Override
                public Runnable decorate(final Runnable runnable) {
                    return () -> {

                        MyTask task = (MyTask) runnable;
                        long t = System.currentTimeMillis();
                        task.run();
                        System.out.printf("time taken for task: %s , %s%n", task.getI(), (System.currentTimeMillis() - t));
                    };
                }
            });
            return t;
        }
    }

    public static class MyListenableFutureCallback implements ListenableFutureCallback<String> {
        @Override
        public void onFailure(Throwable ex) {
            ex.printStackTrace();
        }

        @Override
        public void onSuccess(String result) {
            System.out.println("success object: " + result);
        }
    }


    public class MyTask implements Runnable {

        private final int i;

        MyTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("running task %d. Thread: %s%n", i, Thread.currentThread().getName());
        }

        public int getI() {
            return i;
        }
    }
}



