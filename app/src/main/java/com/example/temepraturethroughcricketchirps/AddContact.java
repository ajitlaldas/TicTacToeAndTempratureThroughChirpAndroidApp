package com.example.temepraturethroughcricketchirps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    Button buttonSubmitContact;
    EditText etContactName;
    EditText etContactPhoneNo;
    EditText etContactAddress;
    EditText etContactWebAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        buttonSubmitContact=findViewById(R.id.buttonSubmitContact);
        etContactName = findViewById(R.id.etContactName);
        etContactPhoneNo = findViewById(R.id.etContactPhoneNo);
        etContactAddress = findViewById(R.id.etContactAddress);
        etContactWebAddress = findViewById(R.id.etContactWebAddress);

        buttonSubmitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent data = new Intent();

                String contactName = etContactName.getText().toString();
                String contactPhoneNo = etContactPhoneNo.getText().toString();
                String contactAddress = etContactAddress.getText().toString();
                String contactWebAddress = etContactWebAddress.getText().toString();

                if (contactName!=null)
                    data.putExtra("contactName", contactName);
                if (contactPhoneNo!=null)
                    data.putExtra("contactPhoneNo", contactPhoneNo);
                if (contactAddress!=null)
                    data.putExtra("contactAddress", contactAddress);
                if (contactWebAddress!=null)
                    data.putExtra("contactWebAddress", contactWebAddress);

                if ((contactName!=null) && (contactPhoneNo!=null || contactAddress!=null || contactWebAddress!=null))
                    setResult(RESULT_OK, data);
                else
                    setResult(RESULT_CANCELED);

                finish();
            }
        });

    }
}