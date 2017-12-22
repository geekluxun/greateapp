package com.geekluxun.greateapp.jdk;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by luxun on 2017/12/22.
 */
public class DateTimeDemo {

    public static void main(String[] argc){
        DateTimeDemo dateTimeDemo = new DateTimeDemo();
        dateTimeDemo.testCalendar();
    }


    private void testCalendar(){
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();


        long ms = date.getTime();
        long ms2 = now.getTimeInMillis();

        if (ms == ms2){
            System.out.println("毫秒数相等");
        }


        now.set(1999,8,31);
        date = now.getTime();

        int year = now.get(Calendar.YEAR);
        int maxMonth = now.getMaximum(Calendar.MONTH);
        System.out.println();
    }
}
