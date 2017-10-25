package com.geekluxun.greateapp.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by luxun on 2017/10/25.
 */
@Component
public class ZkService {

    private static final Logger logger = LoggerFactory.getLogger(ZkService.class);

    private CuratorFramework client;


    @Value("${zookeeper.connect.url}")
    private String connect;

    private String lockPath = "/lock/1";

    @PostConstruct
    public void init(){
        logger.info("============== 开始初始化ZkService ===============");
        client = CuratorFrameworkFactory.newClient(connect, new ExponentialBackoffRetry(1000, 3));

        client.start();
    }



    public CuratorFramework getClient(){
        return this.client;
    }


}
