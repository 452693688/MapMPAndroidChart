package activity.ui.app.com.mapmpandroidchart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import activity.ui.app.com.mapmpandroidchart.R;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = (BarChart) findViewById(R.id.bar_chart);

        /**图表具体设置*/
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        ArrayList<String> xVals = new ArrayList<String>();//横坐标标签
        Random random = new Random();//随机数
        for (int i = 0; i < 12; i++) {
            float profit = random.nextFloat() * 1000;
            //entries.add(BarEntry(float val,int positon);
            entries.add(new BarEntry(profit, i));
            xVals.add((i + 1) + "月");
        }
        BarDataSet dataSet = new BarDataSet(entries, "公司年利润报表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataSet);
        chart.setData(data);
        //设置Y方向上动画animateY(int time);
        chart.animateY(3000);
        //图表描述
        // chart.setDescription("公司前半年财务报表(单位：万元)");
    }
}
