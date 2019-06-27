package com.lixiang.androidmvpdemp;

import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.base.IPresenter;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/activity/second")
public class SecondActivity extends BaseMVPActivity {
    @BindView(R.id.button)
    Button button;

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
    }
}
