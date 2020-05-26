package com.common.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;

import com.xing.commonbase.R;

import butterknife.ButterKnife;

/**
 * @创建者:李祥
 * @创建日期： 2019/7/23 10:38
 * @类说明：项目的BaseDialog
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected View view;
    public  Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStyle(R.style.MyDialogStyle, R.style.AppTheme);
        view = inflater.inflate(getLayoutResId(), null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
     * @param window
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
