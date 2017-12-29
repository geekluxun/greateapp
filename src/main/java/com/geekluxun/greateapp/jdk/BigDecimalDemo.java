package com.geekluxun.greateapp.jdk;



import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by luxun on 2017/12/27.
 */
public class BigDecimalDemo {
    public static void main(String[] argc){
        BigDecimalDemo  decimalDemo = new BigDecimalDemo();
        decimalDemo.test1();
        decimalDemo.test2();
    }


    private void test1(){
        float f1 = 0.013f;
        BigDecimal a1 = new BigDecimal(f1);
        a1.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(a1);
        a1 = a1.setScale(2, BigDecimal.ROUND_HALF_UP); //setScale返回后的值才是经过指定标度和舍入过后的值！！！
        System.out.println(a1);

        BigDecimal a2 = BigDecimal.valueOf(f1);
        System.out.println(a2);
        /** 官方建议使用字符串构造器构造把float或double 转换成 BigDecimal 不会出现精度问题*/
        String s1 = Float.toString(f1);
        BigDecimal a3 = new BigDecimal(s1);

        System.out.println(a3);

        Assert.isTrue( 3 >  4, "前者必须大于后者");

    }


    float f[] = {0.010f, 2.510f, 2.515f, 2.516f, -2.510f, -2.511f, -2.515f, -2.516f};

    private void test2 () {
        DecimalFormat df = new DecimalFormat("0.00");
        /**
         * 0.010f 使用Down模式值为0.00而不是0.01  但0.011 则为0.01 特此注意！！！
         */
        //靠近零舍入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.DOWN);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_DOWN" + ":" + bg.setScale(2, BigDecimal.ROUND_DOWN));
        }

        //远离零舍入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.UP);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_UP" + ":" + bg.setScale(2, BigDecimal.ROUND_UP));
        }

        //5舍6入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.HALF_DOWN);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_HALF_DOWN" + ":" + bg.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        }

        //4舍5入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.HALF_UP);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_HALF_UP" + ":" + bg.setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        //向靠近无穷大舍入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.CEILING);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_CEILING" + ":" + bg.setScale(2, BigDecimal.ROUND_CEILING));
        }

        //向靠近无穷小舍入
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.FLOOR);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_FLOOR" + ":" + bg.setScale(2, BigDecimal.ROUND_FLOOR));
        }

        //前一位是奇数 同 HALF_UP  前一位位是偶数  同 HALF_DOWN
        for (float ff : f) {
            df.setRoundingMode(RoundingMode.HALF_EVEN);
            System.out.println(ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));

            BigDecimal bg = new BigDecimal(ff);
            System.out.println(ff + " " + "ROUND_HALF_EVEN" + ":" + bg.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }

//        for (float ff: f ) {
//            df.setRoundingMode(RoundingMode.UNNECESSARY);
//            Log.i(TAG, ff + " " + df.getRoundingMode().toString() + ":" + df.format(ff));
//        }
    }
}
