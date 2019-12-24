package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.common.util.DensityUtils;


public class LineYView extends View {

    private float max = 130;
    private Paint aPaint, bPaint, cPaint, dPaint, fPaint, ePaint;

    public LineYView(Context context) {
        this(context, null);
    }

    public LineYView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineYView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        aPaint = new Paint();
        aPaint.setColor(Color.parseColor("#288dff"));
        aPaint.setAntiAlias(true);
        aPaint.setStyle(Paint.Style.FILL);//设置空心
        bPaint = new Paint();
        bPaint.setColor(Color.parseColor("#00C372"));

        cPaint = new Paint();
        cPaint.setColor(Color.parseColor("#ffc100"));

        dPaint = new Paint();
        dPaint.setColor(Color.parseColor("#fe6000"));

        fPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fPaint.setColor(Color.parseColor("#38D90A"));
        // 需要加上这句，否则画不出东西
        fPaint.setStyle(Paint.Style.STROKE);
        fPaint.setStrokeWidth(1);
        fPaint.setPathEffect(new DashPathEffect(new float[]{15, 5}, 0));

        ePaint = new Paint();
        ePaint.setColor(Color.parseColor("#333333"));
        ePaint.setStyle(Paint.Style.FILL);
        ePaint.setAntiAlias(true);
        ePaint.setTextSize(DensityUtils.sp2px(11));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int btHeight = DensityUtils.dp2px(14);
        int topHeight = DensityUtils.dp2px(14);
        int itemHeight = (getMeasuredHeight() - btHeight - topHeight) / 4;

        RectF aRectF = new RectF(0, topHeight + 3 * itemHeight, DensityUtils.dp2px(4), getMeasuredHeight() - btHeight);
        RectF bRectF = new RectF(0, topHeight + 2 * itemHeight, DensityUtils.dp2px(4), getMeasuredHeight() - itemHeight - btHeight);
        RectF cRectF = new RectF(0, topHeight + itemHeight, DensityUtils.dp2px(4), getMeasuredHeight() - 2 * itemHeight - btHeight);
        RectF dRectF = new RectF(0, topHeight, DensityUtils.dp2px(4), getMeasuredHeight() - 3 * itemHeight - btHeight);
        canvas.drawRect(aRectF, aPaint);
        canvas.drawRect(bRectF, bPaint);
        canvas.drawRect(cRectF, cPaint);
        canvas.drawRect(dRectF, dPaint);

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        canvas.drawLine(0, topHeight + itemHeight, getWidth(), topHeight + itemHeight, fPaint);
        canvas.drawLine(0, topHeight + 2 * itemHeight, getWidth(), topHeight + 2 * itemHeight, fPaint);
        canvas.drawLine(0, topHeight + 3 * itemHeight, getWidth(), topHeight + 3 * itemHeight, fPaint);


        canvas.drawText(max / 4 + "", DensityUtils.dp2px(6), getMeasuredHeight() - topHeight - itemHeight - DensityUtils.dp2px(3), ePaint);
        canvas.drawText(2 * max / 4 + "", DensityUtils.dp2px(6), getMeasuredHeight() - topHeight - 2 * itemHeight - DensityUtils.dp2px(3), ePaint);
        canvas.drawText(3 * max / 4 + "", DensityUtils.dp2px(6), getMeasuredHeight() - topHeight - 3 * itemHeight - DensityUtils.dp2px(3), ePaint);
    }
}
