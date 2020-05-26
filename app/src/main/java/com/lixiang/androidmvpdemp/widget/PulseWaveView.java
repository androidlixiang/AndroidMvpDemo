package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.common.util.DensityUtils;
import com.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/24 14:49
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/24 14:49
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class PulseWaveView extends View {
    Paint mPaint = new Paint();
    Path path = new Path();
    private List<PointF> pointFList = new ArrayList<>();

    public PulseWaveView(Context context) {
        this(context, null);
    }

    public PulseWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PulseWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DensityUtils.dp2px(3));
        mPaint.setAntiAlias(true);
    }

    float lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.logTest(event.getX() + "<------------");


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                int scrollX = getScrollX();
                scrollBy((int) (lastX - event.getX()), 0);
                break;
        }

        lastX = event.getX();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(with, DensityUtils.dp2px(300));
    }

    int with = DensityUtils.getScreenWidth();


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (pointFList.size() < 2) return;
        path.reset();
        for (int i = 0; i < pointFList.size() - 1; i++) {
            PointF pointF = pointFList.get(i);
            PointF pointF1 = pointFList.get(i + 1);
            path.moveTo(pointF.x, pointF.y);


            path.quadTo((pointF1.x + pointF1.x) / 2+50, (pointF1.y + pointF1.y) / 2+50, pointF1.x, pointF1.y);
            canvas.drawPath(path, mPaint);
        }
    }


    public void setdata(List list) {
        pointFList.clear();
        pointFList.addAll(list);
        invalidate();
    }

}
