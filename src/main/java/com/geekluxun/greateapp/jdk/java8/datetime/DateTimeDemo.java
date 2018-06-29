package com.geekluxun.greateapp.jdk.java8.datetime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by luxun on 2018/5/11.
 */
public class DateTimeDemo {
    
    public static void main(String[] args){
        
        test1();
        test2();
        test3();
        
        test4();
        
        test5();
        
        test6();
        
        test7();

        test8();

        test9();

        test10();
    }

    /**
     * 测试LocalDate
     */
    public static void test1(){
        LocalDate localDate = LocalDate.of(2018,5,11);
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        
        int lenOfMonth = localDate.lengthOfMonth();
        int lenOfYear =  localDate.lengthOfYear();
        //是否闰年
        boolean isLeapYear = localDate.isLeapYear();
        
        LocalDate date2 = LocalDate.parse("2018-04-30");
        
        System.out.println("date2:" + date2);
        
        LocalDate now = LocalDate.now();
        System.out.println("now date:" + now);
        System.out.println("year:" + year + "month:" + month + "day:" + day);
    }

    /**
     * 测试LocalTime
     */
    public static void test2(){
        LocalTime localTime = LocalTime.of(23,11,59);
        
        
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        int nano =  localTime.getNano();
        
        LocalTime nowTime = LocalTime.now();
        
        LocalTime time2 = LocalTime.parse("15:22:33");
        
        System.out.println("time2" + time2);
        System.out.println("nowTime:" + nowTime);
    }


    /**
     * LocalDateTime示例
     */
    public static void test3(){
        LocalDateTime localDateTime = LocalDateTime.now();
        
        LocalDateTime localDateTime1 = LocalDateTime.of(2018,3,5,12,33,55);
        
        LocalDate date = LocalDate.of(2018,11,4);
        
        LocalDateTime localDateTime2 = date.atTime(LocalTime.now());
        
        LocalDate localDate = localDateTime.toLocalDate();
        
        System.out.println("now:" + localDateTime);
        System.out.println("localDateTime1:" + localDateTime1);
        System.out.println("localDateTime2:" + localDateTime2);
        
    }

    /**
     * Instant示例
     */
    public static void test4(){
        Instant instant = Instant.ofEpochSecond(20);
        
        System.out.println();
    }

    /**
     * Duration 和 Period示例
     */
    public static void test5(){
        LocalDate localDate = LocalDate.of(2018,5,2);
        LocalDate localDate1 = LocalDate.of(2018,5,11);
        Period period = Period.between(localDate, localDate1);
        
        System.out.println(period);
        
        LocalTime localTime = LocalTime.of(11,22,33);
        LocalTime localTime1 = LocalTime.of(11,22, 44);
        
        //是后面减前面的差
        Duration duration = Duration.between(localTime1, localTime);
        
        boolean isNegative = duration.isNegative();
        LocalTime localTime2  = (LocalTime) duration.addTo(localTime1);
        //乘2
        Duration duration1 =  duration.multipliedBy(2);
        
        System.out.println("差1：" + duration.getSeconds());

        System.out.println("差2：" + duration1.getSeconds());

    }

    /**
     * 时间日期操作修改示例
     */
    public static void test6(){
        LocalDate date1 = LocalDate.of(2018,5,31);
        LocalDate date2 = date1.withYear(2014);
        LocalDate date3 = date1.withDayOfMonth(2);
        //此处月改成4月，会变成4月30日，不是4月31日，day部分也会变！！！
        LocalDate date4 = date1.withMonth(4);
        
        
        LocalDate date5 = date1.plusDays(10);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);

    }

    /**
     * 时间日期操作修改更加定制化版本
     */
    public static void  test7(){
        
        LocalDate date = LocalDate.of(2018,5,11);
        
        //下一个星期一
        LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(date1);

        //下个月的第一天
        LocalDate date2 = date.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(date2);
        
        //这个月的最后一个星期六
        LocalDate date3 = date.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));
        System.out.println(date3);
        
    }


    /**
     * 格式化示例
     */
    public static void test8(){
        LocalDate date = LocalDate.now();
        
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);//20180511
        System.out.println(s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);//2018-05-11
        System.out.println(s2);
        
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String s3 = dateTimeFormatter.format(date);
        System.out.println("s3:"+ s3);
        LocalDate date1 = LocalDate.parse(s3, dateTimeFormatter);
        System.out.println("date1:" + date1);
        
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yy", Locale.CHINA);
        String s4 = dateTimeFormatter.format(date);
        System.out.println("s4:"+ s4);
        
        
    }


    /**
     *  时区示例 
     */
    public static void test9(){
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        LocalDate date = LocalDate.now();
        ZonedDateTime zonedDateTime = date.atStartOfDay(shanghai);
        System.out.println(zonedDateTime);


        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = dateTime.atZone(tokyo);
        //打印出带时区信息的时间 2018-05-11T13:53:52.433+09:00[Asia/Tokyo]
        System.out.println(zonedDateTime1);

    }

    /**
     * 测试Calendar
     */
    public static void test10() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        SimpleDateFormat sf = new SimpleDateFormat();
        String ss = sf.format(c.getTime());
        c.add(Calendar.MONTH, 1);
        ss = sf.format(c.getTime());

        System.out.println(ss);

    }
    
    
}
