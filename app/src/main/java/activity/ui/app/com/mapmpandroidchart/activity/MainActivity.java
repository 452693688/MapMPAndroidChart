package activity.ui.app.com.mapmpandroidchart.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import activity.ui.app.com.mapmpandroidchart.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.type_1_btn).setOnClickListener(this);
        findViewById(R.id.type_2_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent();
        Class<?> cls = null;
        switch (v.getId()) {
            case R.id.type_1_btn:
                //图柱
                cls = BarChartActivity.class;
                break;
            case R.id.type_2_btn:
                //折线
                cls = LineChartActivity.class;
                break;
        }
        it.setClass(this, cls);
        startActivity(it);
    }
}
