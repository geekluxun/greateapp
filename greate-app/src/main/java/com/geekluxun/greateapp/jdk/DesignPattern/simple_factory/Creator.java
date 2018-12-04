package com.geekluxun.greateapp.jdk.DesignPattern.simple_factory;

/**
 * Created by luxun on 2018/1/2.
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> tClass);
}
