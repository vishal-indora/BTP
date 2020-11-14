package com.example.ntpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class FrontPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_photo);
        refresh(1000);
    }

    public void changeActivity() {
        Intent intent = new Intent(this, InputBaseUrlActivity.class);
        startActivity(intent);
    }

    public void refresh(int milliSeconds) {
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                changeActivity();
            }
        };
        handler.postDelayed(runnable, milliSeconds);
    }
}