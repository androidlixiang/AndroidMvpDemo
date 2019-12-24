package com.lixiang.androidmvpdemp;


import com.common.base.BaseApplication;
import com.lixiang.androidmvpdemp.statistic.SensorsStatisticsHelper;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePal;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        LeakCanary.install(this);
        SensorsStatisticsHelper.initSensors(this);
    }
}
