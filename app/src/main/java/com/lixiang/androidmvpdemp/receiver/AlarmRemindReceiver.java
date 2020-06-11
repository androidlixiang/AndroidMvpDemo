package com.lixiang.androidmvpdemp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/9 15:14
 */
public class AlarmRemindReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(CalendarContract.ACTION_EVENT_REMINDER)) {
            Uri data = intent.getData();
            String alertTime = data.getLastPathSegment();
            String selection = CalendarContract.CalendarAlerts.ALARM_TIME + "=?";
            Cursor cursor = context.getContentResolver().query(CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE, new String[]{CalendarContract.CalendarAlerts.TITLE}, selection, new String[]{alertTime}, null);
            if (cursor == null) {
                Log.v("WANLIU", "cursor is null");
                return;
            }
            while (cursor.moveToNext()) {
                String description = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                Log.v("WANLIU", "description=" + description);
            }
        }
    }
}
