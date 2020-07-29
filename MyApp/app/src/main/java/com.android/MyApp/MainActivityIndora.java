package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            String url = "https://192.168.56.1/hello1.json";
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            Toast.makeText(this, "pehle", Toast.LENGTH_SHORT).show();
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            Toast.makeText(this, "after", Toast.LENGTH_SHORT).show();
//            String inputLine ="";
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in .readLine()) != null) {
//                response.append(inputLine);
//            } in.close();
//            //print in String
//            System.out.println(response.toString());
//            //Read JSON response and print
//            JSONObject myResponse = new JSONObject(response.toString());
//            System.out.println("Result after Reading JSON Response");
//            JSONObject  childObject = myResponse.getJSONObject("Data");
//            JSONObject  innerChildObject = childObject.getJSONObject("PreviousBlock");
//            System.out.println("Time block- "+myResponse.getString("TimeBlock"));
//            System.out.println("Sg- " + (childObject.get("SG")));
//            System.out.println("Ag- "+(childObject.get("AG")));
//            System.out.println("Previous block Ag- "+(innerChildObject.get("AG")));
//            System.out.println("Previous block Sg- "+(innerChildObject.get("SG")));
//            System.out.println("Frequency- "+myResponse.getString("Freq"));
//
//            Log.d("demo",myResponse.getString("TimeBlock"));
//            Toast.makeText(this,myResponse.getString("TimeBlock"), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            // TODO: handle exception
//            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//        }


        try {
            String _output = null;
            URL url = new URL("https://192.168.56.1/hello1.json");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder everything = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                everything.append(line);
            }
            _output = everything.toString();

            buffer.close();
            Toast.makeText(this, "after", Toast.LENGTH_SHORT).show();
            System.out.print(_output);
        } catch (IOException e) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

    }

//    public static void main(String[] args) {
//
//    }

}