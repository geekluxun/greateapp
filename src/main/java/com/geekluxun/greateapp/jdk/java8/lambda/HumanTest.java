package com.geekluxun.greateapp.jdk.java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Created by luxun on 2018/5/7.
 */
public class HumanTest {

    public static void main(String[] argcs) {

        List<Human> list = Lists.newArrayList(new Human("luxun", 30),
            new Human("mark", 3),
            new Human( "alen", 10));




//        sort1(list);
//        sort2(list);
        //sort3(list);
        //sort4(list);
        reverse(list);

        Assert.assertThat(list.get(0), equalTo(new Human("alen", 10)));
        Assert.assertThat("luxun", equalTo("luxun"));

    }


    /**
     * 方式1
     * @param list
     */
    private static void sort1(List<Human> list){
        Collections.sort(list, new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    /**
     * 方式2 lambda方式排序
     * @param list
     */
    private static void sort2(List<Human> list){
        list.sort((Human h1, Human h2)->h1.getName().compareTo(h2.getName()));
    }


    /**
     * 方式3 lambda方法引用
     */
    private static void sort3(List<Human> list){
        list.sort(Human::compareByNameThenAge);
    }


    /**
     * 方式4
     * @param list
     */
    private static void sort4(List<Human> list) {
        Collections.sort(list,Comparator.comparing(Human::getName));

    }


    /**
     * 反转排序
     * @param list
     */
    private static void reverse(List<Human> list){
        Comparator<Human> comparator = (h1, h2)-> h1.getName().compareTo(h2.getName());

        list.sort(comparator.reversed());
    }
}