package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readData();
        TextView theTextView = (TextView) findViewById(R.id.textView);
        theTextView.setText("SUCCESS");
    }


    public static List<DataSample> dataSamples = new ArrayList<>();
    public static int l;
    public static String[][] sampleStr = new String[96][5];
    public void readData() {
        InputStream is = getResources().openRawResource(R.raw.book2);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        int i = 0;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                DataSample sample = new DataSample();
                sample.setTime(tokens[0]);
                sample.setFrequency(tokens[1]);
                sample.setDc((tokens[2]));
                sample.setSg((tokens[3]));
                sample.setAg((tokens[4]));
                dataSamples.add(sample);
                String k = sample.getTime();
                Log.d("MyActivity", "Just created: " + dataSamples);
                sampleStr[i][0] = sample.getTime();
                sampleStr[i][1] = sample.getFrequency();
                sampleStr[i][2] = sample.getDc();
                sampleStr[i][3] = sample.getSg();
                sampleStr[i][4] = sample.getAg();
                i += 1;

            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error" + line, e);
            e.printStackTrace();
        }
        l=dataSamples.size();
    }
}
