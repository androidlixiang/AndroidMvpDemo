package com.lixiang.androidmvpdemp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.common.util.BitmapUtils;
import com.common.util.DensityUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.RangeMonthView;
import com.lixiang.androidmvpdemp.R;

/**
 * 范围选择月视图
 * Created by huanghaibin on 2018/9/13.
 */

public class CustomRangeMonthView extends RangeMonthView {

    private int mRadius;
    private Bitmap bitmap;

    public CustomRangeMonthView(Context context) {
        super(context);
    }


    @Override
    protected void onPreviewHook() {
//        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
        mRadius = DensityUtils.dp2px(22);
        mSchemePaint.setStyle(Paint.Style.STROKE);
        mSelectedPaint.setColor(Color.parseColor("#D1F3DC"));
        bitmap = BitmapUtils.getBitmap(getContext(), R.drawable.bg_calendar, DensityUtils.dp2px(43), DensityUtils.dp2px(43));
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme,
                                     boolean isSelectedPre, boolean isSelectedNext) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        if (isSelectedPre) {
            if (isSelectedNext) {
                canvas.drawRect(x, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
            } else {//最后一个，the last
                canvas.drawRect(x, cy - mRadius, cx, cy + mRadius, mSelectedPaint);
                canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
                canvas.drawBitmap(bitmap, cx - mRadius, cy - mRadius, null);
            }
        } else {
            if (isSelectedNext) {
                canvas.drawRect(cx, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
            }
            canvas.drawBitmap(bitmap, cx - mRadius, cy - mRadius, null);
        }

        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        mSchemePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cx, y + mItemHeight - DensityUtils.dp2px(2), DensityUtils.dp2px(4), mSchemePaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        boolean isInRange = isInRange(calendar);
        boolean isEnable = !onCalendarIntercept(calendar);

        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    mSelectTextPaint);
        } else if (hasScheme) {
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() && isInRange && isEnable ? mSchemeTextPaint : mOtherMonthTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() && isInRange && isEnable ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }


}
