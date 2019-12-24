package com.lixiang.androidmvpdemp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created by User on 2019/3/20.
 */

public class ArcBackgroundView extends View {

    private Paint mPaint;
    //View的宽度
    private int mWidth;
    //View的高度
    private int mHeight;
    //上半部分矩形高度
    private int mRectHeight;
    //弓高
    private int mArcHeight;
    //背景颜色值
    private int mColor;

    public ArcBackgroundView(Context context) {
        this(context, null);
    }

    public ArcBackgroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //弓高
        mArcHeight = DensityUtil.dp2px(30);
        initPaint();
    }


    public void initPaint() {
        mPaint = new Paint();
        mColor = Color.parseColor("#274b34");
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        //矩形高度
        mRectHeight = mHeight - mArcHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        canvas.translate(0, 0);
        RectF arcRect = new RectF(0, 0, mWidth, mArcHeight);
        canvas.drawArc(arcRect, 0, 180, false, mPaint);


//        canvas.translate(0, 0);
//        Rect rect = new Rect(0, 0, mWidth, mRectHeight);
//        canvas.drawRect(rect, mPaint);


    }

    public void setColor(String color) {
        this.mColor = Color.parseColor(color);
        postInvalidate();
    }

    public void setColor(int color) {
        this.mColor = color;
        postInvalidate();
    }


}
