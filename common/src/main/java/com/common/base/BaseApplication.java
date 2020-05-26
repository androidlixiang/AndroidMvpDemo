package com.common.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import androidx.multidex.MultiDexApplication;

import com.common.util.CrashHandler;
import com.common.util.ThreadManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.xing.commonbase.R;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：初始化Application
 */

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication application;
    public static boolean isDebug = true;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.c_f6f6f6, R.color.c_1d2b36);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        isDebug = getApplicationInfo() != null && (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        ThreadManager.getThreadPool().execute(() -> {
            if (isDebug) {
                //       LeakCanary.install(this);
                CrashHandler.getInstance().init(getApplication());
            }
        });
    }


    public static BaseApplication getApplication() {
        return application;
    }
}
