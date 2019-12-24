package com.lixiang.androidmvpdemp.utils;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民健康
 * @Description: java类作用描述
 * @Author: lixiang
 * @CreateDate: 2019/11/5 14:03
 * @UpdateUser: 创造者
 * @UpdateDate: 2019/11/5 14:03
 * @UpdateRemark: 更新说明
 * @Version: 0.2
 */
public class LineManage {


    /**
     * 功能：设置图标的基本属性
     */
    public static void setChartBasicAttr(LineChart lineChart) {
        /***图表设置***/
        lineChart.setDrawGridBackground(false); //是否展示网格线
        lineChart.setDrawBorders(true); //是否显示边界
        lineChart.setDragEnabled(true); //是否可以拖动
        lineChart.setScaleEnabled(true); // 是否可以缩放
        lineChart.setTouchEnabled(true); //是否有触摸事件
        //设置XY轴动画效果
        lineChart.animateY(500);
        lineChart.animateX(500);
    }

    /**
     * 功能：设置XY轴
     */
    public static void setXYAxis(LineChart lineChart, XAxis xAxis, YAxis leftYAxis, YAxis rightYAxis) {
        /***XY轴的设置***/
        //设置竖网格
        xAxis.setDrawGridLines(false);
        //设置X轴线
        xAxis.setDrawAxisLine(false);
        //设置X轴文字
        //设置X轴避免图表或屏幕的边缘的第一个和最后一个轴中的标签条目被裁剪
//        xAxis.setAvoidFirstLastClipping(true);
        //设置X轴值
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //X轴设置显示位置在底部
        xAxis.setAxisMinimum(0f); // 设置X轴的最小值
        xAxis.setAxisMaximum(Integer.MAX_VALUE); // 设置X轴的最大值
        xAxis.setLabelCount(30, false); // 设置X轴的刻度数量，第二个参数表示是否平均分配
//        xAxis.setGranularity(1f); // 设置X轴坐标之间的最小间隔


        //Y轴(右)
        rightYAxis.setEnabled(false);
        //Y轴(左)
        //设置Y轴线
        leftYAxis.setDrawAxisLine(false);
        //设置Y轴文字在内部显示
        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftYAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                return String.format("%.2f", value);
            }
        });
//        axisLeft.setAxisMaximum(200);
//        axisLeft.setAxisMinimum(-1);
        leftYAxis.setLabelCount(5, true);


        //        lineChart.setVisibleYRangeMaximum(30, YAxis.AxisDependency.LEFT);// 当前统计图表中最多在Y轴坐标线上显示的总量
//        lineChart.setVisibleYRangeMaximum(30, YAxis.AxisDependency.RIGHT);// 当前统计图表中最多在Y轴坐标线上显示的总量
        lineChart.fitScreen();
        lineChart.setVisibleXRangeMaximum(30);// 当前统计图表中最多在x轴坐标线上显示的总量
        lineChart.setDescription(null);//设置描写
        lineChart.setNoDataText("没有得到数据");//没有数据时显示的文字
        lineChart.getLegend().setEnabled(false); //设置图例关
        lineChart.setDrawBorders(false); //设置是否显示边界
//        chart.setBackgroundColor(Color.WHITE);//设置背景色
        lineChart.setAutoScaleMinMaxEnabled(true);

        //设置触摸(关闭影响下面3个属性)
        lineChart.setTouchEnabled(true);
        //设置是否可以拖拽
        lineChart.setDragEnabled(true);
        //设置是否可以缩放
        lineChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        lineChart.setPinchZoom(false);
    }


    /**
     * 功能：创建图例
     */
    public static void createLegend(Legend legend) {
        /***折线图例 标签 设置***/
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
//        legend.setEnabled(true);
        legend.setEnabled(false);
    }


    /**
     * 功能：动态创建一条曲线
     */
    public static void createLine(List<Float> dataList, List<Entry> entries, LineDataSet lineDataSet, int color, LineData lineData, LineChart lineChart) {
        for (int i = 0; i < dataList.size(); i++) {
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, dataList.get(i));// Entry(x,y)
            entries.add(entry);
        }

        // 初始化线条
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.CUBIC_BEZIER);

        if (lineData == null) {
            lineData = new LineData();
            lineData.addDataSet(lineDataSet);
            lineChart.setData(lineData);
        } else {
            lineChart.getLineData().addDataSet(lineDataSet);
        }

        lineChart.invalidate();
    }

    /**
     * 曲线初始化设置,一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private static void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {

        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(color);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawCircles(false);//设置是否绘制圆形指示器
        lineDataSet.setDrawValues(false); //是否绘制数据值
        lineDataSet.setDrawHighlightIndicators(false); //设置是否有拖拽高亮指示器

//
//        lineDataSet.setColor(color); // 设置曲线颜色
//        lineDataSet.setCircleColor(color);  // 设置数据点圆形的颜色
//        lineDataSet.setDrawCircleHole(false);// 设置曲线值的圆点是否是空心
//        lineDataSet.setLineWidth(1f); // 设置折线宽度
//        lineDataSet.setCircleRadius(3f); // 设置折现点圆点半径
//        lineDataSet.setValueTextSize(10f);
//
//        lineDataSet.setDrawFilled(true); //设置折线图填充
//        lineDataSet.setFormLineWidth(1f);
//        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }

    }


    /**
     * 动态添加数据
     * 在一个LineChart中存放的折线，其实是以索引从0开始编号的
     *
     * @param yValues y值
     */
    public static void addEntry(LineData lineData, LineChart lineChart, float yValues, int index) {

        // 通过索引得到一条折线，之后得到折线上当前点的数量
        int xCount = lineData.getDataSetByIndex(index).getEntryCount();


        Entry entry = new Entry(xCount, yValues); // 创建一个点
        lineData.addEntry(entry, index); // 将entry添加到指定索引处的折线中

        //通知数据已经改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();

        //把yValues移到指定索引的位置
        lineChart.moveViewToAnimated(xCount - 30, yValues, YAxis.AxisDependency.LEFT, 0);// TODO: 2019/5/4 内存泄漏，异步 待修复
        lineChart.invalidate();
    }


}
