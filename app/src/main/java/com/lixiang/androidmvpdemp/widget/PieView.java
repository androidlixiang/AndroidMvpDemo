package com.lixiang.androidmvpdemp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.common.util.DensityUtils;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/5/27 14:24
 */
public class PieView extends View {
    private RectF rectF;
    private Paint circlePaint, arcPaint;
    private int radius = DensityUtils.dp2px(80);

    private float progress = 0;
    private int screenWidth = DensityUtils.getScreenWidth();
    private int screenHeight = DensityUtils.getScreenHeight();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStrokeWidth(DensityUtils.dp2px(3));
        circlePaint.setStyle(Paint.Style.STROKE);

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(Color.RED);
        arcPaint.setStyle(Paint.Style.FILL);


        rectF = new RectF();
        rectF.left = screenWidth / 2f - radius;
        rectF.right = screenWidth / 2f + radius;
        rectF.top = screenHeight / 2f - radius;
        rectF.bottom = screenHeight / 2f + radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF, 270, progress, true, arcPaint);


        canvas.drawCircle(screenWidth / 2, screenHeight / 2, radius, circlePaint);

    }


    public void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 60).setDuration(5 * 1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int a = (int) animation.getAnimatedValue();
                progress = a / 60f * 360;
                invalidate();
            }
        });
        valueAnimator.start();


    }
}
