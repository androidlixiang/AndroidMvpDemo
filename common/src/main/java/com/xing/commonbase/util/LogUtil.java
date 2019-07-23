package com.xing.commonbase.util;


import android.util.Log;

import com.xing.commonbase.base.BaseApplication;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：正式的不显示log只有当debug打印
 */
public class LogUtil {


    private static boolean isRealease = !BaseApplication.isDebug;


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
            Log.d(Tag, "logi: " + str);
        }

    }

}
