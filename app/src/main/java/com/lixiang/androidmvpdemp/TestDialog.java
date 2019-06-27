package com.lixiang.androidmvpdemp;

import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xing.commonbase.base.BaseDialogFragment;

public class TestDialog extends BaseDialogFragment {
    @Override
    protected void initView() {

    }

//
//    @Override
//    protected WindowManager.LayoutParams getWidthHeight(WindowManager.LayoutParams windowParams) {

//    }


    @Override
    protected WindowManager.LayoutParams changeStyle(Window window) {
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = getContext().getResources().getDisplayMetrics().widthPixels * 3 / 4;
        windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return windowParams;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_test;
    }
}
