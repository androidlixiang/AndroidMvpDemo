package com.xing.commonbase.util;


import android.util.Log;

import com.xing.commonbase.BuildConfig;

/**
 * Created by lixiang on 2018/5/17.
 * 正式的不显示log只有当debug打印
 */

public class LogUtil {


    private static boolean isRealease = BuildConfig.DEBUG ? false : true;


    public static void logXj(String str) {
        if (!isRealease) {
            Log.e("xj", str);
        }

    }

    public static void logXj(int str) {

        if (!isRealease) {
            Log.e("xj", str + "");
        }

    }

    public static void logTest(String str) {
        if (!isRealease) {
            Log.e("TEST", str);
        }

    }

    public static void logi(String Tag, String str) {
        if (!isRealease) {
            Log.i(Tag, "logi: " + str);
        }

    }

}
