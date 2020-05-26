package com.lixiang.androidmvpdemp.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.adapter.HeadScrollAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/12 10:02
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/12 10:02
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class HeadZoomScrollViewActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_headzoom;
    }

    @Override
    protected void initView() {
        ImageView viewById = findViewById(R.id.iv);
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);

//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aaa);
//        layoutParams.height = bitmap.getHeight() * DensityUtils.getScreenWidth() / bitmap.getWidth();
//        layoutParams.width = DensityUtils.getScreenWidth();

        Glide.with(this).load(R.mipmap.aaa).into(viewById);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "啦啦啦");
        }
        HeadScrollAdapter scrollAdapter = new HeadScrollAdapter(R.layout.item_headzoom, list);
        mRecyclerView.setAdapter(scrollAdapter);

    }
}
