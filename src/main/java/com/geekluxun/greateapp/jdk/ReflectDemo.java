package com.geekluxun.greateapp.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by luxun on 2018/1/2.
 */
public class ReflectDemo {
    public static void main(String[] argc) {
        ReflectDemo reflectDemo = new ReflectDemo();
        reflectDemo.demo1();

    }


    private void demo1() {
        Class[] paramTypes = {String.class};
        Class c1 = C1.class;
        Constructor constructor = null;
        try {
            constructor = c1.getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        C1 object = null;
        try {
            object = (C1) constructor.newInstance("lxuun");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        object.f1();
    }
}

class C1 {
    private String filed1;

    public C1(String filed1) {
        this.filed1 = filed1;
    }

    public void f1() {
        System.out.println("filed1:" + filed1);
    }
}
