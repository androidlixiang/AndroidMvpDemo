package com.lixiang.androidmvpdemp.utils;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;

import com.common.util.LogUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/9 11:21
 */
public class CalendarRemindUtlis {
    String[] a = new String[]{
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.READ_CALENDAR
    };

    private static String CALENDER_URL = "content://com.android.calendar/calendars";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

    private static String CALENDARS_NAME = "boohee";
    private static String CALENDARS_ACCOUNT_NAME = "BOOHEE@boohee.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.boohee";
    private static String CALENDARS_DISPLAY_NAME = "BOOHEE账户";

    /**
     * 检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
     * 获取账户成功返回账户id，否则返回-1
     */
    public static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }

    /**
     * 检查是否存在现有账户，存在则返回账户id，否则返回-1
     */
    private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALENDER_URL), null, null, null, null);
        try {
            if (userCursor == null) { //查询返回空值
                return -1;
            }
            int count = userCursor.getCount();
            if (count > 0) { //存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }

    /**
     * 添加日历账户，账户创建成功则返回账户id，否则返回-1
     */
    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(CALENDER_URL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }

    /**
     * 添加日历事件
     */
    public static boolean addCalendarEvent(Context context, String description, long reminderTime, String repetitionWeek) {
        if (context == null) {
            return false;
        }
        int calId = checkAndAddCalendarAccount(context); //获取日历账户的id
        if (calId < 0) { //获取账户id失败直接返回，添加日历事件失败
            return false;
        }
        try {
            ContentValues event = new ContentValues();
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.logTest(currentTimeMillis + "<-------------");

            event.put(CalendarContract.Events.TITLE, currentTimeMillis + "");
            event.put(CalendarContract.Events.DESCRIPTION, description);
            event.put(CalendarContract.Events.CALENDAR_ID, calId); //插入账户的id
            event.put(CalendarContract.Events.DTSTART, reminderTime);
            event.put(CalendarContract.Events.DTEND, reminderTime + 30 * 1000);//30S
            TimeZone tz = TimeZone.getDefault(); // 获取默认时区
            event.put(CalendarContract.Events.EVENT_TIMEZONE, tz.getID());
            event.put(CalendarContract.Events.EVENT_LOCATION, "小康");

            StringBuilder week = new StringBuilder();
//            BYDAY=SU,MO,TU,WE,TH,FR,SA
//            存在重复
            if (!TextUtils.isEmpty(repetitionWeek)) {
                String[] split = repetitionWeek.split(",");
                for (int i = 0; i < split.length; i++) {
                    switch (Integer.valueOf(split[i])) {
                        case 1:
                            week.append("MO,");
                            break;
                        case 2:
                            week.append("TU,");
                            break;
                        case 3:
                            week.append("WE,");
                            break;
                        case 4:
                            week.append("TH,");
                            break;
                        case 5:
                            week.append("FR,");
                            break;
                        case 6:
                            week.append("SA,");
                            break;
                        case 7:
                            week.append("SU,");
                            break;

                    }

                }
                String string = week.toString();
                if (!TextUtils.isEmpty(string)) {
                    string = string.substring(0, string.length() - 1);
                    int i = Calendar.getInstance().get(Calendar.YEAR);
                    int i1 = i + 5;
                    event.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=1;UNTIL=" + i1 + "1224T000000Z;WKST=SU;BYDAY=" + string);
//                    eventValues.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=2;UNTIL=20271224T000000Z;WKST=SU;BYDAY=MO,WE,FR");
                }
            }
            Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
            if (newEvent == null) { //添加日历事件失败直接返回
                return false;
            }

            //事件提醒的设定
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
            values.put(CalendarContract.Reminders.MINUTES, 0);// 提前previousDate天有提醒
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
            if (uri == null || ContentUris.parseId(uri) == 0L) {
                //添加事件提醒失败直接返回
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    /**
     * 删除日历事件
     */
    public static void deleteCalendarEvent(Context context, String title) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) { //事件删除失败
                            return;
                        }
                    }
                }
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
    }

}
