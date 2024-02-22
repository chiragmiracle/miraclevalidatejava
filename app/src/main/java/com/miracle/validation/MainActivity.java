package com.miracle.validation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.validationutility.NumberToWordConverter;
import com.validationutility.Validation;
import com.validationutility.WordToNumberConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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

    //Number to Word Convert
    private AppCompatButton convert_validation;
    private EditText et_convert_number;
    private TextView tv_result;

    //Number to Word Convert
    private AppCompatButton convert_word_validation;
    private EditText et_convert_word;
    private TextView tv_word_result;

    //Number to Word Convert
    private AppCompatButton select_DateTime;
    private TextView dateTime_result;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        simpleteNumValidation();
        countryCodeNumValidation();
        emailValidation();
        numberToWordConvert();
        wordToNumberConvert();
        SelectDateTime();
    }

    private void SelectDateTime() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        calendar = Calendar.getInstance();
        currentDate = calendar.getTime();

        select_DateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDateCalendar = Calendar.getInstance();
                int year = currentDateCalendar.get(Calendar.YEAR);
                int month = currentDateCalendar.get(Calendar.MONTH);
                int dayOfMonth = currentDateCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        int hour = calendar.get(Calendar.HOUR_OF_DAY);
                        int minute = calendar.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);


                                Date selectedDate = calendar.getTime();
                                String selectedDateTime = dateFormat.format(selectedDate) + " " + timeFormat.format(selectedDate);
                                if (selectedDate.after(currentDate)) {
                                    Toast.makeText(MainActivity.this, "Please select a date and time before the current date and time", Toast.LENGTH_SHORT).show();
                                } else {
                                    Calendar selectedDateTimeCalendar = Calendar.getInstance();
                                    selectedDateTimeCalendar.setTime(selectedDate);

                                    String difference = Validation.compareDateTime(selectedDateTimeCalendar, Calendar.getInstance());
                                    dateTime_result.setText(selectedDateTime + "\n" + difference);
                                }
                            }
                        }, hour, minute, false);
                        timePickerDialog.show();
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMaxDate(currentDateCalendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    private void wordToNumberConvert() {
        convert_word_validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputWords = et_convert_word.getText().toString();
                long result = WordToNumberConverter.convertWordToNumber(inputWords);
                if (result == -1) {
                    tv_word_result.setText("Invalid input");
                } else {
                    tv_word_result.setText(String.valueOf(result));
                }
            }
        });
    }

    private void numberToWordConvert() {
        convert_validation.setOnClickListener(v -> {
            String inputNumber = et_convert_number.getText().toString();
            String result = NumberToWordConverter.convertCountToWord(inputNumber);
            tv_result.setText(result);
        });
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
        convert_validation = findViewById(R.id.convert_validation);
        et_convert_number = findViewById(R.id.et_convert_number);
        tv_result = findViewById(R.id.tv_result);
        et_convert_word = findViewById(R.id.et_convert_word);
        convert_word_validation = findViewById(R.id.convert_word_validation);
        tv_word_result = findViewById(R.id.tv_word_result);
        select_DateTime = findViewById(R.id.select_DateTime);
        dateTime_result = findViewById(R.id.dateTime_result);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}