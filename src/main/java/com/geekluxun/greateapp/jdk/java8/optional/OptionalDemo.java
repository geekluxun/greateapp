package com.geekluxun.greateapp.jdk.java8.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by luxun on 2018/5/7.
 */
public class OptionalDemo {

    public static void main(String[] argcs) {
        Optional<String> name = Optional.of("luxun");

        //创建没有值的Optional实例
        Optional empty = Optional.ofNullable(null);

        if (name.isPresent()) {
            System.out.println(name.get());
        }

        try {
            System.out.println(empty.get());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        //如果有值，执行lambada
        name.ifPresent((value) -> System.out.println("the length of value is " + value));


        System.out.println(name.orElse("妹纸"));
        System.out.println(empty.orElse("我是空"));


        //如果值不存在，返回默认值
        System.out.println(name.orElseGet(() -> "geek"));
        System.out.println(empty.orElseGet(() -> "我是empty默认值"));

        try {
            empty.orElseThrow(RuntimeException::new);
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }

        //lambda表达式返回值可以是任何类型，但被包装成Optional实例
        Optional<String> upcaseName = name.map((n) -> n.toUpperCase());

        upcaseName.orElse("No value found");

        //和map的区别是lambda返回值总是Optional实例
        name.flatMap((value) -> Optional.of(value.toUpperCase()));

        Optional<String> longname = name.filter((value) -> value.length() > 6);

        longname.orElse("the name is less 6");


    }
}
