package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // X and Y values for graph
        Float[] sample_time_hours = new Float[96];
        Float[] sample_sg = new Float[96];
        Float[] sample_ag = new Float[96];

        for (int i=0; i<96; i++) {
            sample_time_hours[i] = Float.parseFloat(MainActivity.sampleStr[i][0].substring(0, 2)) + ((Float.parseFloat(MainActivity.sampleStr[i][0].substring(3, 5))) / 60);
            sample_sg[i] = Float.parseFloat(MainActivity.sampleStr[i][3]);
            sample_ag[i] = Float.parseFloat(MainActivity.sampleStr[i][4]);
        }

        TextView theTextView = (TextView) findViewById(R.id.textViewGraph);



    }
}
