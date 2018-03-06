package com.geekluxun.greateapp.redis.redisson;

import com.geekluxun.greateapp.entity.SysUser;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by luxun on 2017/10/28.
 */
public class RedisExamples {

    private RedissonClient redissonClient;



    public static void main(String[] args) throws IOException {
        // connects to 127.0.0.1:6379 by default

        RedisExamples examples = new RedisExamples();
        examples.init();

        examples.testObject();

        examples.testSemaphore();

        examples.testDelayQueue();
    }


    public void init(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://116.62.63.81:6379");

        redissonClient = Redisson.create(config);
    }


    public void destroy(){
        redissonClient.shutdown();
    }




    /**
     * 分布式map
     * @param
     */
    public  void testMap(){
        RMap<String, Integer> map =  redissonClient.getMap("myMap");
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        boolean contains = map.containsKey("a");

        Integer value = map.get("c");
        Integer updatedValue = map.addAndGet("a", 32);

        Integer valueSize = map.valueSize("c");

        Set<String> keys = new HashSet<String>();
        keys.add("a");
        keys.add("b");
        keys.add("c");
        Map<String, Integer> mapSlice = map.getAll(keys);

        // use read* methods to fetch all objects
        Set<String> allKeys = map.readAllKeySet();
        Collection<Integer> allValues = map.readAllValues();
        Set<Map.Entry<String, Integer>> allEntries = map.readAllEntrySet();

        // use fast* methods when previous value is not required
        boolean isNewKey = map.fastPut("a", 100);
        boolean isNewKeyPut = map.fastPutIfAbsent("d", 33);
        long removedAmount = map.fastRemove("b");
    }


    /**
     * 分布式Long
     */
    public  void testLong(){
        RAtomicLong atomicLong = redissonClient.getAtomicLong("myLong");
        atomicLong.getAndDecrement();
        atomicLong.getAndIncrement();

        atomicLong.addAndGet(10L);
        atomicLong.compareAndSet(29, 412);

        atomicLong.decrementAndGet();
        atomicLong.incrementAndGet();

        atomicLong.getAndAdd(302);
        atomicLong.getAndDecrement();
        atomicLong.getAndIncrement();
    }

    /**
     * 分布式对象
     * @param
     */
    public  void testObject(){
        SysUser user = new SysUser();
        user.setCreateTime(new Date());
        user.setUserName("luxun");
        user.setId(123L);

        RBucket<SysUser> bucket = redissonClient.getBucket("myUser");
        bucket.set(user);

        SysUser user2  = bucket.get();

        System.out.println(user2);
    }


    /**
     * 分布式延时队列
     */
    public void testDelayQueue(){
        RQueue<String> distinationQueue = redissonClient.getQueue("queue1");

        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(distinationQueue);
        // 10秒钟以后将消息发送到指定列队
        delayedQueue.offer("msg1", 10, TimeUnit.SECONDS);
        // 一分钟以后将消息发送到指定列队
        delayedQueue.offer("msg2", 1, TimeUnit.MINUTES);

    }


    /**
     * 分布式信号量
     */
    public void testSemaphore() {
        int count;
        RSemaphore rSemaphore = redissonClient.getSemaphore("semaphore1");
        rSemaphore.trySetPermits(5);
        count = rSemaphore.availablePermits();
        try {
            rSemaphore.acquire();
            count = rSemaphore.availablePermits();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        rSemaphore.release();
    }


    /**
     * 分布式锁
     */
    public void testLock(){
        RLock rLock = redissonClient.getLock("mylock");
        try {
            rLock.lock();
        }finally {
            rLock.unlock();
        }
    }

}
