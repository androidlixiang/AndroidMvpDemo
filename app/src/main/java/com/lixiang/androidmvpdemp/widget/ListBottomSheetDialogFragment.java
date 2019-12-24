package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.common.util.DensityUtils;
import com.common.util.LogUtil;
import com.lixiang.androidmvpdemp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/7/31 18:08
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/7/31 18:08
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ListBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给dialog设置主题为透明背景 不然会有默认的白色背景
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);

    }

    /**
     * 如果想要点击外部消失的话 重写此方法
     *
     * @param savedInstanceState
     * @return
     * @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
     * Dialog dialog = super.onCreateDialog(savedInstanceState);
     * //设置点击外部可消失
     * dialog.setCanceledOnTouchOutside(true);
     * //设置使软键盘弹出的时候dialog不会被顶起
     * Window win = dialog.getWindow();
     * WindowManager.LayoutParams params = win.getAttributes();
     * win.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);
     * //这里设置dialog的进出动画
     * win.setWindowAnimations(R.style.DialogBottomAnim);
     * return dialog;
     * }
     */


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里将view的高度设置为精确高度，即可屏蔽向上滑动不占全屏的手势。
        //如果不设置高度的话 会默认向上滑动时dialog覆盖全屏
        View view = inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getScreenHeight(getActivity()) - DensityUtil.dp2px(100)));
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ItemAdapter(100));
        view.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出评论输入框
//                InputDialog inputDialog = new InputDialog(getActivity());
//                Window window = inputDialog.getWindow();
//                WindowManager.LayoutParams params = window.getAttributes();
//                //设置软键盘通常是可见的
//                window.setSoftInputMode(params.SOFT_INPUT_STATE_VISIBLE);
//                inputDialog.show();
            }
        });
    }

    private BottomSheetBehavior<FrameLayout> behavior;

    @Override
    public void onStart() {
        super.onStart();
        // 设置软键盘不自动弹出
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
//        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setDimAmount(0f);
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        if (bottomSheet != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomSheet.getLayoutParams();
            layoutParams.height = getHeight();
            behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setPeekHeight(DensityUtil.dp2px(100));
            // 初始为展开状态
            behavior.setState(BottomSheetBehavior.STATE_DRAGGING);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view, int i) {
                    LogUtil.logTest("onStateChanged");

                }

                @Override
                public void onSlide(@NonNull View view, float v) {

                }
            });
        }
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    private int getHeight() {
        int height = DensityUtils.getScreenHeight();
        if (getContext() != null) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            Point point = new Point();
            if (wm != null) {
                // 使用Point已经减去了状态栏高度
                wm.getDefaultDisplay().getSize(point);
                height = point.y - getTopOffset();
            }
        }
        return height;
    }

    private int topOffset = 0;

    public int getTopOffset() {
        return topOffset;
    }

    public void setTopOffset(int topOffset) {
        this.topOffset = topOffset;
    }

    public BottomSheetBehavior<FrameLayout> getBehavior() {
        return behavior;
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

    /**
     * 得到屏幕的高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}