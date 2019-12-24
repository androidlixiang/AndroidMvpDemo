package com.lixiang.androidmvpdemp.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.widget.DragContainerView;

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
public class ButtonTestActivity extends BaseActivity {
    @BindView(R.id.ll_pa)
    LinearLayout llPa;
    @BindView(R.id.mDragContainerView)
    DragContainerView mDragContainerView;
    private String TAG = "ButtonTestActivity";

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rv)
    RecyclerView rv;
    private  String aaa;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_bt;
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(new ItemAdapter(100));

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
//                    llPa.requestDisallowInterceptTouchEvent(true);
                    mDragContainerView.setIntercept(false);
                } else {
                    Log.i(TAG, "direction -1: false");//滑动到顶部
//                    llPa.requestDisallowInterceptTouchEvent(false);
                    mDragContainerView.setIntercept(true);
                }
            }
        });
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    aaa.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }



    private class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
        private final int mItemCount;

        ItemAdapter(int itemCount) {
            mItemCount = itemCount;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            final TextView text;

            MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }

    }
}
