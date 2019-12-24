package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.common.util.DensityUtils;
import com.common.util.LogUtil;


/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/26 15:00
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/26 15:00
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class DragView extends View {


    private Paint mPaint;
    private PointF dragPoint;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        LogUtil.logTest(event.getX()+"<-----x\n"+event.getY()+"<-----y");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float downx = event.getX();
                float dowmy = event.getY();
                initPaint(downx, dowmy);
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float movex = event.getX();
                float movey = event.getY();
                updrag(movex, movey);
                break;
        }
        invalidate();
        return true;
    }

    private void updrag(float movex, float movey) {
        dragPoint.x = movex;
        dragPoint.y = movey;
    }

    private void initPaint(float downx, float dowmy) {
        dragPoint = new PointF(downx, dowmy);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dragPoint!=null){
            canvas.drawCircle(dragPoint.x,dragPoint.y, DensityUtils.dp2px(6),mPaint);
        }

    }
}
