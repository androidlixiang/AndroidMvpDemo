package com.lixiang.androidmvpdemp.activity;

import android.view.View;
import android.widget.Button;

import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.R;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/18 20:21
 */
public class TestProfilerActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_profiler;
    }

    @Override
    protected void initView() {
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        while (true) {
                            OutMethod();
                        }
                    }
                }.start();

            }
        });

    }


    private void OutMethod() {

        String a=new String();
        int length = a.length();

    }


}
