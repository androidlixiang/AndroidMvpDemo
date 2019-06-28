package com.lixiang.androidmvpdemp;

import android.text.Editable;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixiang.androidmvpdemp.widget.NumberKeyboardView;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.base.IPresenter;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/activity/second")
public class SecondActivity extends BaseMVPActivity implements  NumberKeyboardView.IOnKeyboardListener {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.arc_back_view)
    ArcBackgroundView arcBackView;
    @BindView(R.id.view_keyboard)
    NumberKeyboardView numberKeyboardView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        numberKeyboardView.setIOnKeyboardListener(this);
        numberKeyboardView.shuffleKeyboard();

    }

    @Override
    public void onInsertKeyEvent(Editable text) {

    }

    @Override
    public void onDeleteKeyEvent() {

    }
}
