package com.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yexm on 2019/1/14.
 */

public class TimeUtils {
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat FORMAT_DATA = new SimpleDateFormat("yyyy-MM-dd");
    //天时分秒毫秒的倍数
    public static final long TIME_DAY = 86400000L, TIME_HOUR = 3600000L, TIME_MINUTE = 60000L, TIME_SECOND = 1000L;

    /**
     * 获取时区
     *
     * @return
     */
    public static String getCurrentTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        String strTz = tz.getDisplayName(false, TimeZone.SHORT);
        return strTz;
    }

    public static long dateToStamp(String s) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }

    public static String formatTime(long timeMillis) {
        return formatTime(timeMillis, PATTERN);
    }

    public static long parseTime(String timeStr) {
        return parseTime(timeStr, PATTERN);
    }

    public static String formatTime(long timeMillis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String time = format.format(new Date(timeMillis));
        return time;
    }

    public static long parseTime(String timeStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date = format.parse(timeStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getHour(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }


    public static int getMinute(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int minute = calendar.get(Calendar.MINUTE);
        return minute;
    }


    public static int getSecond(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int second = calendar.get(Calendar.SECOND);
        return second;
    }


    //返回string类型的时间
    public static String getTime(long timeInMillis, DateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }



    //获取当前日期
    public static String getData() {
        return getData(System.currentTimeMillis());
    }

    //获取当前日期
    public static String getData(long data) {
        return getTime(data, FORMAT_DATA);
    }


    public static long getNestDayTime(String s) throws ParseException {
        Date parse = FORMAT_DATA.parse(s.substring(0, 10));
        return parse.getTime();
    }
}
