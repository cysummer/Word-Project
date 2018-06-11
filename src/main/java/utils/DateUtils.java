/*
 * Copyright 2009-2015 lianlianpay.com All right reserved. This software is the confidential and proprietary information
 * of lianlianpay.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with lianlianpay.com.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 实现描述：日期处理工具类
 * 
 * @author SW LIU
 */
public class DateUtils {

    private DateUtils() {

    }

    /**
     * 获取当前日期时间
     * 
     * @return java.util.Date
     */
    public static Date getCurrDateTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 获取当前日期
     * 
     * @return java.util.Date
     */
    public static Date getCurrDate() {
        Calendar now = Calendar.getInstance();
        return new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).getTime();
    }

    /**
     * 返回日期时间字符串<br>
     * 格式：yyyyMMddHHmmssSSS
     * 
     * @return
     */
    public static String getCurrDateTimeStr() {
        Calendar now = Calendar.getInstance();
        return getDateTimeStr(now.getTime(), "yyyyMMddHHmmssSSS");
    }

    /**
     * 返回当天时间字符串<br>
     * 格式：HHmmssSSS
     * 
     * @return
     */
    public static String getTimeStr() {
        Calendar now = Calendar.getInstance();
        return getDateTimeStr(now.getTime(), "HHmmssSSS");
    }

    /**
     * 返回指定格式日期时间字符串
     * 
     * @param format 格式
     * @return
     */
    public static String getCurrDateTimeStr(String format) {
        Calendar now = Calendar.getInstance();
        return getDateTimeStr(now.getTime(), format);
    }

    /**
     * 返回指定时间，指定格式日期时间字符串
     * 
     * @param date 时间
     * @param format 格式
     * @return
     */
    public static String getDateTimeStr(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将日期字符串装换成日期
     * 
     * @param str 格式：yyyy-MM-dd
     * @return
     */
    public static Date parseDate(String str) {
        return parseDate(str, "yyyy-MM-dd");
    }

    /**
     * 将日期字符串装换成日期
     * 
     * @param dateStr 日期字符串
     * @param formatStr 日期格式
     * @return
     */
    public static Date parseDate(String dateStr, String formatStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 将日期字符串装换成日期
     * 
     * @param str 格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseDateTime(String str) {
        return parseDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据定制日期返回指定天数后的日期
     * 
     * @param date 传入日期
     * @param days 指定天数
     * @return 日期
     */
    public static Date createByDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return (Date) cal.getTime();
    }

    /**
     * 根据当前日期返回指定天数后日期
     * 
     * @param days
     * @return
     */
    public static Date createByDays(int days) {
        return createByDays(getCurrDateTime(), days);
    }

    /**
     * 根据指定日期返回指定小时数后日期
     * 
     * @param date
     * @param hours
     * @return
     */
    public static Date createByHours(Date date, int hours) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR, hours);
        return ca.getTime();
    }

    /**
     * 根据当前日期返回指定小时数后日期
     * 
     * @param hours
     * @return
     */
    public static Date createByHours(int hours) {
        return createByHours(getCurrDateTime(), hours);
    }

    /**
     * 根据指定日期返回指定分钟数后日期
     * 
     * @param date
     * @param minutes
     * @return
     */
    public static Date createByMinutes(Date date, int minutes) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, minutes);
        return ca.getTime();
    }

    /**
     * 根据当前日期返回指定分钟数后日期
     * 
     * @param minutes
     * @return
     */
    public static Date createByMinutes(int minutes) {
        return createByMinutes(getCurrDateTime(), minutes);
    }

    /**
     * 根据指定日期返回指定秒数数后日期
     * 
     * @param date
     * @param seconds
     * @return
     */
    public static Date createBySeconds(Date date, int seconds) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.SECOND, seconds);
        return ca.getTime();
    }

    /**
     * 根据当前日期返回指定秒数数后日期
     * 
     * @param seconds
     * @return
     */
    public static Date createBySeconds(int seconds) {
        return createBySeconds(getCurrDateTime(), seconds);
    }

    /**
     * 获取当月第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTime();
    }

    /**
     * 获取当月最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    /**
     * 获取这个星期第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_WEEK, 1);
        return ca.getTime();
    }

    /**
     * 获取两个date的差值的月份
     * 
     * @return
     */
    public static int monthBetween(Date date1, Date date2) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date1);
        Calendar ca2 = Calendar.getInstance();
        ca2.setTime(date2);
        int result = ca2.get(Calendar.MONTH) - ca.get(Calendar.MONTH);
        int month = (ca2.get(Calendar.YEAR) - ca.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 获取date的月份
     * 
     * @return
     */
    public static int getMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取date的日
     * 
     * @return
     */
    public static int getDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取系统前一天
     * @param date
     * @return
     */
    public static Date getPreDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }



}
