package com.example.temepraturethroughcricketchirps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etNoOfChirps;
    Button buttonNoOfChirps;

    Button buttonPlayTicTacToe;
    TextView tvTemperatureResult;

    DecimalFormat temperatureResultFormatter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        etNoOfChirps = findViewById(R.id.etNoOfChirps);
        buttonNoOfChirps = findViewById(R.id.buttonNoOfChirps);
        buttonPlayTicTacToe = findViewById(R.id.buttonPlayTicTacToe);
        tvTemperatureResult = findViewById(R.id.tvTemperatureResult);

        tvTemperatureResult.setVisibility(View.GONE);

        temperatureResultFormatter = new DecimalFormat("0.0");

        buttonNoOfChirps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                double noOfChirps;
                double approximateTemperature;
                String tvTemperatureResultText;

                if(etNoOfChirps.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter some number", Toast.LENGTH_SHORT).show();
                }
                else {
                    noOfChirps = Double.parseDouble(etNoOfChirps.getText().toString());
                    approximateTemperature = (((noOfChirps / 2.0) + 37.0) - 32.0) * (5.0 / 9.0);
                    tvTemperatureResultText = "Approx Temperature: " + temperatureResultFormatter.format(approximateTemperature) + " DEGC";
                    tvTemperatureResult.setText(tvTemperatureResultText);
                    tvTemperatureResult.setVisibility(View.VISIBLE);
                }
            }


        });


        buttonPlayTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MainActivity.this, TicTacToe.class);
                view1.getContext().startActivity(intent);
            }
        });


    }
}