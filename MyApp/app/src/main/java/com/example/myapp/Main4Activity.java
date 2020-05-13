package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Thread thread = new Thread() {
            public void run() {
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            public void run(){
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat simpleDataFormat = new SimpleDateFormat("hh:mm:ss a");
                                final String currenttime = simpleDataFormat.format(calendar.getTime());
                                setValues(currenttime);
                                Button button_previous = (Button) findViewById(R.id.button_previous);
                                button_previous.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        button_previous_function(currenttime);
                                    }
                                });

                                Button button_current = (Button) findViewById(R.id.button_current);
                                button_current.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        button_current_function(currenttime);
                                    }
                                });

                                Button button_cummu1 = (Button) findViewById(R.id.button_cummul);
                                button_cummu1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        button_cummu1_function(currenttime);
                                    }
                                });
                            }
                        });
                    }
                }
                catch (Exception e) {
                }
            }
        };
        thread.start();
    }

    public void button_previous_function(String s) {
        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
        TableLayout tableLayout_3 = (TableLayout) findViewById(R.id.tableLayout_3);

        String p=s;
        int h = Integer.parseInt(p.substring(0,2));
        int m = Integer.parseInt(p.substring(3,5));
        int sec = Integer.parseInt(p.substring(6, 8));
        if (p.charAt(9) == 'A') {
            h = h % 12;
        }
        else {
            if (h != 12) {
                h += 12;
            }
        }

        //AM and PM
        String h1 = String.valueOf(h);
        String m1 = String.valueOf(m);
        if(h<10)h1="0"+h1;
        if(m<10)h1="0"+m1;
        p = String.valueOf(h1) + " : " + String.valueOf(m1);
        int block = (((h * 60) + m) / 15) + 1;

        if (block != 1) {
            block -= 1;
            TextView textView_previous1_2 = (TextView) findViewById(R.id.textView_previous1_2);
            TextView textView_previous2_2 = (TextView) findViewById(R.id.textView_previous2_2);
            textView_previous1_2.setText(String.valueOf(block));
            int startTime = (block - 1) * 15;
            int endTime = block * 15;

            String startTime_hours = String.valueOf((int) startTime /60) ;
            String startTime_min = String.valueOf(startTime % 60);
            String endTime_hours = String.valueOf((int) endTime / 60);
            String endTime_min = String.valueOf(endTime % 60);
            if(Integer.parseInt(startTime_hours)<10)startTime_hours="0"+startTime_hours;
            if(Integer.parseInt(startTime_min)<10)startTime_min="0"+startTime_min;
            if(Integer.parseInt(endTime_hours)<10)endTime_hours="0"+endTime_min;
            if(Integer.parseInt(endTime_min)<10)endTime_min="0"+endTime_min;

            textView_previous2_2.setText((startTime_hours) + ":" + (startTime_min) +
                    " - " + (endTime_hours) + ":" + (endTime_min));

            TextView textView_previous3_2 = (TextView) findViewById(R.id.textView_previous3_2);
            TextView textView_previous4_2 = (TextView) findViewById(R.id.textView_previous4_2);

            textView_previous3_2.setText(MainActivity.sampleStr[block][2]);
            textView_previous4_2.setText(MainActivity.sampleStr[block][3]);

            float total_f = 0;
            float dev=0;
            dev= Float.parseFloat(MainActivity.sampleStr[block][3]) -Float.parseFloat(MainActivity.sampleStr[block][4]);
            dev = Math.round(dev * 100.0F) / 100.0F;

            for (int i=0; i<block; i++) {
                total_f += Float.parseFloat(MainActivity.sampleStr[i][1]);
            }

            total_f /= block;
            total_f = Math.round(total_f * 100.0F) / 100.0F;

            TextView textView_previous7_2 = (TextView) findViewById(R.id.textView_previous7_2);
            textView_previous7_2.setText(String.valueOf(total_f));
            TextView textView_previous8_2 = (TextView) findViewById(R.id.textView_previous8_2);
            textView_previous8_2.setText(String.valueOf(String.valueOf(dev)));

        }


        if (tableLayout_1.getVisibility() == View.VISIBLE) {
            tableLayout_1.setVisibility(View.GONE);
            tableLayout_2.setVisibility(View.GONE);
            tableLayout_3.setVisibility(View.GONE);
        }
        else {
            tableLayout_1.setVisibility(View.VISIBLE);
            tableLayout_2.setVisibility(View.GONE);
            tableLayout_3.setVisibility(View.GONE);
        }
    }

    public void button_current_function(String s) {
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_3 = (TableLayout) findViewById(R.id.tableLayout_3);
        String p=s;
        int h = Integer.parseInt(p.substring(0,2));
        int m = Integer.parseInt(p.substring(3,5));
        int sec = Integer.parseInt(p.substring(6, 8));
        if (p.charAt(9) == 'A') {
            h = h % 12;
        }
        else {
            if (h != 12) {
                h += 12;
            }
        }

        //AM and PM
        String h1 = String.valueOf(h);
        String m1 = String.valueOf(m);
        if(h<10)h1="0"+h1;
        if(m<10)h1="0"+m1;
        p = String.valueOf(h1) + " : " + String.valueOf(m1);
        int block = (((h * 60) + m) / 15) + 1;

        TextView textView_current1_2 = (TextView) findViewById(R.id.textView_current1_2);
        TextView textView_current2_2 = (TextView) findViewById(R.id.textView_current2_2);
        textView_current1_2.setText(String.valueOf(block));
        int startTime = (block - 1) * 15;
        int endTime = block * 15;

        String startTime_hours = String.valueOf((int) startTime /60) ;
        String startTime_min = String.valueOf(startTime % 60);
        String endTime_hours = String.valueOf((int) endTime / 60);
        String endTime_min = String.valueOf(endTime % 60);
        if(Integer.parseInt(startTime_hours)<10)startTime_hours="0"+startTime_hours;
        if(Integer.parseInt(startTime_min)<10)startTime_min="0"+startTime_min;
        if(Integer.parseInt(endTime_hours)<10)endTime_hours="0"+endTime_min;
        if(Integer.parseInt(endTime_min)<10)endTime_min="0"+endTime_min;

        textView_current2_2.setText((startTime_hours) + ":" + (startTime_min) +
                " - " + (endTime_hours) + ":" + (endTime_min));

        TextView textView_current3_2 = (TextView) findViewById(R.id.textView_current3_2);
        TextView textView_current4_2 = (TextView) findViewById(R.id.textView_current4_2);

        textView_current3_2.setText(MainActivity.sampleStr[block][2]);
        textView_current4_2.setText(MainActivity.sampleStr[block][3]);

        float total_f = 0;
        float dev=0;
        dev= Float.parseFloat(MainActivity.sampleStr[block][3]) -Float.parseFloat(MainActivity.sampleStr[block][4]);
        dev = Math.round(dev * 100.0F) / 100.0F;

        for (int i=0; i<block; i++) {
            total_f += Float.parseFloat(MainActivity.sampleStr[i][1]);
        }

        total_f /= block;
        total_f = Math.round(total_f * 100.0F) / 100.0F;

        TextView textView_current8_2 = (TextView) findViewById(R.id.textView_current8_2);
        textView_current8_2.setText(String.valueOf(String.valueOf(dev)));

        TextView textView_current7_2 = (TextView) findViewById(R.id.textView_current7_2);
        textView_current7_2.setText(String.valueOf(total_f));
        if (tableLayout_2.getVisibility() == View.VISIBLE) {
            tableLayout_2.setVisibility(View.GONE);
            tableLayout_1.setVisibility(View.GONE);
            tableLayout_3.setVisibility(View.GONE);
        }
        else {
            tableLayout_2.setVisibility(View.VISIBLE);
            tableLayout_1.setVisibility(View.GONE);
            tableLayout_3.setVisibility(View.GONE);
        }
    }

    public  void button_cummu1_function(String s){
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_3 = (TableLayout) findViewById(R.id.tableLayout_3);

        if (tableLayout_3.getVisibility() == View.VISIBLE) {
            tableLayout_2.setVisibility(View.GONE);
            tableLayout_1.setVisibility(View.GONE);
            tableLayout_3.setVisibility(View.GONE);
        }
        else {
            tableLayout_3.setVisibility(View.VISIBLE);
            tableLayout_2.setVisibility(View.GONE);
            tableLayout_1.setVisibility(View.GONE);
        }
    }


    public void setValues(String s) {
        TextView textView1_2 = (TextView) findViewById(R.id.textView1_2);
        String p=s;
        int h = Integer.parseInt(p.substring(0,2));
        int m = Integer.parseInt(p.substring(3,5));
        int sec = Integer.parseInt(p.substring(6, 8));
        if (p.charAt(9) == 'A') {
            h = h % 12;
        }
        else {
            if (h != 12) {
                h += 12;
            }
        }

        //AM and PM
        String h1 = String.valueOf(h);
        String m1 = String.valueOf(m);
        if(h<10)h1="0"+h1;
        if(m<10)h1="0"+m1;
        p = String.valueOf(h1) + " : " + String.valueOf(m1);
        int block = (((h * 60) + m) / 15) + 1;
        textView1_2.setText(String.valueOf(block));
        int startTime = (block - 1) * 15;
        int endTime = block * 15;

        String startTime_hours = String.valueOf((int) startTime /60) ;
        String startTime_min = String.valueOf(startTime % 60);
        String endTime_hours = String.valueOf((int) endTime / 60);
        String endTime_min = String.valueOf(endTime % 60);
        int endTimeInSec = Integer.parseInt(endTime_hours) * 3600 + Integer.parseInt(endTime_min) * 60;
        int startTimeInSec = Integer.parseInt(startTime_hours) * 3600 + Integer.parseInt(startTime_min) * 60;
        int curTimeInSec = h * 3600 + m * 60 + sec;
        int remainingInSec = endTimeInSec - curTimeInSec;
        String remaining_min = String.valueOf(remainingInSec / 60);
        String remaining_sec = String.valueOf(remainingInSec % 60);

        int elapsedTimeInSec = curTimeInSec - startTimeInSec;
        String elapsed_min = String.valueOf(elapsedTimeInSec / 60);
        String elapsed_sec = String.valueOf(elapsedTimeInSec % 60);



        if (Integer.parseInt(startTime_hours) < 10){
            startTime_hours = "0" + startTime_hours;
        }
        if (Integer.parseInt(startTime_min) < 10){
            startTime_min = "0" + startTime_min;
        }
        if (Integer.parseInt(endTime_hours) < 10){
            endTime_hours = "0" + endTime_hours;
        }
        if (Integer.parseInt(endTime_min) < 10){
            endTime_min = "0" + endTime_min;
        }
        if (Integer.parseInt(remaining_min) < 10){
            remaining_min = "0" + remaining_min;
        }
        if (Integer.parseInt(remaining_sec) < 10){
            remaining_sec = "0" + remaining_sec;
        }
        if (Integer.parseInt(elapsed_min) < 10){
            elapsed_min = "0" + elapsed_min;
        }
        if (Integer.parseInt(elapsed_sec) < 10){
            elapsed_sec = "0" + elapsed_sec;
        }

        TextView textView2_2 = (TextView) findViewById(R.id.textView2_2);
        textView2_2.setText((startTime_hours) + ":" + (startTime_min) +
                " - " + (endTime_hours) + ":" + (endTime_min));

        TextView textView3_2 = (TextView) findViewById(R.id.textView3_2);
        textView3_2.setText(remaining_min + ":" + remaining_sec);


        TextView textView4_2 = (TextView) findViewById(R.id.textView4_2);
        textView4_2.setText(elapsed_min + ":" + elapsed_sec);


        float total_f = 0;

        for (int i=0; i<block; i++) {
            total_f += Float.parseFloat(MainActivity.sampleStr[i][1]);
        }

        total_f /= block;
        total_f = Math.round(total_f * 100.0F) / 100.0F;

        TextView textView5_2 = (TextView) findViewById(R.id.textView5_2);
        textView5_2.setText(String.valueOf(MainActivity.sampleStr[block-1][1]));

        TextView textView6_2 = (TextView) findViewById(R.id.textView6_2);
        textView6_2.setText(String.valueOf(total_f));


    }

}
