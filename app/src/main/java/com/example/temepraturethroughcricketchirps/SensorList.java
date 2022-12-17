package com.example.temepraturethroughcricketchirps;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SensorList extends AppCompatActivity {

    TextView tvSensorList;
    SensorManager sensorManager;
    List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        tvSensorList = findViewById(R.id.tvSensorList);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);

        String displaySensorListText;
        displaySensorListText = "Available Sensors on this Device are: \n\n";
        int i= 0;
        for (Sensor tempSensor : sensorList){
            i++;
            displaySensorListText = displaySensorListText + "\n\n" + i + ") " + tempSensor.toString();
        }

        tvSensorList.setText(displaySensorListText);
        tvSensorList.setVisibility(View.VISIBLE);

        tvSensorList.setMovementMethod(new ScrollingMovementMethod());

    }
}