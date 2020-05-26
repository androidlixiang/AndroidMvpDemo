package com.common.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.common.base.BaseApplication;
import com.xing.commonbase.BuildConfig;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：吐司工具
 */
public class ToastUtil {


    public static void toast(CharSequence cs) {
        if (TextUtils.isEmpty(cs.toString())) {
            return;
        }
        Toast toast = Toast.makeText(BaseApplication.getApplication(), null, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(cs.toString());
        toast.show();
    }

    public static void toastDebug(CharSequence cs) {
        if (BuildConfig.DEBUG)
            Toast.makeText(BaseApplication.getApplication(), cs, Toast.LENGTH_SHORT).show();
    }


    public static void toastLong(CharSequence cs) {
        Toast.makeText(BaseApplication.getApplication(), cs, Toast.LENGTH_SHORT).show();
    }

    public static void toastDebugLong(CharSequence cs) {
        if (BaseApplication.isDebug)
            Toast.makeText(BaseApplication.getApplication(), cs, Toast.LENGTH_SHORT).show();
    }
}
