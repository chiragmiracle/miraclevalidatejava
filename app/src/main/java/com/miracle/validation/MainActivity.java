package com.miracle.validation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.validationutility.Validation;


public class MainActivity extends AppCompatActivity {

    //simple Phone number validation
    private AppCompatButton num_validation;
    private EditText et_phone_number;

    //country code with  Phone number validation
    private EditText et_phone_number1;
    private AppCompatButton num_validation1;
    private Spinner country_code;

    //simple Phone number validation
    private AppCompatButton email_validation;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        simpleteNumValidation();
        countryCodeNumValidation();
        emailValidation();
    }

    private void emailValidation() {
        email_validation.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_email.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Email ID", Toast.LENGTH_LONG).show();
            } else {
                if (Validation.validateEmail(et_email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Valid Email", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void countryCodeNumValidation() {
        // Set up the spinner with country codes
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_code.setAdapter(adapter);
        // Set up click listener for the validate button
        num_validation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryCode = country_code.getSelectedItem().toString();
                String phoneNumber = et_phone_number1.getText().toString();
                if (TextUtils.isEmpty(et_phone_number1.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_LONG).show();
                } else {
                    if (Validation.validatePhoneNumber(countryCode, phoneNumber)) {
                        Toast.makeText(MainActivity.this, "Phone number is valid", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void simpleteNumValidation() {
        num_validation.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_phone_number.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_LONG).show();
            } else {
                if (Validation.validatePhoneNumber(et_phone_number.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Valid Phone Number", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        et_phone_number = findViewById(R.id.et_phone_number);
        num_validation = findViewById(R.id.num_validation);
        et_phone_number1 = findViewById(R.id.et_phone_number1);
        num_validation1 = findViewById(R.id.num_validation1);
        country_code = findViewById(R.id.country_code);
        et_email = findViewById(R.id.et_email);
        email_validation = findViewById(R.id.email_validation);
    }

}