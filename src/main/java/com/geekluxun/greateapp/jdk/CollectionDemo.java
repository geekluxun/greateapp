package com.geekluxun.greateapp.jdk;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by luxun on 2017/12/22.
 */
public class CollectionDemo {
    public static void main(String[] argc) {
        BigDecimal totalAmount = new BigDecimal("52345");
        BigDecimal totalAmount2 = new BigDecimal("-3.15");
        BigDecimal totalAmount3 = new BigDecimal(0.1);
        totalAmount = totalAmount.add(totalAmount2);
        CollectionDemo demo = new CollectionDemo();
        demo.testMap();
        demo.testList();

    }

    private void testMap() {
        Map map = new HashMap();
        map.put("key1", 1);
        map.put("key1", 3);
        map.put("key2", 1);
        Set set = map.entrySet();
        System.out.println(set);
    }

    private void testList() {
        List<String> list = new ArrayList<>();
        String a = "aa";
        String b = "bb";
        list.add(a);
        list.add(b);

        a = "cc";

        List<Integer> list1 = new ArrayList<>();
        Integer i1 = Integer.valueOf(11);
        list1.add(i1);
        i1 = 3;
        System.out.println();
    }

    private class d1 {
    }

    private class d2 {
    }
}



