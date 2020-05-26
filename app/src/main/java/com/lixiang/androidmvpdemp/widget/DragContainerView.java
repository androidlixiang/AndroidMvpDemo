package com.lixiang.androidmvpdemp.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.scwang.smartrefresh.layout.util.DensityUtil;

public class DragContainerView extends FrameLayout {

    private float lastY;
    private float currentY;

    public boolean intercept;

    public DragContainerView(@NonNull Context context) {
        super(context);
    }

    public DragContainerView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragContainerView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (intercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setIntercept(boolean intercept) {
        this.intercept = intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = event.getRawY();
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = event.getRawY();
                MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
                layoutParams.topMargin = layoutParams.topMargin - (int) (lastY - currentY);

                if (layoutParams.topMargin > DensityUtil.dp2px(500)) {
                    layoutParams.topMargin = DensityUtil.dp2px(500);
                }

                if (layoutParams.topMargin < DensityUtil.dp2px(100)) {
                    layoutParams.topMargin = DensityUtil.dp2px(100);
                    intercept = false;
                }
                setLayoutParams(layoutParams);
                lastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
                if (marginLayoutParams.topMargin > DensityUtil.dp2px(300)) {
                    anim(DensityUtil.dp2px(500));
                } else {
                    anim(DensityUtil.dp2px(100));
                }
                break;
        }
        return true;
    }

    public void anim(float end) {
        final MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(marginLayoutParams.topMargin, end);
        valueAnimator.setDuration(200L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                marginLayoutParams.topMargin = (int) y;
                setLayoutParams(marginLayoutParams);
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }


}
