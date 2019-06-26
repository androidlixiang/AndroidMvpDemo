package com.lixiang.androidmvpdemp;

import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.mvp.IPresenter;

public class MainActivity extends BaseMVPActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

}
