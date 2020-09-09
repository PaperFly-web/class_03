package com.paperfly.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
    public static Date getAfterDateByNum(int n){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, n);
        String day = sdf2.format(calendar.getTime());
        return DateUtil.parse(day,"yyyy-MM-dd");
    }
    /**
     * 获取时间，精确到天
     */
    public static Date getDay(Date day){
        String format = dateToString(day);
        day = DateUtil.parse(format, "yyyy-MM-dd");
        return day;
    }

    /**
     * 把日期转换成字符串模式 yyyy-MM-dd  精确到天
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        String format = DateUtil.format(date, "yyyy-MM-dd");
        return format;
    }


    /**
     * 获取指定日期中的月份的  第一天日期
     * @param date
     * @return
     */
    public static Date getDateMonth(Date date){
        DateTime of = DateTime.of(date);
        int month = of.month();
        int year = of.year();
        DateTime parse=null;
        if(month>=9){
            parse= DateUtil.parse(year + "-" + (month + 1)+"-01");
        }else {
            parse= DateUtil.parse(year + "-0" + (month + 1)+"-01");
        }
        return parse;
    }

    /**
     * 获取指定日期  前  n   或者  后  n个  月份第一天的日期
     * @param date
     * @param n
     * @return
     */
    public static Date getDateMonth(Date date,int n){
        DateTime of = DateTime.of(date);
        int month = of.month()+n;
        int year = of.year();
        DateTime parse=null;
        if(month>=9){
            parse= DateUtil.parse(year + "-" + (month + 1)+"-01");
        }else if(month>=1&&month<9){
            parse= DateUtil.parse(year + "-0" + (month + 1)+"-01");
        }else {
            return null;
        }
        return parse;
    }

    /**
     * 获取指定日期的年份  第一天的日期
     * @param date
     * @return
     */
    public static Date getDateYear(Date date){
        DateTime of = DateTime.of(date);
        int year = of.year();
        DateTime parse=null;

        parse= DateUtil.parse(year + "-01" +"-01");

        return parse;
    }

    /**
     * 获取个日期前  n   或者后  n个  年份中第一天的日期
     * @param date
     * @param n
     * @return
     */
    public static Date getDateYear(Date date,int n){
        DateTime of = DateTime.of(date);
        int year = of.year()+n;
        DateTime parse=null;

        parse= DateUtil.parse(year + "-01" +"-01");

        return parse;
    }
}
