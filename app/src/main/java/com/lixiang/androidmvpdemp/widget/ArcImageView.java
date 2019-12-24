package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.common.util.DensityUtils;
import com.lixiang.androidmvpdemp.R;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/8/15 14:06
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/8/15 14:06
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class ArcImageView extends android.support.v7.widget.AppCompatImageView {
    /*
     *弧形高度
     */
    private int mArcHeight = DensityUtils.dp2px(30);
    private static final String TAG = "ArcImageView";

    public ArcImageView(Context context) {
        this(context, null);
    }

    public ArcImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcImageView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcImageView_arcHeight, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, getHeight() - 2 * mArcHeight);

        //计算出控制点Y1的坐标当为1/2的时候为最大值bt
// 公司 bt=(1-t)^2 *p0+2t(1-t)p1+t2^2*p2
        double bt = getHeight();
        double i0 = Math.pow(0.5, 2) * (getHeight() - mArcHeight);
        double i2 = Math.pow(0.5, 2) * (getHeight() - mArcHeight);

        double i3 = i0 + i2 - bt;
        double p1 = i3 / (2 * Math.pow(0.5, 2));


        path.quadTo(getWidth() / 2, (float) Math.abs(p1), getWidth(), getHeight() - 2 * mArcHeight);
        path.lineTo(getWidth(), 0);
        path.close();
        canvas.clipPath(path);

        super.onDraw(canvas);
    }

}