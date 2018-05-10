package com.geekluxun.greateapp.jdk.java8.stream;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by luxun on 2018/5/10.
 * 求和各种方法对比
 */
public class ParallelStreams {

    /**
     * 普通的java8之前方式
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 顺序流方式
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    /**
     * 并行流方式 这里有装箱拆箱所以可能很慢
     * @param 
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    /**
     * range方式 此处使用原始Long流，没有装箱拆箱开销，应该比较快
     * @param n
     * @return
     */
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    /**
     * range并行方式
     * @param n
     * @return
     */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    /**
     * 有共享变量 存在 实质上是顺序的！！！
     * @param n
     * @return
     */
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 并行流对共享变量accumulator产生竞争，导致结果不对！！！
     * @param n
     * @return
     */
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
    
    
}
