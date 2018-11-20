package com.geekluxun.greateapp.jdk;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luxun on 2017/12/22.
 */
public class DateTimeDemo {

    public static void main(String[] argc) {
        DateTimeDemo dateTimeDemo = new DateTimeDemo();
        dateTimeDemo.testCalendar();
        dateTimeDemo.testCalendar2();
    }


    private void testCalendar() {
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();


        long ms = date.getTime();
        long ms2 = now.getTimeInMillis();

        if (ms == ms2) {
            System.out.println("毫秒数相等");
        }


        now.set(1999, 8, 31);
        date = now.getTime();

        int year = now.get(Calendar.YEAR);
        int maxMonth = now.getMaximum(Calendar.MONTH);
        System.out.println();
    }

    private void testCalendar2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");


        Calendar calendar = Calendar.getInstance();
        //month取值0-11
        calendar.set(2018, 01, 1, 0, 0, 0);
        System.out.println("时间" + format.format(calendar.getTime()));
        //当前月实际最大天数
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int minDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxYear = calendar.getActualMaximum(Calendar.YEAR);
        int maxHourOfDay = calendar.getActualMaximum(Calendar.HOUR_OF_DAY);
        int maxHour = calendar.getActualMaximum(Calendar.HOUR);
        //当前日期增加一个月 2018-01-31 增加一个月是2018-02-28
        calendar.add(Calendar.MONTH, 1);
        System.out.println("时间" + format.format(calendar.getTime()));
        System.out.println("当前月最小天:" + minDayOfMonth + "最大天:" + maxDayOfMonth);

    }
}
