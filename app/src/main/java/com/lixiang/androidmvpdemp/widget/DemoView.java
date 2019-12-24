package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/27 9:49
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/27 9:49
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class DemoView extends View {

    Paint mPaint;
    Paint mPaint1;

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint1 = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint1.setColor(Color.BLUE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF1 = new RectF(20, 20, 600, 200);
        canvas.drawRect(rectF1, mPaint1);


        RectF rectF = new RectF(20, 20, 600, 200);
        canvas.drawArc(rectF, -45, 160, false, mPaint);

    }
}
