package com.lixiang.androidmvpdemp.statistic;

import android.content.Context;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2019/1/2 18:00
 * @版本 0.2
 * @类说明: 统计统一入口
 */

public class SensorsStatisticsHelper {
    public static final String EVENT_TYPE = "event";
    public static final String PAGE_TYPE = "page";

    public static void initSensors(Context context) {

        // 数据接收的 URL
        final String SA_SERVER_URL = "http://bi-xiaokang.nahefa.com.cn";
/*
//设置 SAConfigOptions，传入数据接收地址 SA_SERVER_URL
        SAConfigOptions saConfigOptions = new SAConfigOptions(SA_SERVER_URL);
        boolean enableLog = false;
        if (BuildConfig.DEBUG) {
            enableLog = true;
        }
//通过 SAConfigOptions 设置神策 SDK，每个条件都非必须，开发者可根据自己实际情况设置，更多设置可参考 SAConfigOptions 类中方法注释
        saConfigOptions.setAutoTrackEventType(SensorsAnalyticsAutoTrackEventType.APP_CLICK | // 开启全埋点点击事件
                SensorsAnalyticsAutoTrackEventType.APP_START |      //开启全埋点启动事件
                SensorsAnalyticsAutoTrackEventType.APP_END |        //开启全埋点退出事件
                SensorsAnalyticsAutoTrackEventType.APP_VIEW_SCREEN)     //开启全埋点浏览事件
                .enableLog(true)        //开启神策调试日志，默认关闭(调试时，可开启日志)。
                .enableTrackAppCrash();     //开启 crash 采集
//需要在主线程初始化神策 SDK
        SensorsDataAPI.startWithConfigOptions(context, saConfigOptions);*/

        SensorsDataAPI.sharedInstance(
                context,                   // 传入 Context
                SA_SERVER_URL,                      // 数据接收的 URL
                SensorsDataAPI.DebugMode.DEBUG_OFF);
    }

    public static void addStatistics(String bean) {
        JSONObject object = new JSONObject();
        try {
            if (bean != null) {
                object.put("lixiang", "lixiang");
                SensorsDataAPI.sharedInstance().track("properties", object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
