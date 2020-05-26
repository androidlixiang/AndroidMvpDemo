package com.lixiang.androidmvpdemp.activity;

import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.common.util.DensityUtils;
import com.common.util.LogUtil;
import com.lixiang.androidmvpdemp.R;

import butterknife.BindView;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/5 10:13
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/5 10:13
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ButtonTest2Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scroll)
    AppBarLayout scroll;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private BottomSheetBehavior behavior;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_bt2;
    }

    @Override
    protected void initView() {
        scroll.getLayoutParams().height = DensityUtils.getScreenHeight() - DensityUtils.dp2px(200);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Adapter adapter = new Adapter();
        mRecyclerView.setAdapter(adapter);

        behavior = BottomSheetBehavior.from(findViewById(R.id.scroll));
//        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        } else {
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                LogUtil.logTest("onStateChanged");


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


    }

    public void intro(View view) {

        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setPeekHeight(0);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setPeekHeight(DensityUtils.dp2px(150));
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void select(View view) {
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this)
                .inflate(R.layout.list, null);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(recyclerView);
        dialog.show();
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String text) {
//                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }



    static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mItemClickListener = li;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            holder.tv.setText("item " + position);
            if (mItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemClick(holder.getLayoutPosition(),
                                holder.tv.getText().toString());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;

            public Holder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }

        interface OnItemClickListener {
            void onItemClick(int position, String text);
        }
    }

}
