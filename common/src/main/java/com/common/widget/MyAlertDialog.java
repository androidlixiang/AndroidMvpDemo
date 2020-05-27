package com.common.widget;


import android.content.Context;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.common.util.DensityUtils;

/**
 * Created by lixiang on 2018/10/11
 * 修复使用头条的360dp适配存在的大小异常
 */
public class MyAlertDialog extends AlertDialog.Builder {


    public MyAlertDialog(@NonNull Context context) {
        super(context);

    }

    public MyAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public AlertDialog create() {
        AlertDialog alertDialog = super.create();
        alertDialog.show();
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        alertDialog.getWindow().setAttributes( getWidthHeight(lp));
        return alertDialog;
    }



    /**
     * 设置默认的宽高
     *
     * @param windowParams
     * @return
     */

    public  WindowManager.LayoutParams getWidthHeight(WindowManager.LayoutParams windowParams) {
        windowParams.width = DensityUtils.getScreenWidth() * 5 / 6;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return windowParams;
    }


}
