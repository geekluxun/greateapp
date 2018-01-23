package com.geekluxun.greateapp.zookeeper.lock;

import com.geekluxun.greateapp.zookeeper.ZkClientService;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luxun on 2017/10/25.
 */

@Service
public class ZkLock
{
    private static final Logger logger = LoggerFactory.getLogger(ZkLock.class);

    @Autowired
    ZkClientService zkClientService;


    /**
     * 不能多个线程共享一个InterProcessMutex实例，需要每个线程有单独的InterProcessMutex实例
     * 否则无法释放锁
     * @param lockPath
     */
    public void testLock(String lockPath) throws Exception{

        InterProcessMutex mutex  = new InterProcessMutex(zkClientService.getClient(), lockPath);

        logger.info("======== 当前线程id: " + Thread.currentThread().getId());

        try {
            mutex.acquire();
        } catch (Exception e) {
            logger.error("获取锁失败！" +  e);
            throw e;
        }

        logger.info("================ 获取到了分布式锁成功！================");


        Thread.sleep(3000);

        try {
            mutex.release();
        } catch (Exception e) {
            logger.error("释放锁失败！" +  e);
            throw e;
        }

        logger.info("================ 释放了分布式锁成功！ =================");

    }
}
