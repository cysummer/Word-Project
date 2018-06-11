package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouxx on 2017/12/26 10:25 .
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static String DATE_FORMAT = "yyyyMMdd";
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 取得当前日期
     *
     * @return Date 当前日期
     */
    public static Date getCurrentDate() {
        return new Date(getCurrentDateBylong());
    }

    /**
     * 取得当前日期
     *
     * @return Date 当前日期
     */
    public static long getCurrentDateBylong() {
        return System.currentTimeMillis();
    }

    /**
     * 返回当前日期对应的默认格式的字符串
     *
     * @return String 当前日期对应的字符串
     */
    public static String getCurrentStringDate() {
        return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
    }
    /**
     * 返回当前日期对应的指定格式的字符串
     *
     * @param dateFormat - 日期格式
     * @return String 当前日期对应的字符串`
     */
    public static String getCurrentStringDate(String dateFormat) {
        return convertDate2String(getCurrentDate(), dateFormat);
    }

    /**
     * 将日期转换成指定格式的字符串
     *
     * @param date       - 要转换的日期
     * @param dateFormat - 日期格式
     * @return String 日期对应的字符串
     */
    public static String convertDate2String(Date date, String dateFormat) {
        SimpleDateFormat sdf;
        if (dateFormat != null && !dateFormat.equals("")) {
            try {
                sdf = new SimpleDateFormat(dateFormat);
            } catch (Exception e) {
                logger.error(e.getMessage());
                sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            }
        } else {
            sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        }
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    /**
     * 将字符串转换成日期
     *
     * @param stringDate - 要转换的字符串格式的日期
     * @return Date 字符串对应的日期
     */
    public static Date convertString2Date(String stringDate) {
        return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * 将字符串转换成日期
     *
     * @param stringDate - 要转换的字符串格式的日期
     * @param dateFormat - 要转换的字符串对应的日期格式
     * @return Date 字符串对应的日期
     */
    public static Date convertString2Date(String stringDate, String dateFormat) {
        SimpleDateFormat sdf;
        if (dateFormat != null && !dateFormat.equals("")) {
            try {
                sdf = new SimpleDateFormat(dateFormat);
            } catch (Exception e) {
                logger.error(e.getMessage());
                sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            }
        } else {
            sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        }
        try {
            return sdf.parse(stringDate);
        } catch (ParseException pe) {
            return new Date();
        }
    }
}
