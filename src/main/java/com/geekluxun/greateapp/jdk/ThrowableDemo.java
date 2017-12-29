package com.geekluxun.greateapp.jdk;

/**
 * Created by luxun on 2017/12/27.
 */
public class ThrowableDemo
{
    public static void main(String[] argc){
        ThrowableDemo throwableDemo = new ThrowableDemo();
        throwableDemo.f1();
    }


    private void f1(){
        try {
            f2();
        } catch (Exception e) {
            throw new RuntimeException("aa", e); //异常链 向上传播此异常
        }
    }

    private void f2(){

        try {
            f3();
        } catch (Exception e) {
            throw new RuntimeException("bb", e); //异常链 向上传播此异常
        }
    }

    private void f3(){
        throw new IllegalArgumentException("dd");
    }

}
