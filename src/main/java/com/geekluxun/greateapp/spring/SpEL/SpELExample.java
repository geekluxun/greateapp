package com.geekluxun.greateapp.spring.SpEL;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/30 15:44
 * Description: spring 表达式语言实例
 */
public class SpELExample {

    public static void main(String[] argc) {
        SpELExample elExample = new SpELExample();
        elExample.test();
    }


    public void test() {
        ExpressionParser parser = new SpelExpressionParser();

        //字符串
        String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
        String str2 = parser.parseExpression("\"Hello World!\"").getValue(String.class);

        //数字类型
        int int1 = parser.parseExpression("1").getValue(Integer.class);
        long long1 = parser.parseExpression("-1L").getValue(long.class);
        float float1 = parser.parseExpression("1.1").getValue(Float.class);
        double double1 = parser.parseExpression("1.1E+2").getValue(double.class);
        int hex1 = parser.parseExpression("0xa").getValue(Integer.class);
        long hex2 = parser.parseExpression("0xaL").getValue(long.class);


        //布尔型

        boolean true1 = parser.parseExpression("true").getValue(boolean.class);
        boolean false1 = parser.parseExpression("false").getValue(boolean.class);

        //null
        Object null1 = parser.parseExpression("null").getValue(Object.class);


        //加减乘除
        int result1 = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);//-3


        //求余
        int result2 = parser.parseExpression("4%3").getValue(Integer.class);//1

        //幂运算
        int result3 = parser.parseExpression("2^3").getValue(Integer.class);//8


        //SpEL同样提供了等价的“EQ” 、“NE”、 “GT”、“GE”、 “LT” 、“LE”来表示等于、不等于、大于、大于等于、小于、小于等于，不区分大小写。


        //逻辑表达式
        String expression1 = "2>1 and (!true or !false)";
        boolean result4 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result4);

        String expression2 = "2>1 and (NOT true or NOT false)";
        boolean result5 = parser.parseExpression(expression2).getValue(boolean.class);
        Assert.assertEquals(true, result5);


        //正则表达式
        String expression13 = "'123' matches '\\d{3}";
        boolean result6 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result6);


        System.out.println();

    }


    /**
     * 类类型表达式
     */
    @Test
    public void testClassTypeExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //java.lang包类访问
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        Assert.assertEquals(String.class, result1);
        //其他包类访问
        String expression2 = "T(com.geekluxun.greateapp.spring.SpEL.SpELExample)";
        Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
        Assert.assertEquals(SpELExample.class, result2);
        //类静态字段访问
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        Assert.assertEquals(Integer.MAX_VALUE, result3);
        //类静态方法调用
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        Assert.assertEquals(1, result4);
    }


    /**
     * 类实例化表达式
     */
    @Test
    public void testConstructorExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("new String('haha')").getValue(String.class);
        Assert.assertEquals("haha", result1);
        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        Assert.assertNotNull(result2);
    }


    /**
     * 变量定义及引用
     */
    @Test
    public void testVariableExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("variable", "haha");
        context.setVariable("variable", "haha");
        String result1 = parser.parseExpression("#variable").getValue(context, String.class);
        Assert.assertEquals("haha", result1);

        context = new StandardEvaluationContext("haha");
        String result2 = parser.parseExpression("#root").getValue(context, String.class);
        Assert.assertEquals("haha", result2);
        String result3 = parser.parseExpression("#this").getValue(context, String.class);
        Assert.assertEquals("haha", result3);
    }

    /**
     * 自定义函数
     *
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @Test
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt", parseInt);
        context.setVariable("parseInt2", parseInt);
        String expression1 = "#parseInt('3') == #parseInt2('3')";
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);
        Assert.assertEquals(true, result1);
    }


    /**
     * 赋值表达式
     */
    @Test
    public void testAssignExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //1.给root对象赋值
        EvaluationContext context = new StandardEvaluationContext("aaaa");
        String result1 = parser.parseExpression("#root='aaaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaaa", result1);
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaa", result2);

        //2.给自定义变量赋值
        context.setVariable("#variable", "variable");
        String result3 = parser.parseExpression("#variable=#root").getValue(context, String.class);
        Assert.assertEquals("aaaa", result3);
    }


    /**
     * 对象属性存取
     */
    @Test
    public void test4() {
        ExpressionParser parser = new SpelExpressionParser();
//1.访问root对象属性
        Date date = new Date();
        StandardEvaluationContext context = new StandardEvaluationContext(date);
        int result1 = parser.parseExpression("Year").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result1);
        int result2 = parser.parseExpression("year").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result2);


        //2.安全访问
        context.setRootObject(null);
        Object result3 = parser.parseExpression("#root?.year").getValue(context, Object.class);
        Assert.assertEquals(null, result3);

        //3.给root对象属性赋值
        context.setRootObject(date);
        int result4 = parser.parseExpression("Year = 4").getValue(context, int.class);
        Assert.assertEquals(4, result4);
        parser.parseExpression("Year").setValue(context, 5);
        int result5 = parser.parseExpression("Year").getValue(context, int.class);
        Assert.assertEquals(5, result5);

    }


    /**
     * 对象方法调用
     */
    @Test
    public void test5() {
        ExpressionParser parser = new SpelExpressionParser();
        Date date = new Date();
        StandardEvaluationContext context = new StandardEvaluationContext(date);
        int result2 = parser.parseExpression("getYear()").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result2);
    }


    /**
     * Bean引用
     */
    @Test
    public void testBeanExpression() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(ctx));
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);
        Assert.assertEquals(System.getProperties(), result1);
    }

    /**
     * 集合表达式
     */
    @Test
    public void testCollection() {
        ExpressionParser parser = new SpelExpressionParser();

        //将返回不可修改的空List
        List<Integer> result2 = parser.parseExpression("{}").getValue(List.class);


        //对于字面量列表也将返回不可修改的List
        List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(List.class);
        Assert.assertEquals(new Integer(1), result1.get(0));
        try {
            result1.set(0, 2);
            //不可能执行到这，对于字面量列表不可修改
            Assert.fail();
        } catch (Exception e) {
        }


        //对于列表中只要有一个不是字面量表达式，将只返回原始List，
        //不会进行不可修改处理
        String expression3 = "{{1+2,2+4},{3,4+4}}";
        List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);
        result3.get(0).set(0, 1);
        Assert.assertEquals(2, result3.size());


        //声明一个大小为2的一维数组并初始化
        int[] result4 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);


        //定义一维数组但不初始化
        int[] result5 = parser.parseExpression("new int[1]").getValue(int[].class);


        //定义多维数组但不初始化
        int[][][] result6 = parser.parseExpression("new int[1][2][3]").getValue(int[][][].class);


        //错误的定义多维数组，多维数组不能初始化
        String expression4 = "new int[1][2][3]{{1}{2}{3}}";
        try {
            int[][][] result7 = parser.parseExpression(expression4).getValue(int[][][].class);
            Assert.fail();
        } catch (Exception e) {
        }


        //SpEL内联List访问
        int result8 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);//即list.get(0)
        Assert.assertEquals(1, result8);


        //SpEL目前支持所有集合类型的访问
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(1);
        collection.add(2);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("collection", collection);
        int result9 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
