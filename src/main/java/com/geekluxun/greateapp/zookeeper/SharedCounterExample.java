package com.geekluxun.greateapp.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by luxun on 2018/1/23.
 */
@Service
@Configuration
@ComponentScan
public class SharedCounterExample implements SharedCountListener {

    @Autowired
    ZkClientService zkClientService;


    private static final int QTY = 5;
    private static final String PATH = "/examples/counter";

    public static void main(String[] args) {
        /** 直接通过注解方式注入bean 但是ZkClientService中的@value有问题，无法获取*/
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SharedCounterExample.class);
        ZkClientService zkClientService = applicationContext.getBean(ZkClientService.class);

        SharedCounterExample example = new SharedCounterExample();
        try {
            example.testShareCounter();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void testShareCounter() throws IOException, Exception {
        final Random rand = new Random();
        SharedCounterExample example = new SharedCounterExample();

        SharedCount baseCount = new SharedCount(zkClientService.getClient(), PATH, 0);
        baseCount.addListener(example);
        baseCount.start();

        List<SharedCount> examples = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(QTY);

        for (int i = 0; i < QTY; ++i) {
            final SharedCount count = new SharedCount(zkClientService.getClient(), PATH, 0);
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    count.start();
                    Thread.sleep(rand.nextInt(10000));
                    /**count 随机加10以内的数*/
                    System.out.println("Increment:" + count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
                    return null;
                }
            };
            service.submit(task);
        }


        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);

        for (int i = 0; i < QTY; ++i) {
            examples.get(i).close();
        }
        baseCount.close();
    }

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        System.out.println("State changed: " + arg1.toString());
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        System.out.println("Counter's value is changed to " + newCount);
    }
}