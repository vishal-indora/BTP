package com.example.ntpc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchingAndDisplayingActivity extends AppCompatActivity {

    private TextView textView1_2;
    private TextView textView2_2;
    private TextView textView3_2;
    private TextView textView4_2;
    private TextView textView5_2;
    private TextView textView6_2;
    private TextView textView7_2;
    private TextView textView8_2;
    private TextView textView9_2;
    private TextView textView10_2;
    private TextView textView_previous1_2;
    private TextView textView_previous2_2;
    private TextView textView_previous3_2;
    private TextView textView_previous4_2;
    private TextView textView_previous5_2;
    private TextView textView_previous6_2;
    private TextView textView_previous7_2;
    private TextView textView_previous8_2;
    private TextView textView_previous9_2;
    private TextView textView_previous10_2;
    private TextView textView_previous11_2;
    private TextView textView_previous12_2;
    private TextView textView_previous13_2;
    private TextView textView_previous14_2;
    private TextView textView_current1_2;
    private TextView textView_current2_2;
    private TextView textView_current3_2;
    private TextView textView_current4_2;
    private TextView textView_current5_2;
    private TextView textView_current6_2;
    private TextView textView_current7_2;
    private TextView textView_current8_2;
    private TextView textView_current9_2;
    private TextView textView_current10_2;
    private TextView textView_current11_2;
    private TextView textView_current12_2;
    private TextView textView_current13_2;
    private TextView textView_current14_2;
    private TextView textView_sgPlusOne;
    private TextView textView_sgPlusTwo;
    private TextView textView_sgPlusThree;
    private TextView textView_sgPlusFour;

    private  String content = "";

    private String TimeElapsed;
    private String NextBlockTime;
    private String CurrentBlockNumber;
    private String CurrentBlockTime;
    private String TimeRemaining;
    private String PreviousBlockNumber;

    private String FuelPrice;
    private String Frequency;
    private String Dc;
    private String Sg;
    private String Ag;
    private String AdditionalDeviationCharge;
    private String Deviation_Rs;
    private String Deviation;
    private String DeviationRate;
    private String SgPlusOne;
    private String SgPlusTwo;
    private String SgPlusThree;
    private String SgPlusFour;
    private String NetGain;
    private String FuelCost;
    private String TotalDeviationRupees;
    private String PreviousNetGain;
    private String PreviousAdditionalDeviationCharge;
    private String PreviousFrequency;
    private String PreviousSg;
    private String PreviousDeviationRate;
    private String PreviousDeviation;
    private String PreviousDc;
    private String PreviousBlock;
    private String PreviousFuelCost;
    private String PreviousDeviationRupees;
    private String PreviousTotalDeviationRupees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetching_and_displaying);

        textView1_2 =findViewById(R.id.textView1_2);
        textView2_2 =findViewById(R.id.textView2_2);
        textView3_2 =findViewById(R.id.textView3_2);
        textView4_2 =findViewById(R.id.textView4_2);
        textView5_2 =findViewById(R.id.textView5_2);
        textView6_2 =findViewById(R.id.textView6_2);
        textView7_2 =findViewById(R.id.textView7_2);
        textView8_2 =findViewById(R.id.textView8_2);
        textView9_2 =findViewById(R.id.textView9_2);
        textView10_2 =findViewById(R.id.textView10_2);

        textView_previous1_2 = findViewById(R.id.textView_previous1_2);
        textView_previous2_2 = findViewById(R.id.textView_previous2_2);
        textView_previous3_2 = findViewById(R.id.textView_previous3_2);
        textView_previous4_2 = findViewById(R.id.textView_previous4_2);
        textView_previous5_2 = findViewById(R.id.textView_previous5_2);
        textView_previous6_2 = findViewById(R.id.textView_previous6_2);
        textView_previous7_2 = findViewById(R.id.textView_previous7_2);
        textView_previous8_2 = findViewById(R.id.textView_previous8_2);
        textView_previous9_2 = findViewById(R.id.textView_previous9_2);
        textView_previous10_2 = findViewById(R.id.textView_previous10_2);
        textView_previous11_2 = findViewById(R.id.textView_previous11_2);
        textView_previous12_2 = findViewById(R.id.textView_previous12_2);
        textView_previous13_2 = findViewById(R.id.textView_previous13_2);
        textView_previous14_2 = findViewById(R.id.textView_previous14_2);

        textView_current1_2 = findViewById(R.id.textView_current1_2);
        textView_current2_2 = findViewById(R.id.textView_current2_2);
        textView_current3_2 = findViewById(R.id.textView_current3_2);
        textView_current4_2 = findViewById(R.id.textView_current4_2);
        textView_current5_2 = findViewById(R.id.textView_current5_2);
        textView_current6_2 = findViewById(R.id.textView_current6_2);
        textView_current7_2 = findViewById(R.id.textView_current7_2);
        textView_current8_2 = findViewById(R.id.textView_current8_2);
        textView_current9_2 = findViewById(R.id.textView_current9_2);
        textView_current10_2 = findViewById(R.id.textView_current10_2);
        textView_current11_2 = findViewById(R.id.textView_current11_2);
        textView_current12_2 = findViewById(R.id.textView_current12_2);
        textView_current13_2 = findViewById(R.id.textView_current13_2);
        textView_current14_2 = findViewById(R.id.textView_current14_2);

        textView_sgPlusOne = findViewById(R.id.textView_sgPlusOne);
        textView_sgPlusTwo = findViewById(R.id.textView_sgPlusTwo);
        textView_sgPlusThree = findViewById(R.id.textView_sgPlusThree);
        textView_sgPlusFour = findViewById(R.id.textView_sgPlusFour);

        callApi();

    }

    public void callApi() {
        fetchTimeDataEverySecond();
        fetchPowerPlantDataEverySecond();
        textView1_2.setText(CurrentBlockNumber);
        textView2_2.setText(CurrentBlockTime);
        textView3_2.setText(TimeRemaining);
        textView4_2.setText(TimeElapsed);
        textView5_2.setText(Frequency);
        textView9_2.setText(FuelPrice);
        textView6_2.setText("50.01");

        textView_current1_2.setText(CurrentBlockNumber);
        textView_current2_2.setText(CurrentBlockTime);
        textView_current3_2.setText(Dc);
        textView_current4_2.setText(Sg);
        textView_current8_2.setText(Deviation);
        textView_current9_2.setText(DeviationRate);
        textView_current10_2.setText(Deviation_Rs);
        textView_current11_2.setText(AdditionalDeviationCharge);
        textView_current12_2.setText(TotalDeviationRupees);
        textView_current13_2.setText(FuelCost);
        textView_current14_2.setText(NetGain);

        textView_previous1_2.setText(PreviousBlockNumber);
        textView_current2_2.setText(PreviousBlock);
        textView_previous3_2.setText(PreviousDc);
        textView_previous4_2.setText(PreviousSg);
        textView_previous7_2.setText(PreviousFrequency);
        textView_previous8_2.setText(PreviousDeviation);
        textView_previous9_2.setText(PreviousDeviationRate);
        textView_previous10_2.setText(PreviousDeviationRupees);
        textView_previous11_2.setText(PreviousAdditionalDeviationCharge);
        textView_previous12_2.setText(PreviousTotalDeviationRupees);
        textView_previous13_2.setText(PreviousFuelCost);
        textView_previous14_2.setText(PreviousNetGain);

        textView_sgPlusOne.setText(SgPlusOne);
        textView_sgPlusTwo.setText(SgPlusTwo);
        textView_sgPlusThree.setText(SgPlusThree);
        textView_sgPlusFour.setText(SgPlusFour);

        Button button_previous = (Button) findViewById(R.id.button_previous);
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_previous_function();
            }
        });

        Button button_current = (Button) findViewById(R.id.button_current);
        button_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_current_function();
            }
        });

        Button button_cummu1 = (Button) findViewById(R.id.button_nextSg);
        button_cummu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_cummu1_function();
            }
        });
        refresh(1000);
    }

    public void fetchTimeDataEverySecond() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InputBaseUrlActivity.BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final TimeData timeData = retrofit.create(TimeData.class);

        Call<TimeDataPojo> call = timeData.getPOJOs();
        call.enqueue(new Callback<TimeDataPojo>() {
            @Override
            public void onResponse(Call<TimeDataPojo> call, Response<TimeDataPojo> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Code: " + response.code() , Toast.LENGTH_SHORT).show();
                    return;
                }

                TimeDataPojo timeDataPojo = response.body();
                assert timeDataPojo != null;
                content += "TimeElapsed: " + timeDataPojo.getTimeElapsed();
                content += "\nNextBlockTime: " + timeDataPojo.getNextBlockTime();
                content += "\nCurrentBlockNumber: " + timeDataPojo.getCurrentBlockNumber();
                content += "\nCurrentBlockTime: " + timeDataPojo.getCurrentBlockTime();
                content += "\nTimeRemaining: " + timeDataPojo.getTimeRemaining();

                CurrentBlockNumber = timeDataPojo.getCurrentBlockNumber();
                CurrentBlockTime = timeDataPojo.getCurrentBlockTime();
                TimeRemaining = timeDataPojo.getTimeRemaining();
                TimeElapsed = timeDataPojo.getTimeElapsed();
            }

            @Override
            public void onFailure(Call<TimeDataPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchPowerPlantDataEverySecond() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InputBaseUrlActivity.BaseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PowerPlantData powerPlantData = retrofit.create(PowerPlantData.class);

        Call<PowerPlantDataPojo> call =  powerPlantData.getPOJOs();
        call.enqueue(new Callback<PowerPlantDataPojo>() {
            @Override
            public void onResponse(Call<PowerPlantDataPojo> call, Response<PowerPlantDataPojo> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Code: " + response.code() , Toast.LENGTH_SHORT).show();
                    return;
                }

                PowerPlantDataPojo powerPlantDataPojo = response.body();
                assert powerPlantDataPojo != null;
                content += "\nFuelPrice: " + powerPlantDataPojo.getFuelPrice();
                content += "\nFrequency: " + powerPlantDataPojo.getFrequency();
                content += "\nDc: " + powerPlantDataPojo.getDc();
                content += "\nSg: " + powerPlantDataPojo.getSg();
                content += "\nAg: " + powerPlantDataPojo.getAg();

                FuelPrice = powerPlantDataPojo.getFuelPrice();
                Frequency = powerPlantDataPojo.getFrequency();
                Dc = powerPlantDataPojo.getDc();
                Sg = powerPlantDataPojo.getSg();
                Ag = powerPlantDataPojo.getAg();
                Deviation = powerPlantDataPojo.getDeviation();
                Deviation_Rs = powerPlantDataPojo.getDeviation_Rs();
                DeviationRate = powerPlantDataPojo.getDeviationRate();
                AdditionalDeviationCharge = powerPlantDataPojo.getAdditionalDeviationCharge();
                FuelCost = powerPlantDataPojo.getFuelCost();
                NetGain = powerPlantDataPojo.getNetGain();
                SgPlusOne = powerPlantDataPojo.getSgPlusOne();
                SgPlusTwo = powerPlantDataPojo.getSgPlusTwo();
                SgPlusThree = powerPlantDataPojo.getSgPlusThree();
                SgPlusFour = powerPlantDataPojo.getSgPlusFour();
                TotalDeviationRupees = Double.toString(Double.parseDouble(AdditionalDeviationCharge)+Double.parseDouble(Deviation_Rs));
                PreviousNetGain = powerPlantDataPojo.getPreviousNetGain();
                PreviousAdditionalDeviationCharge = powerPlantDataPojo.getPreviousAdditionalDeviationCharge();
                PreviousFrequency = powerPlantDataPojo.getPreviousFrequency();
                PreviousSg = powerPlantDataPojo.getPreviousSg();
                PreviousDeviationRate = powerPlantDataPojo.getPreviousDeviationRate();
                PreviousDeviation = powerPlantDataPojo.getPreviousDeviation();
                PreviousDc = powerPlantDataPojo.getPreviousDc();
                PreviousBlock = powerPlantDataPojo.getPreviousBlock();
                PreviousFuelCost = powerPlantDataPojo.getPreviousFuelCost();
                PreviousDeviationRupees = powerPlantDataPojo.getPreviousDeviationRupees();
                PreviousBlockNumber = powerPlantDataPojo.getPreviousBlockNumber();
                PreviousTotalDeviationRupees = Double.toString(Double.parseDouble(PreviousAdditionalDeviationCharge)+Double.parseDouble(PreviousDeviationRupees));
            }

            @Override
            public void onFailure(Call<PowerPlantDataPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void button_previous_function() {

        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
        TableLayout tableLayout_3 = (TableLayout) findViewById(R.id.tableLayout_3);

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

    private void button_current_function() {

        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
        TableLayout tableLayout_3 = (TableLayout) findViewById(R.id.tableLayout_3);

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

    private void button_cummu1_function() {

        TableLayout tableLayout_1 = (TableLayout) findViewById(R.id.tableLayout_1);
        TableLayout tableLayout_2 = (TableLayout) findViewById(R.id.tableLayout_2);
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

    public void refresh(int milliSeconds) {
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                callApi();
            }
        };
        handler.postDelayed(runnable, milliSeconds);
    }
}