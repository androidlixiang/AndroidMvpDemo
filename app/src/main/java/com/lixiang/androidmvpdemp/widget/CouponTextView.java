package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.lixiang.androidmvpdemp.R;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/14 10:04
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/14 10:04
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class CouponTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint;

    private Context mContext;

    private int mColor;

    public CouponTextView(Context context) {
        super(context);
        initPaint();
    }

    public CouponTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CouponTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(12f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectf = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRect(rectf, mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        canvas.drawCircle(0, 50, 20, mPaint);
        super.onDraw(canvas);

    }
}
