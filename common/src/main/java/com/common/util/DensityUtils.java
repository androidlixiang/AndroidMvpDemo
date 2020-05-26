package com.common.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.WindowManager;

import com.common.base.BaseApplication;


/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：dp,px相互转化工具类
 */
public class DensityUtils {

    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, BaseApplication.getApplication().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px( float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, BaseApplication.getApplication().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / BaseApplication.getApplication().getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 得到屏幕的高
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 得到屏幕的宽
     *
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int resourceId =  BaseApplication.getApplication().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return  BaseApplication.getApplication().getResources().getDimensionPixelSize(resourceId);
    }

}

