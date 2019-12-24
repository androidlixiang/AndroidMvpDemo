package com.lixiang.androidmvpdemp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lixiang.androidmvpdemp.R;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/12 10:15
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/12 10:15
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class HeadScrollAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public HeadScrollAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);

    }
}
