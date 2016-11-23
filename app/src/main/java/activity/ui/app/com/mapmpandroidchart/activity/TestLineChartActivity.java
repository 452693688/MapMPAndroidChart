package activity.ui.app.com.mapmpandroidchart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import activity.ui.app.com.mapmpandroidchart.R;
import activity.ui.app.com.mapmpandroidchart.test.ChartTest;
import activity.ui.app.com.mapmpandroidchart.test.TestManager;
import activity.ui.app.com.mapmpandroidchart.test.TextViewManager;

public class TestLineChartActivity extends AppCompatActivity {

    private LineChart chart;
    private TextView unitTv;
    private TextView valueTv;
    private TextView[] indicateTvs = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lin_chart);
        chart = (LineChart) findViewById(R.id.chart_line);
        unitTv = (TextView) findViewById(R.id.chart_unit_tv);
        valueTv = (TextView) findViewById(R.id.chart_value_tv);
        indicateTvs[0] = (TextView) findViewById(R.id.indicate_1_tv);
        indicateTvs[1] = (TextView) findViewById(R.id.indicate_2_tv);
        indicateTvs[2] = (TextView) findViewById(R.id.indicate_3_tv);
        indicateTvs[3] = (TextView) findViewById(R.id.indicate_4_tv);
        setData();
    }

    public void setData() {
        ChartTest bean = TestManager.getChartTest();
        String[] tag = getUnitValue(bean);
        unitTv.setText(tag[0]);
        valueTv.setText(tag[1]);
        String[] values = getIndicateValue(bean);
        int[] icons = getIndicateIcon(bean);
        for (int i = 0; i < icons.length; i++) {
            TextViewManager.setText(this, indicateTvs[i], icons[i], values[i], 2);
        }
        setChart(bean, icons.length);
    }

    private String[] getUnitValue(ChartTest bean) {
        String[] tag = new String[2];
        switch (bean.type) {
            case 1:
                tag[0] = "（mmHg）";
                tag[1] = "血压";
                break;
            case 2:
                tag[0] = "（mmol/L）";
                tag[1] = "血糖";
                break;
            case 3:
                tag[0] = "（mmol）";
                tag[1] = "血脂";
                break;
            case 4:
                tag[0] = "（s）";
                tag[1] = "血凝";
                break;

        }
        return tag;
    }

    public int[] getIndicateIcon(ChartTest bean) {
        int[] icon = null;
        switch (bean.type) {
            case 1:
                icon = new int[2];
                icon[0] = R.mipmap.line_3_tag;
                icon[1] = R.mipmap.line_4_tag;
                break;
            case 2:
                icon = new int[2];
                icon[0] = R.mipmap.line_4_tag;
                icon[1] = R.mipmap.line_3_tag;
                break;
            case 3:
                icon = new int[4];
                icon[0] = R.mipmap.line_1_tag;
                icon[1] = R.mipmap.line_2_tag;
                icon[2] = R.mipmap.line_3_tag;
                icon[3] = R.mipmap.line_4_tag;
                break;
            case 4:
                icon = new int[2];
                icon[0] = R.mipmap.line_1_tag;
                icon[1] = R.mipmap.line_4_tag;
                icon[2] = R.mipmap.line_3_tag;
                break;

        }
        return icon;
    }

    private String[] getIndicateValue(ChartTest bean) {
        String[] values = null;
        switch (bean.type) {
            case 1:
                values = new String[2];
                values[0] = "收缩压";
                values[1] = "舒张压";
                break;
            case 2:
                values = new String[2];
                values[0] = "餐前";
                values[1] = "餐后";
                break;
            case 3:
                values = new String[4];
                values[0] = "TC";
                values[1] = "TG";
                values[2] = "LDL-C";
                values[3] = "HDL-C";
                break;
            case 4:
                values = new String[3];
                values[0] = "PT";
                values[1] = "APTT";
                values[2] = "TT";
                break;

        }
        return values;
    }

    private int[] getLinColors(ChartTest bean) {

        int[] colors = new int[]{0xff62D9D5, 0xffF47E62, 0xff35B197, 0xffF4B91F};
        int[] icon = null;
        switch (bean.type) {
            case 1:
                icon = new int[2];
                icon[0] = colors[2];
                icon[1] = colors[3];
                break;
            case 2:
                icon = new int[2];
                icon[0] = colors[3];
                icon[1] = colors[2];
                break;
            case 3:
                icon = new int[4];
                icon[0] = colors[0];
                icon[1] = colors[1];
                icon[2] = colors[2];
                icon[3] = colors[3];
                break;
            case 4:
                icon = new int[2];
                icon[0] = colors[0];
                icon[1] = colors[3];
                icon[2] = colors[2];
                break;

        }
        return icon;
    }

    private void setChart(ChartTest bean, int lienSize) {
        chart.setData(getDataSet(bean, lienSize));
        chart.animateY(3000);
        //设置折线的描述的样式（默认在图表的左下角）
        XAxis xAxis = chart.getXAxis();
        YAxis yAxis = chart.getAxisLeft();
        setXY(xAxis, yAxis);
        Description desc = new Description();
        desc.setText("");
        chart.setDescription(desc);
        //不显示右边的坐标
        chart.getAxisRight().setEnabled(false);
        Legend lgend = chart.getLegend();
        //不显示LineDataSet的标签
        lgend.setEnabled(false);
    }

    private LineData getDataSet(ChartTest bean, int lienSize) {
        ArrayList<ArrayList<Entry>> lines = bean.lines;
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        int[] colors = getLinColors(bean);
        for (int i = 0; i < lienSize; i++) {
            ArrayList<Entry> entrys = lines.get(i);
            LineDataSet dataSet = new LineDataSet(entrys, "");
            int color = colors[i];
            setLineDataSet(dataSet, color);
            dataSets.add(dataSet);
        }
        LineData data = new LineData(dataSets);
        return data;
    }

    private void setLineDataSet(LineDataSet dataSet, int color) {
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //折点 是否为圆
        dataSet.setDrawCircles(true);
        //折点 圆圈颜色
        dataSet.setCircleColor(color);
        dataSet.setDrawCircleHole(false);
        //线条颜色
        dataSet.setColor(color);
        //选中颜色
        dataSet.setHighLightColor(0x0000);
        dataSet.setDrawValues(false);
    }

    private void setXY(XAxis xAxis, YAxis yAxis) {
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(13);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setAxisMaximum(9);
        xAxis.setAxisMinimum(0);
        xAxis.setLabelCount(9);
        //---------------
        yAxis.setDrawGridLines(false);
        yAxis.setDrawLimitLinesBehindData(false);
        yAxis.setSpaceTop(10);
        yAxis.setSpaceBottom(0);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
    }


    //设置x或y轴基线的标签
    final String[] quarters = new String[]{"0", "1", "2", "3", "4",
            "5", "6", "7","（ 日期 ） ", "", "10", "11", "12"};

    IAxisValueFormatter formatter = new IAxisValueFormatter() {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.e("=====",""+value);
            return quarters[(int) value];
        }

        // we don't draw numbers, so no decimal digits needed
        @Override
        public int getDecimalDigits() {
            return 0;
        }
    };
}
