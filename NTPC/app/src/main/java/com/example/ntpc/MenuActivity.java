package com.example.ntpc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button MonitorData, PlotData, AboutUs;
    private Dialog AboutUsDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MonitorData = (Button) findViewById(R.id.buttonMonitorData);
        intent = new Intent(this, InputBaseUrlActivity.class);
        MonitorData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        AboutUsDialog = new Dialog(this);

        AboutUs = (Button) findViewById(R.id.buttonAboutUs);
        AboutUsDialog.setContentView(R.layout.about_us);
        AboutUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAboutUsPopup();
            }
        });
    }

    public void showAboutUsPopup() {
        AboutUsDialog.show();
    }
}