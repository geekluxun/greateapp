package com.geekluxun.greateapp.jdk.DesignPattern.simple_factory;

/**
 * Created by luxun on 2018/1/2.
 */
public class ConcreteProduct extends Product {
    @Override
    public void sayHello() {
        System.out.println("hello!!!");
    }
}
