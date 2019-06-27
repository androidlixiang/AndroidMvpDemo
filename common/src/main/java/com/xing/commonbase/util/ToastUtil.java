package com.xing.commonbase.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.xing.commonbase.BuildConfig;
import com.xing.commonbase.base.BaseApplication;

/**
 * Created by lixiang on 2018/5/17.
 */

public class ToastUtil {


    public static void toast(CharSequence cs) {
        if (TextUtils.isEmpty(cs.toString())) {
            return;
        }
        Toast toast = Toast.makeText(BaseApplication.application, cs, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void toastDebug(CharSequence cs) {
        if (BuildConfig.DEBUG) Toast.makeText(BaseApplication.application, cs, Toast.LENGTH_SHORT).show();
    }


    public static void toastLong(CharSequence cs) {
        Toast.makeText(BaseApplication.application, cs, Toast.LENGTH_SHORT).show();
    }

    public static void toastDebugLong(CharSequence cs) {
        if (BuildConfig.DEBUG) Toast.makeText(BaseApplication.application, cs, Toast.LENGTH_SHORT).show();
    }
}
