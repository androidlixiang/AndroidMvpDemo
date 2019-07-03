package com.xing.commonbase.util;

import android.content.Context;
import android.util.TypedValue;

import com.xing.commonbase.base.BaseApplication;

/**
 * dp,px 相互转化工具类
 */
public class DensityUtil {

    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, BaseApplication.application.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, BaseApplication.application.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = BaseApplication.application.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / BaseApplication.application.getResources().getDisplayMetrics().scaledDensity);
    }


    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int resourceId =  BaseApplication.application.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return  BaseApplication.application.getResources().getDimensionPixelSize(resourceId);
    }

}

