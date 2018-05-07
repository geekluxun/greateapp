package com.geekluxun.greateapp.jdk.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by luxun on 2018/5/7.
 */
public class LambdaTest {

    public static void main(String[] argcs){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        /*
         通过lambda流的方式打印x*x 注意这里System.out::println是一个lambda表达式，两个冒号的作用
         是把普通函数转换成lambda
         */

        list.stream().map((x)->x*x).forEach(System.out::println);

        System.out.println("all numbers");
        evalute(list,(n)->true);

        System.out.println("no numbers");
        evalute(list,(n)->false);

        System.out.println("even number");
        evalute(list,(n)-> n%2 == 0);

        System.out.println("odd number");
        evalute(list, (n)-> n%2 == 1);

        System.out.println("greater than 5");
        evalute(list, (n)-> n > 5);

        test2();

    }

    public static void evalute(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n: list){
            if (predicate.test(n)){
                System.out.println(n + " ");
            }
        }
    }

    /**
     * Preditcate很适合做过滤
     */
    public static void test2(){
        List<String> words = Arrays.asList("luxu", "china", "lu13", "lx44");

        Predicate<String> startWithL = (n)->n.startsWith("lu");
        Predicate<String> fourLen = (n)->n.length() == 4;
        //and 表示同时满足两个条件
        words.stream().filter(startWithL.and(fourLen)).forEach((n)->System.out.println("满足条件的是：" + n));

    }
}

