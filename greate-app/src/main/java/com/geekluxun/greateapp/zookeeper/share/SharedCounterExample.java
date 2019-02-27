package com.geekluxun.greateapp.zookeeper.share;

import com.geekluxun.greateapp.zookeeper.ZkClientService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by luxun on 2018/1/23.
 * 分布式计数器示例
 */
@Service
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
        // 对这个PATH结点进行监听
        SharedCount baseCount = new SharedCount(zkClientService.getClient(), PATH, 0);
        baseCount.addListener(this);
        baseCount.start();

        List<SharedCount> sharedCounts = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(QTY);


        List<Future> results = new ArrayList<>();
        for (int i = 0; i < QTY; ++i) {
            /**分布式计数器，虽然此处是5个实例，但是PATH相同，实际上共享同一个count*/
            final SharedCount sharedCount = new SharedCount(zkClientService.getClient(), PATH, 0);
            sharedCounts.add(sharedCount);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    sharedCount.start();
                    Thread.sleep(rand.nextInt(10000));
                    /**count 随机加10以内的数*/
                    System.out.println("Increment:" + sharedCount.trySetCount(sharedCount.getVersionedValue(), sharedCount.getCount() + rand.nextInt(10)));
                    return null;
                }
            };
            Future future = service.submit(task);
            results.add(future);
        }
        
        for (int i = 0; i < QTY; i++){
            results.get(0).get();
        }
        for (int i = 0; i < QTY; ++i) {
            System.out.println("sharecount" +  i + ": " + sharedCounts.get(i).getCount());
            sharedCounts.get(i).close();
        }
        baseCount.close();
    }

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        System.out.println("State changed: " + arg1.toString());
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        System.out.println("Counters value is changed to " + newCount);
    }
}