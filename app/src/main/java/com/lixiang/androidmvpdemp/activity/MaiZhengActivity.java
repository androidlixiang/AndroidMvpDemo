package com.lixiang.androidmvpdemp.activity;

import android.graphics.Color;
import android.graphics.PointF;
import android.widget.Button;

import com.common.base.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.lixiang.androidmvpdemp.R;
import com.lixiang.androidmvpdemp.widget.PulseWaveView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/9/24 17:24
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/9/24 17:24
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class MaiZhengActivity extends BaseActivity {
    @BindView(R.id.mPulseWaveView)
    PulseWaveView mPulseWaveView;
    @BindView(R.id.mButt)
    Button mButt;
    private int mLineChartInterval = 2;//数据点间隔，默认 2 s
    private int mLineChartCount = 60 / mLineChartInterval * 60;//最大数据点
    private long mLineChartUpdateTime = System.currentTimeMillis();
    private Map<Long, Integer> mLineChartValues = new HashMap<>();

    private SimpleDateFormat mLineChartDateFormat = new SimpleDateFormat("mm:ss.SSS");
    @BindView(R.id.chart)
    LineChart chart;
    private LineDataSet mLineDataSetTime;
    private LineDataSet mLineDataSetData;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_maizheng;
    }

    @Override
    protected void initView() {
        initLinerChart();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int data = new Random().nextInt(30) + 361;
                            notifyLineChartRefreshData(data);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    private void notifyLineChartRefreshData(int data) {

        if (isFinishing()) return;

        long nowTime = System.currentTimeMillis();

        mLineChartUpdateTime = nowTime;
        mLineChartValues.put(nowTime / 100, data);
        long highlightTime = 0L;
        float highlightRefreshX = 0F;

        if (chart.getHighlighted() != null && chart.getHighlighted().length > 0) {
            Highlight highlight = chart.getHighlighted()[0];
            if (mLineDataSetData.getEntriesForXValue(highlight.getX()) != null) {
                highlightTime = (long) mLineDataSetData.getEntriesForXValue(highlight.getX()).get(0).getData();
            }

        }


        mLineDataSetData.clear();


        for (int i = 0; i < mLineChartCount; i++) {
            long time = mLineChartUpdateTime / 100 - (mLineChartCount - i);
            if (mLineChartValues.containsKey(time)) {

                mLineDataSetData.addEntry(new Entry(i, mLineChartValues.get(time), time));
                if (time == highlightTime) {
                    highlightRefreshX = i;
                }
            }

        }

        if (highlightRefreshX <= 0) {
            chart.highlightValues(null);
        } else {
            chart.highlightValue(highlightRefreshX, 1);
        }

    }

    private void initLinerChart() {
        chart.setDescription(null);//设置描写
        chart.getLegend().setEnabled(false); //设置图例关
        chart.setDrawBorders(false); //设置是否显示边界
        chart.setBackgroundColor(Color.WHITE);//设置背景色

        //设置触摸(关闭影响下面3个属性)
        chart.setTouchEnabled(true);
        //设置是否可以拖拽
        chart.setDragEnabled(true);
        //设置是否可以缩放
        chart.setScaleEnabled(false);
        //设置是否能扩大扩小
        chart.setPinchZoom(false);


//
//            chart.marker = MyMarkerView(this, R.layout.ui_marker_view).apply { chartView = chart }
//            chart.setOnChartValueSelectedListener(object :OnChartValueSelectedListener {
//                var prevEntry: Entry? = null
//
//                override fun onNothingSelected() {
//                    prevEntry?.icon = null
//                }
//
//                override fun onValueSelected(e: Entry?, h: Highlight?) {
//                    prevEntry?.icon = null
//                    e?.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_oval)
//                    prevEntry = e
//                }
//            })

        //X轴

        XAxis xAxis = chart.getXAxis();

        //设置竖网格
        xAxis.setDrawGridLines(false);
        //设置X轴线
        xAxis.setDrawAxisLine(false);
        //设置X轴文字在底部显示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴文字
//        textColor = Color.parseColor("#434B61")
//        textSize = 9f
        //设置X轴避免图表或屏幕的边缘的第一个和最后一个轴中的标签条目被裁剪
        xAxis.setAvoidFirstLastClipping(true);
        //设置X轴值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return mLineChartDateFormat.format(System.currentTimeMillis());
//                return mLineChartDateFormat.format(mLineChartUpdateTime - (mLineChartCount - value) * mLineChartInterval * 100);
            }
        });


        //Y轴(左)
        YAxis axisLeft = chart.getAxisLeft();
        //设置Y轴线
        axisLeft.setDrawAxisLine(false);
        //设置Y轴文字在内部显示
        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置Y轴文字
//        textColor = Color.parseColor("#434B61")
//        textSize = 12f
        axisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.1f", value / 10);
            }
        });
        axisLeft.setAxisMaximum(420f);
        axisLeft.setAxisMinimum(350);
        axisLeft.enableGridDashedLine(5f, 2f, 0f);
//        addLimitLine(LimitLine(385f).apply {
//            lineColor = Color.parseColor("#FF3912")
//            enableDashedLine(10f, 10f, 0f)
//        })
//        addLimitLine(LimitLine(375f).apply {
//            lineColor = Color.parseColor("#00E69D")
//            enableDashedLine(10f, 10f, 0f)
//        })

        //Y轴(右)
        chart.getAxisRight().setEnabled(false);

        //设置时间标签
        mLineDataSetTime = new LineDataSet(new ArrayList<>(), null);
        //设置数据标签
        mLineDataSetData = new LineDataSet(new ArrayList<>(), null);

        mLineDataSetData.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mLineDataSetData.setDrawCircles(false);//设置是否绘制圆形指示器
        mLineDataSetData.setDrawValues(false); //是否绘制数据值
        mLineDataSetData.setDrawHighlightIndicators(false); //设置是否有拖拽高亮指示器

        for (int i = 0; i < mLineChartCount; i++) {
            mLineDataSetTime.addEntry(new Entry(i, 0f));
        }
        chart.setData(new LineData(mLineDataSetTime, mLineDataSetData));
        chart.getData().setHighlightEnabled(true);


        chart.invalidate();
        setLineChartVisibleCount(mLineChartCount / 30);

    }

    private void setLineChartVisibleCount(int i) {

        chart.fitScreen();
        //设置可见的个数
        chart.setVisibleXRangeMaximum(60);
        chart.moveViewToX((mLineChartCount - 1));
    }


    @OnClick(R.id.mButt)
    public void onViewClicked() {
        List<PointF> list=new ArrayList<>();

        for (int i = 0; i <100 ; i++) {
            PointF pointF=new PointF();
            pointF.x=i*50;
            pointF.y=new Random().nextInt(500)+100;
            list.add(pointF);

        }
        mPulseWaveView.setdata(list);
    }
}
