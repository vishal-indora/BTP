package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView theTextView = (TextView) findViewById(R.id.textView);
        String s = "Time, Frequency, DC, SG, AG";
        s += "\n";
        for (int i=0; i < MainActivity.l; i++) {
            s += MainActivity.sampleStr[i][0] + ", ";
            s += MainActivity.sampleStr[i][1] + ", ";
            s += MainActivity.sampleStr[i][2] + ", ";
            s += MainActivity.sampleStr[i][3] + ", ";
            s += MainActivity.sampleStr[i][4] + "\n";

        }
        theTextView.setText(s);

    }
}
