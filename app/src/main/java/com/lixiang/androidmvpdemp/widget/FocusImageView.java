package com.lixiang.androidmvpdemp.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lixiang.androidmvpdemp.R;

public class FocusImageView extends androidx.appcompat.widget.AppCompatImageButton {
    public FocusImageView(Context context) {
        this(context,null);
    }

    public FocusImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FocusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusableInTouchMode(true);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(FocusImageView.this, "scaleY", 1.25f, 1),
                        ObjectAnimator.ofFloat(FocusImageView.this, "scaleX", 1.25f, 1)

                );
                animatorSet.setDuration(200);
                animatorSet.start();
                setBackground(null);
                break;
            case MotionEvent.ACTION_DOWN:

                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(FocusImageView.this, "scaleY", 1, 1.25f),
                        ObjectAnimator.ofFloat(FocusImageView.this, "scaleX", 1, 1.25f)

                );
                set.setDuration(200);
                set.start();

                setBackgroundResource(R.drawable.lalala);
                break;


        }

        return true;
    }


}
