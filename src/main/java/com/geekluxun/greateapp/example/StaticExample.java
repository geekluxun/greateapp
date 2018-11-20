package com.geekluxun.greateapp.example;

import io.swagger.models.auth.In;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/3/8 13:20
 * Description:
 */
public class StaticExample {

    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String[] argc) {
        x--;
        System.out.println(x);
        func1();
        System.out.println(x + y++ + x);

        test();

    }


    private static void func1() {
        y = x++ + ++x;
        System.out.println(x);
        System.out.println(y);
    }

    private static void test() {
        int a = "luxun".length();
        char c = 17;

        Integer b = Integer.valueOf(10);
        Integer b2 = Integer.valueOf(11);

        System.out.println(b.equals(b2));
    }

}
