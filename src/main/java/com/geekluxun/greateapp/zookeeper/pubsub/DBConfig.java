package com.geekluxun.greateapp.zookeeper.pubsub;

/**
 * Created by luxun on 2018/01/23
 */
public class DBConfig {

    private String url;
    private String driver;
    private String username;
    private String password;

    public DBConfig() {
    }

    public DBConfig(String url, String driver, String name, String password) {
        this.url = url;
        this.driver = driver;
        this.username = name;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DRIVER:" + driver + ",URL:" + url + ",USERNAME:" + username + ",PASSWORD:" + password;
    }
}
