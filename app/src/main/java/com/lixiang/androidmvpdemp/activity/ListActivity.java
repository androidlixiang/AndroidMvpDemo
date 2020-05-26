package com.lixiang.androidmvpdemp.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.common.base.BaseActivity;
import com.common.util.LogUtil;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/10 11:17
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/10 11:17
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ListActivity extends BaseActivity {


    private int aaa;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    List<String> strings = new ArrayList<>();
    private ListAdapter listAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {


        for (int i = 0; i < 300; i++) {
            strings.add(i + "");

        }
        listAdapter = new ListAdapter(R.layout.item_list, strings, aaa);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(listAdapter);

    }

    public void doClick(View view) {

        int ran = new Random().nextInt(100);
        aaa = ran;
        LogUtil.logTest(aaa+"-----------ListActivity");
        for (int i = 0; i < ran; i++) {
            strings.add(i + "");
        }
        listAdapter.setAaa(aaa);
        listAdapter.notifyDataSetChanged();

    }

}
