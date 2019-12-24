package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/14 10:35
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/14 10:35
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class BtArcImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPaint;
    private int mColor;

    public BtArcImageView(Context context) {
        super(context);
    }

    public BtArcImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BtArcImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initPaint() {
        mPaint = new Paint();
        mColor = Color.parseColor("#274b34");
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        RectF arcRect = new RectF(-mWidth / 2, -mArcHeight, mWidth / 2, mArcHeight);
//        canvas.drawArc(arcRect, 0, 180, false, mPaint);
    }
}
