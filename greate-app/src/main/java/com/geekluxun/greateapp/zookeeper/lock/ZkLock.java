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
public class ZkLock {
    private static final Logger logger = LoggerFactory.getLogger(ZkLock.class);

    @Autowired
    ZkClientService zkClientService;


    /**
     * 不能多个线程共享一个InterProcessMutex实例，需要每个线程有单独的InterProcessMutex实例
     * 否则无法释放锁
     *
     * @param lockPath
     */
    public void testLock(String lockPath) throws Exception {

        /**
         * 是一个公平锁，通过结点的顺序wacher机制实现,zookeeper保证只有一个客户端在path上创建结点
         */
        InterProcessMutex mutex = new InterProcessMutex(zkClientService.getClient(), lockPath);

        logger.info("======== 当前线程id: " + Thread.currentThread().getId());

        try {
            mutex.acquire();
            logger.info("================ 获取到了分布式锁成功！================");
            Thread.sleep(3000);

        } finally {
            mutex.release();
            logger.info("================ 释放了分布式锁成功！ =================");
        }
    }
    
}
