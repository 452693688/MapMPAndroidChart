package activity.ui.app.com.mapmpandroidchart.test;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/21.
 */

public class TestManager {
    public static ChartTest getChartTest() {
        ChartTest test = new ChartTest();
        test.type = 1;
        test.lines = new ArrayList<ArrayList<Entry>>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            ArrayList<Entry> entrys = new ArrayList<Entry>();
            for (int j = 0; j < 31; j++) {
                 if (j==0||j==5||j==9||j==30){
                     Entry entry = new Entry();
                     entry.setX(j +3);
                     entry.setY(random.nextFloat());
                     entrys.add(entry);
                 }

            }
            test.lines.add(entrys);
        }

        return test;
    }
}
