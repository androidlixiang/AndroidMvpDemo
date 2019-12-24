package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class MyAlertDialog extends AlertDialog {
    protected MyAlertDialog(@NonNull Context context) {
        super(context);
    }

    protected MyAlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    public void setView(View view) {
        super.setView(view);
    }
}
