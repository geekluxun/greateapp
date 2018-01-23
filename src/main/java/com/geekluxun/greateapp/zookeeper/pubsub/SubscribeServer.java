package com.geekluxun.greateapp.zookeeper.pubsub;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/** 从ZooKeeper中读取数据库的配置信息，并注册监听，当数据库切换时，更改数据库
 * @Author luxun
 * @Date 2017/12/10 19:22
 **/

public class SubscribeServer {


    //@Value("${zookeeper.connect.url}")
    private static String connect = "116.62.63.81:2181";

    private static DBConfig dbConfig;
    private static CuratorFramework client;
    private static NodeCache nodeCache = null;

    public static void main(String[] args) throws InterruptedException {
        init();
        subscribeInfo();
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 初始化操作
     */
    public static void init() {
        client = CuratorFrameworkFactory.newClient(connect, new ExponentialBackoffRetry(1000, 3));
        client.start();

    }


    /**
     * 反序列化得到数据库内容
     */
    public static void unSerialize() {
        System.out.println("读取ZooKeeper服务器数据库信息。。。。。。");
        byte[] data = new byte[0];
        try {
            data = client.getData().forPath(ZKConstants.configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbConfig = JSON.parseObject(data, DBConfig.class);
        System.out.println("ZooKeeper中的数据为：" + dbConfig.toString());
    }


    /**
     * 订阅ZooKeeper中的信息，也就是设置监听,如果ZooKeeper中没有对应的信息的话，就读取本地的数据库信息
     */
    public static void subscribeInfo() {
        nodeCache = new NodeCache(client, ZKConstants.configPath);
        try {
            nodeCache.start(true);
            if (nodeCache.getCurrentData() != null) {
                if (!(new String(nodeCache.getCurrentData().getData()).equals(""))) {
                    unSerialize();
                }
            }
            //设置监听
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("数据库节点信息发生变化，读取新的数据库信息！");
                    unSerialize();//反序列化得到信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消订阅
     */
    public static void unSubscribeInfo() {
        if (nodeCache != null) {
            try {
                System.out.println("取消订阅！");
                nodeCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
