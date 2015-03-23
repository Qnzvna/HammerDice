package tk.owab.hammerdice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class ChartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int dices = intent.getIntExtra(MainActivity.DICES, 1);
        double toHit = intent.getDoubleExtra(MainActivity.HIT, 1);
        double toWound = intent.getDoubleExtra(MainActivity.WOUND, 1);
        double toASave = intent.getDoubleExtra(MainActivity.ASAVE, 0);

        //Log.d("Hammer", dices+" dices");

        ArrayList<BarEntry> probsHigh = new ArrayList<BarEntry>();
        //ArrayList<BarEntry> probsLow = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        int prob;
        for (int i = 1; i <= dices; i++) {
            prob = (int) Math.round(DiceProb.fightSimulator(dices, i, toASave, toWound, toHit) * 100);
            if (prob != 0) {
                probsHigh.add(new BarEntry(prob, i - 1));
                xVals.add(i+"");
            }
        }

        //BarDataSet comp1 = new BarDataSet(probsLow, "");
        //comp1.setColor(Color.RED);

        BarDataSet comp2 = new BarDataSet(probsHigh, "");
        comp2.setColor(Color.BLUE);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(comp2);
        //dataSets.add(comp1);

        setContentView(R.layout.activity_chart);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(xVals, dataSets);
        chart.setData(data);

        chart.getLegend().setEnabled(false);
        chart.getXAxis().setSpaceBetweenLabels(0);
        chart.setDescription("");

        chart.invalidate();

    }

}
