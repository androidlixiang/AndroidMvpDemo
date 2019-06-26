package com.lixiang.androidmvpdemp;

import android.widget.TextView;

import com.xing.commonbase.base.BaseMVPActivity;
import com.xing.commonbase.mvp.IPresenter;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity {


    @BindView(R.id.tv_aa)
    TextView tvAa;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        tvAa=findViewById(R.id.tv_aa);
        tvAa.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

}
