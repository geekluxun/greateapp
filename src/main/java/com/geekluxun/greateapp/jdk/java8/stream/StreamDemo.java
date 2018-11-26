package com.geekluxun.greateapp.jdk.java8.stream;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * Created by luxun on 2018/5/8.
 */
public class StreamDemo {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();


    public static void main(String[] argcs) {

//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();

        test10();
    }


    public static void test1() {
        List<String> title = Arrays.asList("java", "in", "action");
        Stream<String> s = title.stream();

        s.forEach(System.out::println);
        // 流只能被遍历一次，再遍历会异常
        //s.forEach(System.out::println);

    }


    public static void test2() {
        List<Integer> number = Arrays.asList(1, 3, 5, 3, 7, 8, 6, 7, 8, 9);

        System.out.println("所有大于3的");
        //所有大于3的
        number.stream().filter((a) -> a > 3).distinct().forEach(System.out::println);
        System.out.println("所有大于3的只取前3个");
        //所有大于3的只取前3个
        number.stream().filter((a) -> a > 3).distinct().limit(3).forEach(System.out::println);
        System.out.println("所有大于3的 跳过前3个");
        //所有大于3的 跳过前3个
        number.stream().filter((a) -> a > 3).distinct().skip(3).forEach(System.out::println);

        System.out.println();

    }


    /**
     * 规约测试
     */
    public static void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> result = list.stream().reduce((a, b) -> a + b);

        int result2 = list.stream().reduce(0, (a, b) -> a + b);

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


    public static void test5() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter((a) -> a % 2 == 0);

        System.out.println("1~100中偶数个数是" + evenNumbers.count());
    }

    /**
     * 规约收集器示例
     */
    public static void test6() {
        //根据食物的热量创建一个比较器
        Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
        //对流使用maxBy收集器找到热量最大的食物
        Optional<Dish> maxCalorie = Dish.menu.stream().collect(Collectors.maxBy(comparator));

        //总和
        int sum = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        //平均
        double avg = Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));


        IntSummaryStatistics intSummaryStatistics =
                Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println("所有结果是:" + intSummaryStatistics);


        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(" "));
    }

    /**
     * 分组示例
     */
    public static void test7() {

        //Dish::getType 是一个方法引用 是lambda表达式的一种等价形式 按照食物类型分组
        Map<Dish.Type, List<Dish>> g = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));

        System.out.println("按类型分组:" + g);


        //按照热量级别分组
        Map<Dish.CaloricLevel, List<Dish>> g2 = Dish.menu.stream().collect(Collectors.groupingBy((Dish dish) -> {
            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() >= 700) return Dish.CaloricLevel.FAT;
            else return Dish.CaloricLevel.NORMAL;
        }));


        //多级分组示例 map的值又是map 类似一个树型结构 类似两个for循环，先里层后外层
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> g3 =
                Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy((Dish dish) -> {
                    if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories() >= 700) return Dish.CaloricLevel.FAT;
                    else return Dish.CaloricLevel.NORMAL;
                })));


        //分区：按是否是素食分区 true or false
        Map<Boolean, List<Dish>> partion = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));

        //得到素食分组
        List<Dish> sushi = partion.get(true);

        //通过过滤方式得到素食分组示例
        List<Dish> sushi2 = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

    }

    /**
     * 并行处理 求n个数和示例
     */
    public static void test8() {

        long sum = Stream.iterate(1L, i -> i + 1).limit(100).parallel().reduce(0L, Long::sum);
        System.out.println(sum);

    }

    /**
     * 获取处理器内核数
     */
    public static void test9() {
        System.out.println("当前CPU内核数：" + Runtime.getRuntime().availableProcessors());
    }


    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("result:" + sum);
            if (duration < fastest) fastest = duration;
        }

        return fastest;
    }

    /**
     * 各种方法求和性能测试
     */
    public static void test10() {
        System.out.println("顺序加法器：" + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + "毫秒");
        System.out.println("传统的迭代方式：" + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + "毫秒");

        System.out.println("并行方式：" + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + "毫秒");


        System.out.println("LongSteam方式：" + measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + "毫秒");
        System.out.println("LongSteam并行方式：" + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + "毫秒");


        System.out.println("产生副作用方式：" + measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + "毫秒");

        System.out.println("产生副作用并行方式：" + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000) + "毫秒");

        System.out.println("forkjoin方式：" + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000) + "毫秒");

    }


}
