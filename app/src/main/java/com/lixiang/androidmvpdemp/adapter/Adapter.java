package com.lixiang.androidmvpdemp.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.util.DensityUtils;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.widget.LeftSlideView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/30 16:45
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/30 16:45
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements LeftSlideView.IonSlidingButtonListener {

    private Context mContext;

    private List<String> mDatas = new ArrayList<String>();

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private IonSlidingViewClickListener mISetBtnClickListener;

    private LeftSlideView mMenu = null;


    public Adapter(Context context) {

        mContext = context;
//        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
//        mISetBtnClickListener = (IonSlidingViewClickListener) context;

        for (int i = 0; i < 10; i++) {
            mDatas.add(i + "");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.textView.setText(mDatas.get(position));

        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = DensityUtils.getScreenWidth();

        //item正文点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }

            }
        });


        //左滑设置点击事件
        holder.btn_Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();
                mISetBtnClickListener.onSetBtnCilck(view, n);
            }
        });


        //左滑删除点击事件
        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(view, n);
            }
        });

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        //获取自定义View的布局（加载item布局）
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, arg0, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView btn_Set;
        public TextView btn_Delete;
        public TextView textView;
        public ViewGroup layout_content;

        public MyViewHolder(View itemView) {
            super(itemView);

            btn_Set = (TextView) itemView.findViewById(R.id.tv_set);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            textView = (TextView) itemView.findViewById(R.id.text);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);

            ((LeftSlideView) itemView).setSlidingButtonListener(Adapter.this);
        }
    }


    /**
     * 删除item
     * @param position
     */
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (LeftSlideView) view;
    }


    /**
     * 滑动或者点击了Item监听
     *
     * @param leftSlideView
     */
    @Override
    public void onDownOrMove(LeftSlideView leftSlideView) {
        if (menuIsOpen()) {
            if (mMenu != leftSlideView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }

    /**
     * 判断菜单是否打开
     *
     * @return
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }


    /**
     * 注册接口的方法：点击事件。在Mactivity.java实现这些方法。
     */
    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);//点击item正文

        void onDeleteBtnCilck(View view, int position);//点击“删除”

        void onSetBtnCilck(View view, int position);//点击“设置”
    }

}
