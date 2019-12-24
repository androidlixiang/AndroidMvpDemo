package com.lixiang.androidmvpdemp.activity;

import android.content.Intent;

import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.MainActivity;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/31 15:48
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/31 15:48
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class WelcomeActivity extends BaseActivity {

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initView() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
