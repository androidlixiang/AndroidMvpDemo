package com.lixiang.androidmvpdemp.widget;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.BaseDialogFragment;
import com.common.util.DensityUtils;
import com.common.util.TimeUtils;
import com.common.util.ToastUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.lixiang.androidmvpdemp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: AndroidMvpDemo
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2020/6/19 14:59
 */
public class CalendarSelectDialog extends BaseDialogFragment implements
        CalendarView.OnMonthChangeListener, CalendarView.OnCalendarRangeSelectListener {

    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.tv_appointment)
    TextView tvAppointment;
    @BindView(R.id.calendarView)
    CalendarView mCalendarView;
    @BindView(R.id.tv_data_time)
    TextView tv_data_time;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;


    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_calendar_select;
    }

    protected void initView() {
        mCalendarView.setSelectRangeMode();
//        设置范围
        long time30 = 24 * 60 * 60 * 1000 * 31L;
        String time = TimeUtils.getData(System.currentTimeMillis() + time30);
        String[] split = time.split("-");
        if (split.length == 3) {
            mCalendarView.setRange(2020, 1, 1,
                    Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])
            );
        }
//        设置默认选中7天
        String startCalendar = TimeUtils.getData();
        String[] startCalendarTime = startCalendar.split("-");
        long time7 = 24 * 60 * 60 * 1000 * 6L;
        String endCalendar = TimeUtils.getData(System.currentTimeMillis() + time7);
        String[] endCalendarTime = endCalendar.split("-");
        if (startCalendarTime.length == 3 && endCalendarTime.length == 3) {
            mCalendarView.setSelectCalendarRange(Integer.parseInt(startCalendarTime[0]), Integer.parseInt(startCalendarTime[1]), Integer.parseInt(startCalendarTime[2]),
                    Integer.parseInt(endCalendarTime[0]), Integer.parseInt(endCalendarTime[1]), Integer.parseInt(endCalendarTime[2]));
        }


        mCalendarView.setSelectRange(-1, 31);
        mCalendarView.post(new Runnable() {
            @Override
            public void run() {
                mCalendarView.scrollToCurrent();
            }
        });
        initData();
    }

    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();


        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setOnCalendarRangeSelectListener(this);


        tv_data_time.setText(year + "年" + month + "月");
        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
        mCalendarView.setCalendarItemHeight(DensityUtils.dp2px(48));

    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        color = Color.parseColor("#4DBD73");
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }


    @Override
    public void onMonthChange(int year, int month) {
        tv_data_time.setText(year + "年" + month + "月");
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCanceledOnTouchOutside(true); // 外部点击取消
        Window window = getDialog().getWindow();
        //设置背景透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity = Gravity.TOP;
        windowParams.width = getContext().getResources().getDisplayMetrics().widthPixels;
        windowParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);
    }

    @OnClick({R.id.iv_pre, R.id.tv_data_time, R.id.iv_next, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pre:
                mCalendarView.scrollToPre();
                break;
            case R.id.tv_data_time:
                break;
            case R.id.iv_next:
                mCalendarView.scrollToNext();
                break;
            case R.id.tv_confirm:
                List<Calendar> calendars = mCalendarView.getSelectCalendarRange();
                if (calendars == null || calendars.size() == 0) {
                    ToastUtil.toast(clickCalendar.getYear() + "年" + clickCalendar.getMonth() + "月" + clickCalendar.getDay() + "日");
                    return;
                }
                for (Calendar c : calendars) {

                    Log.e("SelectCalendarRange", c.toString()
                            + " -- " + c.getScheme()
                            + "  --  " + c.getLunar());
                }
                ToastUtil.toast(String.format("选择了%s个日期: %s —— %s", calendars.size(),
                        calendars.get(0).toString(), calendars.get(calendars.size() - 1).toString()));
                break;
        }
    }


    @Override
    public void onCalendarSelectOutOfRange(Calendar calendar) {
    }

    @Override
    public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {
        ToastUtil.toast((isOutOfMinRange ? "小于最小选择范围" : "最多不可以超过31天"));
    }

    private Calendar clickCalendar;

    @Override
    public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {
        clickCalendar = calendar;
    }
}
