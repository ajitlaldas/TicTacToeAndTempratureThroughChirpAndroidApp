package com.example.temepraturethroughcricketchirps;


import android.content.Intent;

import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    EditText etNoOfChirps;
    Button buttonNoOfChirps;
    Button buttonSesnorList;
    Button buttonPlayTicTacToe;
    Button buttonAddContact;
    ImageButton buttonSaveContact;
    ImageButton buttonPlaceCall;
    ImageButton buttonOpenWebAddress;
    ImageButton buttonOpenAddress;
    TextView tvTemperatureResult;
    TextView tvDescription;

    DecimalFormat temperatureResultFormatter;

    final int REQUEST_CODE_START_ADDCONTACT_ACTIVITY = 1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etNoOfChirps = findViewById(R.id.etNoOfChirps);
        buttonNoOfChirps = findViewById(R.id.buttonNoOfChirps);
        buttonPlayTicTacToe = findViewById(R.id.buttonPlayTicTacToe);
        buttonSesnorList = findViewById(R.id.buttonSesnorList);
        buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonSaveContact = findViewById(R.id.buttonSaveContact);
        buttonPlaceCall = findViewById(R.id.buttonPlaceCall);
        buttonOpenAddress =findViewById(R.id.buttonOpenAddress);
        buttonOpenWebAddress = findViewById(R.id.buttonOpenWebAddress);
        tvTemperatureResult = findViewById(R.id.tvTemperatureResult);



        tvTemperatureResult.setVisibility(View.GONE);

        buttonSaveContact.setVisibility(View.GONE);
        buttonPlaceCall.setVisibility(View.GONE);
        buttonOpenAddress.setVisibility(View.GONE);
        buttonOpenWebAddress.setVisibility(View.GONE);

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
                startActivity(intent);
            }
        });

        buttonSesnorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MainActivity.this, com.example.temepraturethroughcricketchirps.SensorList.class);
                startActivity(intent);
            }
        });

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(MainActivity.this, com.example.temepraturethroughcricketchirps.AddContact.class);
                startActivityForResult(intent, REQUEST_CODE_START_ADDCONTACT_ACTIVITY);
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the AdContactActivity with an OK result
        if (requestCode == REQUEST_CODE_START_ADDCONTACT_ACTIVITY) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String returnString;
                returnString = data.getStringExtra("contactName");
                //returnString = returnString + "\n" + data.getStringExtra("contactPhoneNo");
                //returnString = returnString + "\n" + data.getStringExtra("contactAddress");
               // returnString = returnString + "\n" + data.getStringExtra("contactWebAddress");

                if(!returnString.equals("")) {
                    buttonSaveContact.setVisibility(View.VISIBLE);
                    buttonSaveContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            Intent saveContact = new Intent(Intent.ACTION_INSERT);
                            saveContact.setType(ContactsContract.Contacts.CONTENT_TYPE);
                            saveContact.putExtra(ContactsContract.Intents.Insert.NAME, data.getStringExtra("contactName"));
                            saveContact.putExtra(ContactsContract.Intents.Insert.PHONE, data.getStringExtra("contactPhoneNo"));
                            startActivity(saveContact);
                        }
                    });
                }
                else
                    buttonSaveContact.setVisibility(View.GONE);

                returnString = data.getStringExtra("contactPhoneNo");
                if(!returnString.equals("")) {
                    buttonPlaceCall.setVisibility(View.VISIBLE);
                    buttonPlaceCall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            Intent placeCall = new Intent(Intent.ACTION_CALL);
                            placeCall.setData(Uri.parse("tel:" +data.getStringExtra("contactPhoneNo")));
                            //placeCall.putExtra(ContactsContract.Intents.Insert.NAME, data.getStringExtra("contactName"));
                            //placeCall.putExtra(ContactsContract.Intents.Insert.PHONE, data.getStringExtra("contactPhoneNo"));
                            if (placeCall.resolveActivity(getPackageManager()) != null)
                                startActivity(placeCall);
                        }
                    });
                }
                else
                    buttonPlaceCall.setVisibility(View.GONE);

                returnString = data.getStringExtra("contactAddress");
                if(!returnString.equals("")) {
                    buttonOpenAddress.setVisibility(View.VISIBLE);
                    buttonOpenAddress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            Intent openAddress = new Intent(Intent.ACTION_VIEW);
                            openAddress.setData(Uri.parse("geo:0,0?q=" +data.getStringExtra("contactAddress")));
                            //placeCall.putExtra(ContactsContract.Intents.Insert.NAME, data.getStringExtra("contactName"));
                            //placeCall.putExtra(ContactsContract.Intents.Insert.PHONE, data.getStringExtra("contactPhoneNo"));
                            startActivity(openAddress);
                        }
                    });
                }
                else
                    buttonOpenAddress.setVisibility(View.GONE);



            }
        }
    }

}