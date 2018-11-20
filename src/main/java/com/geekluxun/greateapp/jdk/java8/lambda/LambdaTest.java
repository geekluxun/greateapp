package com.geekluxun.greateapp.jdk.java8.lambda;

import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Created by luxun on 2018/5/7.
 */
public class LambdaTest {

    public static void main(String[] argcs) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        /*
         通过lambda流的方式打印x*x 注意这里System.out::println是一个lambda表达式，两个冒号的作用
         是把普通函数转换成lambda
         */

        list.stream().map((x) -> x * x).forEach(System.out::println);

        System.out.println("all numbers");
        evalute(list, (n) -> true);

        System.out.println("no numbers");
        evalute(list, (n) -> false);

        System.out.println("even number");
        evalute(list, (n) -> n % 2 == 0);

        System.out.println("odd number");
        evalute(list, (n) -> n % 2 == 1);

        System.out.println("greater than 5");
        evalute(list, (n) -> n > 5);

        test2();

        test5();

        test6();

    }

    public static void evalute(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    /**
     * Preditcate很适合做过滤
     */
    public static void test2() {
        List<String> words = Arrays.asList("luxu", "china", "lu13", "lx44");

        Predicate<String> startWithL = (n) -> n.startsWith("lu");
        Predicate<String> fourLen = (n) -> n.length() == 4;
        //and 表示同时满足两个条件
        words.stream().filter(startWithL.and(fourLen)).forEach((n) -> System.out.println("满足条件的是：" + n));

    }


    /**
     * java8提供的函数式接口和lambda表达式对应示例
     */
    public static void test3() {
        //T->R
        Function<T, String> function = (T) -> "luxun";

        //(int, int)->int
        //方式1 会存在自动装箱开销 因为java中的泛型只能是引用类型，不能是原始类型
        BiFunction<Integer, Integer, Integer> function1 = (Integer a, Integer b) -> a * b;
        //方式2 不存在自动装箱开销
        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;

        // T -> void
        Consumer<T> consumer = (T) -> System.out.println(T);

        //()-> T
        Supplier<Integer> supplier = () -> 3;


    }

    //lambda表达式中可以引用外部环境中的变量，但必须是final或事实上的final
    int port2 = 1000;

    public void test4() {

        int port1 = 8800;
        //使用局部变量
        Runnable r = () -> System.out.println(port1);
        //使用类实例变量 此处不变的是指this
        Runnable r2 = () -> System.out.println(port2);

    }


    /**
     * lambda表达式和等效的方法引用
     */
    public static void test5() {
        Function<String, Integer> stirng2Integer = (String s) -> Integer.parseInt(s);
        int a = stirng2Integer.apply("100");

        //lambda表达式调用静态方法
        Function<String, Integer> string2Integer2 = Integer::parseInt;

        int b = string2Integer2.apply("33");


        BiPredicate<List<String>, String> contains1 = (list, element) -> list.contains(element);

        boolean result = contains1.test(Arrays.asList("luxun", "lu", "xun"), "lu");

        BiPredicate<List<String>, String> constains2 = List::contains;


        boolean result2 = contains1.test(Arrays.asList("luxun", "lu", "xun"), "lu");

        System.out.print("end");
    }

    /**
     * 比较器复合示例
     */
    public static void test6() {
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);

        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        //按重量逆序 Apple::getWeight是一种根据已有方法构造方法引用，方法引用是lamdba表达式的一种快捷写法
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        //按重量逆序，如果重量相同，再按颜色排序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));


        Function<Integer, Integer> f = (x) -> x + 1;
        Function<Integer, Integer> g = (x) -> x * 3;


        //两个函数的复合，先调用f,f的返回作为g的入参传给g
        //g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(5);

        //f(g(x)
        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(5);

        System.out.println(result + result2);

    }


    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}




