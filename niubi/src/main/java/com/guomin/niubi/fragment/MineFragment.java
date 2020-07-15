package com.guomin.niubi.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.base.BaseFragment;
import com.guomin.niubi.R;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/12 16:00
 */
public class MineFragment extends BaseFragment {


    public static MineFragment getInsytance(String s) {
        Bundle bundle = new Bundle();
        bundle.putString("type", s);
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);

        return mineFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_kt_mine;
    }

    @Override
    protected void initView(View rootView) {
        TextView viewById = rootView.findViewById(R.id.textView);
        viewById.setText(getArguments().getString("type"));
    }

    @Override
    protected void initData() {


    }
}
