package com.lixiang.androidmvpdemp.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.adapter.Adapter;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/30 16:39
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/30 16:39
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class CehuaActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cehua;
    }

    @Override
    protected void initView() {


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        mRecyclerView.setAdapter(new Adapter(this));//设置适配器
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置控制Item增删的动画

    }
}
