package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "Main5Activity";

    private LineChart mChartAg;
    private LineChart mChartSg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mChartAg = (LineChart) findViewById(R.id.linechartAg);
        mChartSg = (LineChart) findViewById(R.id.linechartSg);

        // X and Y values for graph
        Float[] sample_time_hours = new Float[96];
        Float[] sample_sg = new Float[96];
        Float[] sample_ag = new Float[96];
        ArrayList<Entry> yValuesSg = new ArrayList<>();
        ArrayList<Entry> yValuesAg = new ArrayList<>();

        for (int i=0; i<96; i++) {
            sample_time_hours[i] = Float.parseFloat(MainActivity.sampleStr[i][0].substring(0, 2)) + ((Float.parseFloat(MainActivity.sampleStr[i][0].substring(3, 5))) / 60);
            sample_sg[i] = Float.parseFloat(MainActivity.sampleStr[i][3]);
            sample_ag[i] = Float.parseFloat(MainActivity.sampleStr[i][4]);
        }


        for (int i=0; i<96; i++) {
            yValuesSg.add(new Entry(sample_time_hours[i], sample_sg[i]));
            yValuesAg.add(new Entry(sample_time_hours[i], sample_ag[i]));
        }


        LineDataSet setSg = new LineDataSet(yValuesSg, "SG");
        setSg.setFillAlpha(110);

        LineDataSet setAg = new LineDataSet(yValuesSg, "AG");
        setAg.setFillAlpha(110);

        //Aesthetics
        setAg.setColor(Color.GREEN);
        setAg.setCircleColor(Color.RED);
        setAg.setLineWidth(3f);
        setAg.setValueTextSize(0);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setAg);

        LineData data = new LineData(dataSets);
        mChartAg.setData(data);



        // mChart.setOnChartGestureListener(Main5Activity.this);
        // mChart.setOnChartValueSelectedListener(Main5Activity.this);

        mChartAg.setDragEnabled(true);
        mChartAg.setScaleEnabled(false);



    }
}