//对于任何集合类型通过Iterator来定位元素
        Assert.assertEquals(2, result9);


        //SpEL对Map字典元素访问的支持
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        EvaluationContext context3 = new StandardEvaluationContext();
        context3.setVariable("map", map);
        int result10 = parser.parseExpression("#map['a']").getValue(context3, int.class);
        Assert.assertEquals(1, result10);


        //1.修改数组元素值
        int[] array = new int[]{1, 2};
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("array", array);
        int result11 = parser.parseExpression("#array[1] = 3").getValue(context1, int.class);
        Assert.assertEquals(3, result11);


        //2.修改集合值
        Collection<Integer> collection2 = new ArrayList<Integer>();
        collection2.add(1);
        collection2.add(2);
        EvaluationContext context4 = new StandardEvaluationContext();
        context4.setVariable("collection", collection2);
        int result12 = parser.parseExpression("#collection[1] = 3").getValue(context4, int.class);
        Assert.assertEquals(3, result12);
        parser.parseExpression("#collection[1]").setValue(context4, 4);
        result12 = parser.parseExpression("#collection[1]").getValue(context4, int.class);
        Assert.assertEquals(4, result12);


        //3.修改map元素值
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("a", 1);
        EvaluationContext context5 = new StandardEvaluationContext();
        context5.setVariable("map", map2);
        int result13 = parser.parseExpression("#map['a'] = 2").getValue(context5, int.class);
        Assert.assertEquals(2, result13);

    }

    /**
     * 集合投影
     */
    @Test
    public void test7() {
        ExpressionParser parser = new SpelExpressionParser();

        //1.首先准备测试数据
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(4);
        collection.add(5);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);


        //2.测试集合或数组
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", collection);
        Collection<Integer> result1 =
                parser.parseExpression("#collection.![#this+1]").getValue(context1, Collection.class);
        Assert.assertEquals(2, result1.size());
        Assert.assertEquals(new Integer(5), result1.iterator().next());


        //3.测试字典
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        List<Integer> result2 =
                parser.parseExpression("#map.![ value+1]").getValue(context2, List.class);
        Assert.assertEquals(2, result2.size());
    }

    /**
     * 测试集合选择
     */
    @Test
    public void test8() {

        ExpressionParser parser = new SpelExpressionParser();

        //1.首先准备测试数据
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(4);
        collection.add(5);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);


        //2.集合或数组测试
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", collection);
        Collection<Integer> result1 =
                parser.parseExpression("#collection.?[#this>4]").getValue(context1, Collection.class);
        Assert.assertEquals(1, result1.size());
        Assert.assertEquals(new Integer(5), result1.iterator().next());


        //3.字典测试
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        Map<String, Integer> result2 =
                parser.parseExpression("#map.?[#this.key != 'a']").getValue(context2, Map.class);
        Assert.assertEquals(1, result2.size());

        List<Integer> result3 =
                parser.parseExpression("#map.?[key != 'a'].![value+1]").getValue(context2, List.class);
        Assert.assertEquals(new Integer(3), result3.iterator().next());
    }

}
