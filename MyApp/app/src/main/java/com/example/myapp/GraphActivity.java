package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private static final String TAG = "GraphActivity";

    private LineChart mChartAg;
    private LineChart mChartSg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mChartAg = (LineChart) findViewById(R.id.linechartAg);
        mChartSg = (LineChart) findViewById(R.id.linechartSg);

        Button button_ag = (Button) findViewById(R.id.buttonAg);
        Button button_sg = (Button) findViewById(R.id.buttonSg);

        button_ag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChartSg.setVisibility(View.GONE);
                if (mChartAg.getVisibility() == View.VISIBLE) {
                    mChartAg.setVisibility(View.GONE);
                }
                else {
                    mChartAg.setVisibility(View.VISIBLE);
                }
            }
        });

        button_sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChartAg.setVisibility(View.GONE);
                if (mChartSg.getVisibility() == View.VISIBLE) {
                    mChartSg.setVisibility(View.GONE);
                }
                else {
                    mChartSg.setVisibility(View.VISIBLE);
                }
            }
        });








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

        LineDataSet setAg = new LineDataSet(yValuesAg, "AG");
        setAg.setFillAlpha(110);

        //Aesthetics
        setAg.setColor(Color.BLUE);
        setAg.setCircleColor(Color.BLUE);
        setAg.setLineWidth(4f);
        setAg.setValueTextSize(0);

        setSg.setColor(Color.RED);
        setSg.setCircleColor(Color.RED);
        setSg.setLineWidth(4f);
        setSg.setValueTextSize(0);

        //Information
        Description descriptionAg = mChartAg.getDescription();
        descriptionAg.setEnabled(false);
        Description descriptionSg = mChartSg.getDescription();
        descriptionSg.setEnabled(false);


        ArrayList<ILineDataSet> dataSetsAg = new ArrayList<>();
        dataSetsAg.add(setAg);

        ArrayList<ILineDataSet> dataSetsSg = new ArrayList<>();
        dataSetsSg.add(setSg);

        LineData dataAg = new LineData(dataSetsAg);
        mChartAg.setData(dataAg);

        LineData dataSg = new LineData(dataSetsSg);
        mChartSg.setData(dataSg);

        mChartAg.setDragEnabled(true);
        mChartAg.setScaleEnabled(true);
        mChartSg.setDragEnabled(true);
        mChartSg.setScaleEnabled(true);



    }
}
