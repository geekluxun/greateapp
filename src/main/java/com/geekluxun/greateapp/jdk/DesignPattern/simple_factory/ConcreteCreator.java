package com.geekluxun.greateapp.jdk.DesignPattern.simple_factory;

/**
 * Created by luxun on 2018/1/2.
 */
public class ConcreteCreator extends Creator {


    @Override
    public <T extends Product> T createProduct(Class<T> tClass) {
        Product product = null;

        try {
            product = (Product) Class.forName(tClass.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  (T)product;
    }
}
