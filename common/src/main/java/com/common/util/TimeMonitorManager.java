package com.common.util;

import android.util.Log;

import java.util.HashMap;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: GuoMinDoctor
 * @Description: 监控APP消耗
 * @Author: lixinag
 * @CreateDate: 2020/4/22 14:24
 */
public class TimeMonitorManager {
    private static final String TAG = "TimeMonitorManager";

    private HashMap<String, Long> mTimeTagMap = new HashMap<>();
    private long mStartTime = 0;
    private static volatile TimeMonitorManager mMonitorManager;

    private TimeMonitorManager() {

    }

    public static TimeMonitorManager getInstance() {
        if (mMonitorManager == null) {
            synchronized (TimeMonitorManager.class) {
                if (mMonitorManager == null) {
                    mMonitorManager = new TimeMonitorManager();
                }
            }
        }
        return mMonitorManager;
    }

    /**
     * 开始监听.
     */
    public void startMonitor() {
        if (mTimeTagMap.size() > 0) {
            mTimeTagMap.clear();
        }

        mStartTime = System.currentTimeMillis();

    }


    /**
     * 结束监听.
     *
     * @param tag 所要打印的tag.
     */
    public void endMonitor(String tag) {
        if (mTimeTagMap.get(tag) != null) {
            mTimeTagMap.remove(tag);
        }

        long time = System.currentTimeMillis() - mStartTime;
        mTimeTagMap.put(tag, time);
        showData();
    }

    private void showData() {
        if (mTimeTagMap.size() <= 0) {
            return;
        }

        for (String tag : mTimeTagMap.keySet()
        ) {
            long time = mTimeTagMap.get(tag);
            Log.d(TAG, tag + ": " + time);
        }
    }

}
