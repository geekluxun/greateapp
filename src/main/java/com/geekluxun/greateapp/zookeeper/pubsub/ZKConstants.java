package com.geekluxun.greateapp.zookeeper.pubsub;

/**
 * @Author luxun
 * @Date 2017/12/10 19:33
 **/
public class ZKConstants {
    public static final String zkAddress = "centos3";
    public static final int sessionTimeout = 2000;
    public static String parentPath = "/Pub-Sub";//父节点
    public static String configPath = parentPath + "/DBConfig";//存放配置信息的节点
}
