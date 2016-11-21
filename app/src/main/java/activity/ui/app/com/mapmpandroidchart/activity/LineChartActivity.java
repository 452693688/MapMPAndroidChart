package activity.ui.app.com.mapmpandroidchart.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import activity.ui.app.com.mapmpandroidchart.R;

public class LineChartActivity extends AppCompatActivity implements View.OnClickListener {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        chart = (LineChart) findViewById(R.id.line_chart);
        findViewById(R.id.line_chart_rest_tv).setOnClickListener(this);
        ArrayList<String> xVals = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            xVals.add((i + 1) + "月");
        }

        // data1.addDataSet(getDataSet2());
        chart.setData(getDataSet1());
        //chart.setDescription("公司年度利润");
        chart.animateY(3000);
        //设置图表是否支持触控操作
        //chart.setTouchEnabled(false);
        //chart.setScaleEnabled(false);

        //设置折线的描述的样式（默认在图表的左下角）
        XAxis xAxis = chart.getXAxis();
        setX(xAxis);
        YAxis yAxis = chart.getAxisLeft();
        setY(yAxis);

        Description desc = new Description();
        desc.setText("1234");
        chart.setDescription(desc);
        //设置没有数据时的显示文案
        chart.setNoDataText("没有数据");
        //不显示右边的坐标
        chart.getAxisRight().setEnabled(false);
        Legend lgend = chart.getLegend();
        //不显示LineDataSet的标签
        lgend.setEnabled(false);
        chart.setOnChartValueSelectedListener(selectedListener);
        //
        //chart.setRendererLeftYAxis(1);
        chart.setExtraLeftOffset(10);
        //设置图表外，布局内显示的偏移量
        // chart.setExtraOffsets(20, 30, -10, 0);
        //将左边的边放到指定的位置，参数是（float xindex）
        chart.moveViewToX(20);
        //边框是否显示
        chart.setDrawBorders(false);
        //上面的边框颜色
        chart.setBorderColor(0xffff0000);
        chart.setMaxVisibleValueCount(20);
    }

    private LineData getDataSet1() {
        ArrayList<Entry> yVals = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            float profix = random.nextFloat();
            Entry xy = new Entry(i, profix);
            yVals.add(xy);
        }
        LineDataSet dataSet = new LineDataSet(yVals, "颜色");
        setLineDataSet(dataSet);
        //ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        //dataSets.add(dataSet);
        // dataSets.add(dataSet);
        LineData data = new LineData(dataSet);
        return data;
    }

    private void setLineDataSet(LineDataSet dataSet) {

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //折点 是否为圆
        dataSet.setDrawCircles(true);
        //折点 圆圈颜色
        dataSet.setCircleColor(0xffff0000);
        dataSet.setDrawCircleHole(false);
        //线条颜色
        dataSet.setColor(Color.YELLOW);
        //设置线条宽度
        //dataSet.setLineWidth(10);
        //选中颜色
        dataSet.setHighLightColor(0xffff3333);
        //折点 圆圈大小
        //dataSet.setCircleSize(20);
        //true:线条和底部形成闭环
        dataSet.setDrawFilled(false);
        //不会显示折点上的value
        dataSet.setDrawValues(false);
        //线条和底部形成闭环区内的颜色
        //dataSet.setFillColor(0xffff3333);
        //曲线 弯度
        //dataSet.setCubicIntensity(10);
        //设置x轴的样式
        // ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        // dataSet.setAxisDependency( YAxis.AxisDependency.LEFT);

    }

    //x和y通用
    public void AxisBase(AxisBase axisBase) {
        axisBase.setEnabled(true);     //是否显示X坐标轴 及 对应的刻度竖线，默认是true
        axisBase.setDrawAxisLine(true); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        axisBase.setDrawGridLines(false); //是否显示X坐标轴上的刻度竖线，默认是true
        axisBase.setDrawLabels(true); //是否显示X坐标轴上的刻度，默认是true
        axisBase.setTextColor(Color.rgb(145, 13, 64)); //X轴上的刻度的颜色
        axisBase.setTextSize(5); //X轴上的刻度的字的大小 单位dp
//      xAxis.setTypeface(Typeface tf); //X轴上的刻度的字体
        axisBase.setGridColor(Color.rgb(145, 13, 64)); //X轴上的刻度竖线的颜色

        // xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        // xAxis.enableGridDashedLine(40, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线
    }

    private void setX(XAxis xAxis) {
        xAxis.setDrawGridLines(false); //是否显示X坐标轴上的刻度竖线，默认是true
        //-----------------------------------
        //x 基线在底部 标示在基线下面
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        //设置标签数量
        xAxis.setLabelCount(14);
        //设置标签旋转角度
        // xAxis.setLabelRotationAngle(45);
        //避免第一个和最后一个裁剪
        xAxis.setAvoidFirstLastClipping(true);
        // xAxis.setSpaceBetweenLabels(50);
        xAxis.resetAxisMaximum();
        xAxis.resetAxisMinimum();
        xAxis.setAxisMaximum(13);    //设置X轴坐标最大为多少
        xAxis.setAxisMinimum(0);    //设置X轴坐标最小为多少
    }

    private void setY(YAxis yAxis) {

        // AxisBase(yAxis);
        // Y轴专用
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLimitLinesBehindData(false);
        yAxis.setSpaceTop(10);    //Y轴坐标距顶有多少距离，即留白
        yAxis.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //yAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        //  yAxis.setValueFormatter(formatter);
        yAxis.resetAxisMaximum();
        yAxis.resetAxisMinimum();
        yAxis.setAxisMaximum(13);    //设置Y轴坐标最大为多少
        yAxis.setAxisMinimum(0);    //设置Y轴坐标最小为多少
        yAxis.setLabelCount(20);

    }

    private LineDataSet getDataSet2() {
        ArrayList<Entry> yVals = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            float profix = random.nextFloat();
            yVals.add(new Entry(profix, i));
        }
        LineDataSet dataSet = new LineDataSet(yVals, "");
        dataSet.setColors(new int[]{0xffff0000});
        return dataSet;
    }

    //设置x或y轴基线的标签
    final String[] quarters = new String[]{"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

    IAxisValueFormatter formatter = new IAxisValueFormatter() {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return quarters[(int) value];
        }

        // we don't draw numbers, so no decimal digits needed
        @Override
        public int getDecimalDigits() {
            return 0;
        }
    };
    private OnChartValueSelectedListener selectedListener = new OnChartValueSelectedListener() {


        @Override
        public void onValueSelected(Entry entry, Highlight highlight) {
            Toast.makeText(LineChartActivity.this, String.valueOf(entry.getData()), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(LineChartActivity.this, "什么", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onClick(View v) {
        Log.e("-------------", "===============");
        LineData data = chart.getData();
        data.clearValues();
        chart.setData(getDataSet1());
        chart.notifyDataSetChanged();
        chart.invalidate();
    }
}
