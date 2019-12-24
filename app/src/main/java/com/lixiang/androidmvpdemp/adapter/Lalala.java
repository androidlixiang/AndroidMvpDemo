package com.lixiang.androidmvpdemp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/2 14:23
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/2 14:23
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class Lalala extends BaseQuickAdapter<String, BaseViewHolder> {
    public Lalala(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
