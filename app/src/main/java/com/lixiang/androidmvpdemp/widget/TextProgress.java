package com.lixiang.androidmvpdemp.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: 带字的进度条
 * @Author: lixiang
 * @CreateDate: 2019/8/29 15:20
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/29 15:20
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */

public class TextProgress extends ProgressBar {

    String text;
    Paint mPaint;
    private String title;


    public TextProgress(Context context) {
        this(context, null, 0);
    }
    public TextProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public TextProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initText(attrs);
    }




    @Override
    public synchronized void setProgress(int progress) {
        setText(progress);
        super.setProgress(progress);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);


        if (TextUtils.isEmpty(title)) {
            // 文字宽
            float textWidth = mPaint.measureText(text);
            // 将坐标原点移到控件中心
            canvas.translate(getWidth() -textWidth, getHeight() / 2);
            // 文字baseline在y轴方向的位置
            float baseLineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;
            canvas.drawText(text, -textWidth / 2, baseLineY, mPaint);

        } else {

            // 文字宽
            float textWidth = mPaint.measureText(title + text);
            canvas.translate(getWidth() -textWidth, getHeight() / 2);
            // 文字baseline在y轴方向的位置
            float baseLineY = Math.abs(mPaint.ascent() + mPaint.descent()) / 2;
            canvas.drawText(title + text, -textWidth / 2, baseLineY, mPaint);
        }


    }

    //初始化，画笔
    @SuppressLint("ResourceType")
    private void initText(AttributeSet attributeSet) {
        this.mPaint = new Paint();
        if (attributeSet == null) {
            this.mPaint.setColor(Color.WHITE);
            this.mPaint.setTextSize(28);
        } else {
//            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.GroupBuyProgress);
//            title = typedArray.getString(R.styleable.GroupBuyProgress_m_title);
//            int mColor = typedArray.getColor(R.styleable.GroupBuyProgress_m_color, R.color.white);
            this.mPaint.setColor(Color.parseColor("#BC7F63"));
            this.mPaint.setTextSize(28);
            this.mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        }

    }

    private void setText() {
        setText(this.getProgress());
    }

    //设置文字内容
    private void setText(int progress) {
        if (this.getMax() != 0) {
            int i = (progress * 100) / this.getMax();
            this.text = String.valueOf(i) + "%";
        } else {
            this.text = "0%";
        }

    }


}
