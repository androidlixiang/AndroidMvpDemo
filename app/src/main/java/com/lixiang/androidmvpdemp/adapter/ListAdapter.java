package com.lixiang.androidmvpdemp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.util.LogUtil;
import com.lixiang.androidmvpdemp.R;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/10 11:19
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/10 11:19
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int aaa;

    public ListAdapter(int layoutResId, @Nullable List<String> data, int bb) {
        super(layoutResId, data);
        aaa = bb;
    }

    public void setAaa(int aaa) {
        this.aaa = aaa;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setVisible(R.id.tv_name, true);
        helper.setText(R.id.tv_name, item);
        LogUtil.logTest(aaa+"------------------------");

    }
}
