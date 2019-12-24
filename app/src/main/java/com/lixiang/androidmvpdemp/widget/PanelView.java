package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.common.util.DensityUtils;
import com.scwang.smartrefresh.layout.util.DensityUtil;


public class PanelView extends View {

    private int mWidth;
    private int mHeight;

    private int mCircleRadius;
    private int mPointRadius;
    private int mNumRadius;

    private int mNumSize;


    private Paint mCirclePaint;
    private Paint mPointPaint;
    private Paint mIndicatorPaint;
    private int mIndicatorLength;

    private int mStart = 0;
    private int mEnd = 12;
    private int mNumScale = 10;

    private int mTotalScale;

    private float mRotateDegree;
    private Paint mTextPaint;
    private float mIndicatorDegree = 0;

    private int mLastX;


    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCircleRadius = DensityUtil.dp2px(132);
        mPointRadius = DensityUtil.dp2px(102);
        mNumRadius = DensityUtil.dp2px(70);
        mNumSize = DensityUtils.sp2px( 12);
        mIndicatorLength = DensityUtil.dp2px( 50);
        mTotalScale = (mEnd - mStart - 1) * mNumScale;
        mRotateDegree = 180f / mTotalScale;
        mIndicatorDegree = 0;
        initPaint();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight);
        RectF rectF = new RectF(-mCircleRadius, -mCircleRadius, mCircleRadius, mCircleRadius);
        canvas.drawArc(rectF, 180, 180, false, mCirclePaint);

        for (int i = 0; i < mTotalScale; i++) {
            canvas.save();
            canvas.rotate(-90 + i * mRotateDegree, 0, 0);
            canvas.drawPoint(0, -mPointRadius, mPointPaint);
            if (i % 10 == 0) {
                canvas.drawText((mStart + i / 10) + "", 0, -mNumRadius, mTextPaint);
            }
            canvas.restore();
        }

        canvas.save();
        canvas.rotate(mIndicatorDegree);
        canvas.drawLine(0, 0, 0, -mIndicatorLength, mIndicatorPaint);
        canvas.restore();
    }


    private void initPaint() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.parseColor("#66D007"));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(20);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.parseColor("#66D007"));
        mPointPaint.setStrokeWidth(2);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mNumSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#66D007"));

        mIndicatorPaint = new Paint();
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mIndicatorPaint.setStrokeWidth(5);
        mIndicatorPaint.setColor(Color.parseColor("#66D007"));
        mIndicatorPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();

                float deltaDegree = (x-mLastX) / (2f * mNumRadius) * 180;
                mIndicatorDegree = mIndicatorDegree + deltaDegree;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        mLastX = (int) event.getX();
        return true;
    }
}
