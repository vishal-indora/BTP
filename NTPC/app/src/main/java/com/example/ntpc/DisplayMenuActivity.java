package com.example.ntpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisplayMenuActivity extends AppCompatActivity {

    private Button MonitorData, PlotData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);

        MonitorData = (Button) findViewById(R.id.buttonMonitorData);
        PlotData = (Button) findViewById(R.id.buttonPlotData);
        final Intent intent1 = new Intent(this, FetchingAndDisplayingActivity.class);
        final Intent intent2 = new Intent(this, PlotPredictionActivity.class);

        MonitorData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        PlotData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
    }
}