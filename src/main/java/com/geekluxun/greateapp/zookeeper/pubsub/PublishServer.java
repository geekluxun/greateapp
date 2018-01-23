package com.geekluxun.greateapp.zookeeper.pubsub;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 使用ZooKeeper发布消息
 *
 * @Author luxun
 * @Date 2017/01/23 17:45
 **/

public class PublishServer {


    //@Value("${zookeeper.connect.url}")
    private static String connect = "116.62.63.81:2181";

    private static DBConfig dbConfig;
    private static CuratorFramework client;

    public static void main(String[] args) {
        init();
        readConfig();
        publishInfo();
    }


    /**
     * 创建客户端，并且如果父节点不存在则创建父节点和配置信息的节点
     */
    public static void init() {
        client = CuratorFrameworkFactory.newClient(connect, new ExponentialBackoffRetry(1000, 3));
        client.start();

        try {
            if (client.checkExists().forPath(ZKConstants.parentPath) == null) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ZKConstants.parentPath);
            }
            if (client.checkExists().forPath(ZKConstants.configPath) == null) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ZKConstants.configPath, "".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取配置文件到一个DBConfig对象中。
     */
    public static void readConfig() {
        BufferedReader reader = null;//加载文件流
        try {
            System.out.println(System.getProperty("user.dir"));
            reader = new BufferedReader(new FileReader("src/main/resources/dev/application.properties"));
            Properties prop = new Properties();//创建属性操作对象
            prop.load(reader);//加载流
            dbConfig = new DBConfig(prop.getProperty("db.jdbcUrl"), prop.getProperty("db.driverClass"), prop.getProperty("db.username"), prop.getProperty("db.password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将DBConfig使用Kryo序列化之后发布到ZooKeeper节点中
     */
    public static void publishInfo() {
        try {
            client.setData().forPath(ZKConstants.configPath, JSON.toJSONString(dbConfig).getBytes());//添加到节点中
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}