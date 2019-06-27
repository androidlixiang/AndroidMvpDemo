package com.xing.commonbase.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xing.commonbase.R;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStyle(R.style.MyDialogStyle, R.style.AppTheme);
        View view = inflater.inflate(getLayoutResId(), null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCanceledOnTouchOutside(true); // 外部点击取消
        Window window = getDialog().getWindow();
        //设置背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setAttributes(changeStyle(window));
    }


    /**
     * 更改dialog大小,样式，已经点击是否消失等
     *
     * @param windowParams
     * @return
     */
    protected WindowManager.LayoutParams changeStyle(Window window) {
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity = Gravity.BOTTOM;
        windowParams.width = getContext().getResources().getDisplayMetrics().widthPixels * 3 / 4;
        windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return windowParams;
    }


    protected abstract void initView();

    protected abstract int getLayoutResId();
}
