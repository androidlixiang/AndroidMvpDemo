package com.lixiang.androidmvpdemp.line;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.lixiang.androidmvpdemp.R;

import java.util.List;

/**
 * Created by lixiang on 2018/11/28
 */
public class LineChartManage {
    private XAxis xAxis;
    private YAxis leftYAxis;
    private LineChart lineChart;
    private Activity activity;

    public LineChartManage(Activity lineActivity, @NonNull LineChart lineChart) {
        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        this.lineChart = lineChart;
        this.activity = lineActivity;
        initChart();
    }

    /**
     * 初始化图表
     */
    private void initChart() {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        //是否有触摸事件
//        lineChart.setTouchEnabled(false);

        //设置XY轴动画效果
        lineChart.animateY(500);
        lineChart.animateX(500);
        Description description = new Description();
        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);
        lineChart.setEnabled(true);
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示


        /***折线图例 标签 设置***/
        Legend legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(false);
    }

    /**
     * 展示曲线
     *
     * @param entries 数据集合
     * @param name    曲线名称
     */
    public void showLineChart(List<Entry> entries, String name) {


        /******根据需求的不同 在此在次设置X Y轴的显示内容******/
        xAxis.setLabelCount(8, false);

        xAxis.setDrawGridLines(false);

        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        //设置是否绘制刻度
//        xAxis.setDrawScale(false);
        //是否绘制X轴线
        xAxis.setDrawAxisLine(false);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + "";
            }
        });

        //设置Y轴网格线为虚线
        leftYAxis.enableGridDashedLine(10f, 10f, 0f);
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftYAxis.setEnabled(false);
        leftYAxis.setLabelCount(5,true);
        leftYAxis.setAxisLineColor(Color.parseColor("#0000ff"));
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(130);
        leftYAxis.setDrawZeroLine(false); // draw a zero line
        leftYAxis.setZeroLineColor(R.color.c_aaaaaa);
        leftYAxis.setZeroLineWidth(1);
        leftYAxis.setAxisLineWidth(1);


        leftYAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                return String.format("%.2f", value);
            }
        });

        ValueFormatter valueFormatter = leftYAxis.getValueFormatter();
        for (Entry entry : entries) {
            String pointLabel = valueFormatter.getPointLabel(entry);
        }


        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        //LINEAR 折线图  CUBIC_BEZIER 圆滑曲线
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        lineDataSet.setCircleColors(Color.parseColor("#FF0000"),Color.parseColor("#00FF00"));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setHighLightColor(Color.TRANSPARENT);

//        设置填充颜色
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.fade_red);
        lineDataSet.setFillDrawable(drawable);
        lineDataSet.setDrawFilled(true);
//
        //线条自定义内容 放在这里
        lineDataSet.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                return (int) value + "啦啦啦";
            }

        });
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    /**
     * 设置 可以显示X Y 轴自定义值的 MarkerView
     */
    public void setMarkerView(Context context) {
        //折线图点的标记
        MyMarkerView mv = new MyMarkerView(context);
        lineChart.setMarker(mv);
        lineChart.invalidate();
    }

}
