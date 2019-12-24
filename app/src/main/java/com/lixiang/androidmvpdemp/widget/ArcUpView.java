package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.common.util.DensityUtils;


/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/15 15:10
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/15 15:10
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ArcUpView extends View {

    private int arcHeight = DensityUtils.dp2px(30);
    private int height;
    private Paint paint = new Paint();

    public ArcUpView(Context context) {
        this(context, null);
    }

    public ArcUpView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int aa = height - arcHeight;
        Rect rect = new Rect(0, 0, getWidth(), aa);
        canvas.drawRect(rect, paint);

        double bt = getHeight();
        double i0 = Math.pow(0.5, 2) * (getHeight() - arcHeight);
        double i2 = Math.pow(0.5, 2) * (getHeight() - arcHeight);

        double i3 = i0 + i2 - bt;
        double p1 = i3 / (2 * Math.pow(0.5, 2));
        Path path = new Path();
        path.moveTo(0, aa);


        path.quadTo(getWidth()/2, (float) Math.abs(p1), getWidth(), aa);

        canvas.drawPath(path, paint);
    }
}
