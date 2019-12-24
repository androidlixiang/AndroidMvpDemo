package com.lixiang.androidmvpdemp;

import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.common.base.BaseDialogFragment;

import butterknife.BindView;

public class TestDialog extends BaseDialogFragment {
    @BindView(R.id.tv_biaoti)
    TextView tvBiaoti;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void initView() {
        tvBiaoti.setText("我是标题");
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
