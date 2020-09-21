package com.example.ntpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputBaseUrlActivity extends AppCompatActivity {

    private EditText editText;

    private Button submitButton;

    static String BaseURl;

    final String VALID_BASE_URL = "http://ec2-54-236-56-160.compute-1.amazonaws.com/" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_base_url);

        editText = (EditText)findViewById(R.id.enterUrl);
        submitButton = (Button)findViewById(R.id.submitButton);
        final Intent intent = new Intent(this, FetchingAndDisplayingActivity.class);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseURl = editText.getText().toString();
                if(VALID_BASE_URL.equals(BaseURl)) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Base URL" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}