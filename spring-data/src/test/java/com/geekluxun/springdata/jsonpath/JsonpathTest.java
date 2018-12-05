package com.geekluxun.springdata.jsonpath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.geekluxun.springdata.SpringDataApplication;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 16:04
 * @Description: JSONPATH测试用例
 * @Other:
 */
public class JsonpathTest extends SpringDataApplication {


    @Test
    public void test1() throws Exception {
        Entity entity = new Entity(123, new Object());

        Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Assert.assertTrue(JSONPath.contains(entity, "$.value"));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        assertEquals(2, JSONPath.size(entity, "$"));
        //Assert.assertEquals(0, JSONPath.size(new Object[], "$"));
    }

    @Test
    public void test2() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));

        List<String> names = (List<String>) JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        Assert.assertSame(entities.get(0).getName(), names.get(0));
        Assert.assertSame(entities.get(1).getName(), names.get(1));
    }

    @Test
    public void test3() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[1,2]"); // 返回下标为1和2的元素
        assertEquals(2, result.size());
        Assert.assertSame(entities.get(1), result.get(0));
        Assert.assertSame(entities.get(2), result.get(1));
    }

    @Test
    public void test4() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));

        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[0:2]"); // 返回下标从0到2的元素
        assertEquals(3, result.size());
        Assert.assertSame(entities.get(0), result.get(0));
        Assert.assertSame(entities.get(1), result.get(1));
        Assert.assertSame(entities.get(2), result.get(1));
    }

    @Test
    public void test5() {
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));

        List<Object> result = (List<Object>) JSONPath.eval(entities, "[id in (1001)]");
        assertEquals(1, result.size());
        Assert.assertSame(entities.get(0), result.get(0));
    }

    @Test
    public void test6() {
        Entity entity = new Entity(1001, "ljw2083");
        Assert.assertSame(entity, JSONPath.eval(entity, "[id = 1001]"));
        Assert.assertNull(JSONPath.eval(entity, "[id = 1002]"));

        JSONPath.set(entity, "id", 123456); //将id字段修改为123456
        assertEquals(123456, entity.getId().intValue());

        JSONPath.set(entity, "value", new int[0]); //将value字段赋值为长度为0的数组
        JSONPath.arrayAdd(entity, "value", 1, 2, 3); //将value字段的数组添加元素1,2,3
    }

    @Test
    public void test7() {
        Map root = Collections.singletonMap("company", //
                Collections.singletonMap("departs", //
                        Arrays.asList( //
                                Collections.singletonMap("id", 1001), //
                                Collections.singletonMap("id", 1002), //
                                Collections.singletonMap("id", 1003) //
                        ) //
                ));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        assertEquals(3, ids.size());
        assertEquals(1001, ids.get(0));
        assertEquals(1002, ids.get(1));
        assertEquals(1003, ids.get(2));
    }

    @Test
    public void test9() {
        Entity e = new Entity();
        e.setId(null);
        e.setName("hello");
        Map<String, Entity> map = Collections.singletonMap("e", e);
        Collection<String> result;

        // id is null, excluded by keySet
        result = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        assertEquals(1, result.size());
        Assert.assertTrue(result.contains("name"));

        e.setId(1);
        result = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains("id")); // included
        Assert.assertTrue(result.contains("name"));

        // Same result
        Assert.assertEquals(result, JSONPath.keySet(map, "$.e"));
        Assert.assertEquals(result, new JSONPath("$.e").keySet(map));
    }


    @Test
    public void test10() {
        String json = JSON.toJSONString(new Entity(Integer.valueOf(1), "luxun"));
        String name = (String) JSONPath.read(json, "$.name");
        Assert.assertTrue(name.equals("luxun"));
    }


    public static class Entity {
        private Integer id;
        private String name;
        private Object value;

        public Entity() {
        }

        public Entity(Integer id, Object value) {
            this.id = id;
            this.value = value;
        }

        public Entity(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Entity(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public Object getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
