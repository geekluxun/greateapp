package com.geekluxun.greateapp.jdk.DesignPattern.simple_factory;

/**
 * Created by luxun on 2018/1/2.
 */
public class Client {

    public static void main(String[] argc) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct.class);
        product.sayHello();
    }
}

