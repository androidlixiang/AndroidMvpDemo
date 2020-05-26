package com.common.base;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.xing.commonbase.R;

@SuppressLint("ValidFragment")
public class CustomDialog extends BaseDialogFragment {
    TextView tvTitle;
    TextView tvContent;
    TextView tvConfirm;
    TextView tvCancel;
    public Builder builder;

    static final int BT_POSITIVE = -1;
    static final int BT_NEGATIVE = -2;

    @Override
    protected void initView() {
        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);
        tvConfirm = view.findViewById(R.id.tv_confirm);
        tvCancel = view.findViewById(R.id.tv_cancel);
//        tvTitle.setText("啦啦啦我是讨论讨论");
        if (builder != null) {
//            设置title
            if (builder.title != null) {
                tvTitle.setText(builder.title);
            } else {
                tvTitle.setVisibility(View.GONE);
            }
//            设置内容
            if (builder.content != null) {
                tvContent.setText(builder.content);
            } else {
                tvContent.setVisibility(View.GONE);
            }
            if (builder.negativeButtonText != null) {
                tvCancel.setText(builder.negativeButtonText);
            } else {
                tvCancel.setVisibility(View.INVISIBLE);
            }
            if (builder.positiveButtonText != null) {
                tvConfirm.setText(builder.positiveButtonText);
            } else {
                tvConfirm.setVisibility(View.INVISIBLE);
            }


            tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (builder.mPositiveButtonListener != null) {
                        builder.mPositiveButtonListener.onClick(getDialog(), BT_POSITIVE);
                    } else {
                        dismiss();
                    }

                }
            });
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (builder.mNegativeButtonListener != null) {
                        builder.mNegativeButtonListener.onClick(getDialog(), BT_NEGATIVE);
                    } else {
                        dismiss();
                    }
                }
            });


        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (builder != null) {
            getDialog().setCanceledOnTouchOutside(builder.mCancelable);
        } else {
            //默认点击外面不取消
            getDialog().setCanceledOnTouchOutside(false);
        }
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_custom;
    }

    private CustomDialog(Builder builder) {
        this.builder = builder;
    }

    @Override
    protected WindowManager.LayoutParams changeStyle(Window window) {
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = getContext().getResources().getDisplayMetrics().widthPixels * 3 / 4;
        windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return windowParams;
    }

    public static class Builder {
        private String title;
        private String content;
        //确定的文字
        private String positiveButtonText;
        //取消的文字
        private String negativeButtonText;
        private OnClickListener mPositiveButtonListener;
        private OnClickListener mNegativeButtonListener;
        private boolean mCancelable;

        public Builder setCancelable(boolean mCancelable) {
            this.mCancelable = mCancelable;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText, OnClickListener mPositiveButtonListener) {
            this.positiveButtonText = positiveButtonText;
            this.mPositiveButtonListener = mPositiveButtonListener;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText, OnClickListener mNegativeButtonListener) {
            this.negativeButtonText = negativeButtonText;
            this.mNegativeButtonListener = mNegativeButtonListener;
            return this;
        }

        public void show(FragmentManager fragmentManager) {
            new CustomDialog(this).show(fragmentManager, "CustomDialog");
        }

    }

    public interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which  the button that was clicked (ex.
         *               {@link DialogInterface#BUTTON_POSITIVE}) or the position
         *               of the item clicked
         */
        void onClick(DialogInterface dialog, int which);
    }
}
