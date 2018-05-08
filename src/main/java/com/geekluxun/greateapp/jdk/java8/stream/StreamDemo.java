package com.geekluxun.greateapp.jdk.java8.stream;

import com.geekluxun.greateapp.annotation.CheckCase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by luxun on 2018/5/8.
 */
public class StreamDemo {

    public static void main(String[] argcs){

        test1();
        test2();
        test3();
        test4();
        test5();
    }


    public static void test1(){
        List<String>  title = Arrays.asList("java", "in", "action");
        Stream<String>  s = title.stream();

        s.forEach(System.out::println);
        // 流只能被遍历一次，再遍历会异常
        //s.forEach(System.out::println);

    }


    public static void test2(){
        List<Integer> number = Arrays.asList(1, 3, 5, 3, 7, 8, 6, 7, 8, 9);

        System.out.println("所有大于3的");
        //所有大于3的
        number.stream().filter((a)-> a > 3).distinct().forEach(System.out::println);
        System.out.println("所有大于3的只取前3个");
        //所有大于3的只取前3个
        number.stream().filter((a)-> a > 3).distinct().limit(3).forEach(System.out::println);
        System.out.println("所有大于3的 跳过前3个");
        //所有大于3的 跳过前3个
        number.stream().filter((a)-> a > 3).distinct().skip(3).forEach(System.out::println);

        System.out.println();

    }


    /**
     *  规约测试
     */
    public static void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        Optional<Integer> result = list.stream().reduce((a, b)-> a + b);

        int result2 = list.stream().reduce(0, (a,b)->a + b);

        Optional result3 = list.stream().reduce(Integer::sum);

        Optional max = list.stream().reduce(Integer::max);

        System.out.println(result);

    }


    public static void test4() {

        //计算所有菜肴的卡路里，但存在Integer拆箱开销！！！
        int sumCalories = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

        //先映射成IntSteam在计算，就不存在拆箱开销！！！
        int sumCalories2 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();

    }


    public static void test5(){
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter((a)-> a % 2 == 0);

        System.out.println("1~100中偶数个数是" + evenNumbers.count());
    }


}
