package com.geekluxun.greateapp.jdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by luxun on 2017/12/22.
 */
public class CollectionDemo
{
    public static void main(String[] argc){
        CollectionDemo demo = new CollectionDemo();
        demo.testMap();
    }

    private void testMap(){
        Map map = new HashMap();
        map.put("key1", 1);
        map.put("key1", 3);
        map.put("key2", 1);
        Set set = map.entrySet();
        System.out.println(set);
    }
}

