package com.miracle.validation;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.validationutility.Validation.IN_NumberToWord;
import com.validationutility.Validation.NumberToWordConverter;
import com.validationutility.Validation.Validation;
import com.validationutility.Validation.WordToNumberConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    //Generate Random Password
    CheckBox pass_chkCapital, pass_chkSmall, pass_chkNumber, pass_chkSpec;
    AppCompatButton pass_btnSUbmit;
    int MAX_CHAR = 0;
    RadioGroup pass_radioGroup;
    RadioButton pass_radiobtnm;
    TextView pass_result;

    //Mass Convert
    private AppCompatButton mass_convert;
    private EditText mass_et1;
    private TextView mass_et2;
    Spinner mass_sp1, mass_sp2;

    //Volume Convert
    private AppCompatButton volume_convert;
    private EditText volume_et1;
    private TextView volume_et2;
    Spinner volume_sp1, volume_sp2;

    //length Convert
    private AppCompatButton length_convert;
    private EditText length_et1;
    private TextView length_et2;
    Spinner length_sp1, length_sp2;

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
    Spinner country_numtoword;
    String str_numtoword;

    //Word To Number  Convert
    private AppCompatButton convert_word_validation;
    private EditText et_convert_word;
    private TextView tv_word_result;

    //Time Date
    private AppCompatButton select_DateTime;
    private TextView dateTime_result;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private Date currentDate;

    //Numbe Convert
    private AppCompatButton num_convert;
    private Spinner num_sp1, num_sp2;
    private EditText num_et1;
    private TextView num_et2;

    //Email Auto LowerCas Convert
    private AppCompatButton email_lower_validation;
    private EditText et_email_lower;
    private TextView email_lower_result;

    //name first latter capital Convert
    private EditText et_name_cap;

    private AppCompatButton ll_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ll_form.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Form_Validation.class));
        });

        nameFirstLatterCapital();
        RendomePassword();
        volumeConverter();
        MassConverter();
        lengthConverter();
        simpleteNumValidation();
        countryCodeNumValidation();
        emailValidation();
        emailLowerValidation();
        numberToWordConvert();
        wordToNumberConvert();
        SelectDateTime();
        numberConvert();

    }

    private void nameFirstLatterCapital() {
        Validation.validateAutoCapitalizeFirstLetter(et_name_cap);
    }

    private void emailLowerValidation() {
        Validation.validateAutoLowerCaseEmail(et_email_lower);
        email_lower_validation.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_email_lower.getText().toString())) {
                email_lower_result.setText("Enter Email ID");
            } else {
                if (Validation.validateEmail(et_email_lower.getText().toString())) {
                    email_lower_result.setText("Valid Email");
                } else {
                    email_lower_result.setText("Invalid Email");
                }
            }
        });
    }

    private void RendomePassword() {
        pass_btnSUbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder password = new StringBuilder();
                ArrayList<Integer> passSel = new ArrayList<Integer>();
                int selectedId = pass_radioGroup.getCheckedRadioButtonId();

                pass_radiobtnm = findViewById(selectedId);

                MAX_CHAR = Integer.parseInt(pass_radiobtnm.getText().toString());

                if (!pass_chkCapital.isChecked() && !pass_chkSmall.isChecked() && !pass_chkNumber.isChecked() && !pass_chkSpec.isChecked()) {
                    pass_result.setText("Please select at least one checkbox");
                    Toast.makeText(MainActivity.this, "Please select at least one checkbox", Toast.LENGTH_SHORT).show();
                    return;
                }

                // when  UPPER CASE selected
                if (pass_chkCapital.isChecked()) passSel.add(1);

                // when  LOWER CASE selected
                if (pass_chkSmall.isChecked()) passSel.add(3);

                // when  Number  selected
                if (pass_chkNumber.isChecked()) passSel.add(0);

                // when  Special selected
                if (pass_chkSpec.isChecked()) passSel.add(2);

                for (int i = 1; i <= MAX_CHAR; ) {
                    if (passSel.contains(0) && i <= MAX_CHAR) {
                        password.append(Validation.getRandomPasswordCharacters(0));
                        i++;
                    }
                    if (passSel.contains(1) && i <= MAX_CHAR) {
                        password.append(Validation.getRandomPasswordCharacters(1));
                        i++;
                    }
                    if (passSel.contains(2) && i <= MAX_CHAR) {
                        password.append(Validation.getRandomPasswordCharacters(2));
                        i++;
                    }
                    if (passSel.contains(3) && i <= MAX_CHAR) {
                        password.append(Validation.getRandomPasswordCharacters(3));
                        i++;
                    }
                }
                pass_result.setText("your password is : " + password);
                Log.e("CHIRAG_PASS", "your password is :>--> " + password + "");
            }
        });
    }

    private void volumeConverter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volume_sp1.setAdapter(adapter);
        volume_sp2.setAdapter(adapter);
        volume_sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainVolumeConverter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        volume_sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainVolumeConverter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        volume_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainVolumeConverter();
            }
        });
    }

    private void mainVolumeConverter() {
        String unitFrom = volume_sp1.getSelectedItem().toString();
        String unitTo = volume_sp2.getSelectedItem().toString();
        String valueStr = volume_et1.getText().toString();
        if (valueStr.isEmpty()) {
            volume_et2.setText("result");
            return;
        }
        double value = Double.parseDouble(valueStr);
        String result;
        result = String.valueOf(Validation.VolumeConvert(value, unitFrom, unitTo));
        volume_et2.setText(result);
    }

    private void lengthConverter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.length_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        length_sp1.setAdapter(adapter);
        length_sp2.setAdapter(adapter);

        length_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromUnit = length_sp1.getSelectedItem().toString();
                String toUnit = length_sp2.getSelectedItem().toString();
                double length_str = Double.parseDouble(length_et1.getText().toString());
                String result;
                result = String.valueOf(Validation.LengthConvert(length_str, fromUnit, toUnit));
                length_et2.setText(result);
            }
        });
    }

    private void MassConverter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mass_sp1.setAdapter(adapter);
        mass_sp2.setAdapter(adapter);

        mass_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromUnit = mass_sp1.getSelectedItem().toString();
                String toUnit = mass_sp2.getSelectedItem().toString();
                String mass_str = mass_et1.getText().toString();
                String result;
                result = String.valueOf(Validation.MassConvert(mass_str, fromUnit, toUnit));
                mass_et2.setText(result);
            }
        });
    }

    private void numberConvert() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        num_sp1.setAdapter(adapter);
        num_sp2.setAdapter(adapter);
        num_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = num_et1.getText().toString();
                String unit1 = num_sp1.getSelectedItem().toString();
                String unit2 = num_sp2.getSelectedItem().toString();
                String result;
                if (Validation.getMultiplier(unit1) < Validation.getMultiplier(unit2)) {
                    result = String.valueOf(Validation.NumberConvert(number, unit1, unit2));
                } else {
                    result = Validation.NumberConvertStr(number, unit1, unit2);
                }
                num_et2.setText(result);
            }
        });
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
                                    String difference_ago = Validation.compareDateTimeAgo(selectedDateTimeCalendar, Calendar.getInstance());
                                    dateTime_result.setText(selectedDateTime + "\n" + difference + "\n" + difference_ago);
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
        country_numtoword.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        str_numtoword = "1";
                        break;
                    case 1:
                        str_numtoword = "2";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_numtoword, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_numtoword.setAdapter(adapter);
        String spinner_str = country_numtoword.getSelectedItem().toString();
        convert_validation.setOnClickListener(v -> {
            String inputNumber = et_convert_number.getText().toString();
            if (str_numtoword.equals("2")) {
                String result = IN_NumberToWord.convertCountToWord(inputNumber);
                tv_result.setText(result);
            } else {
                String result = NumberToWordConverter.convertCountToWord(inputNumber);
                tv_result.setText(result);
            }
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
        ll_form = findViewById(R.id.ll_form);

        email_lower_validation = findViewById(R.id.email_lower_validation);
        et_email_lower = findViewById(R.id.et_email_lower);
        email_lower_result = findViewById(R.id.email_lower_result);

        et_name_cap = findViewById(R.id.et_name_cap);

        pass_chkCapital = findViewById(R.id.pass_checkBox1);
        pass_chkSmall = findViewById(R.id.pass_checkBox2);
        pass_chkNumber = findViewById(R.id.pass_checkBox3);
        pass_chkSpec = findViewById(R.id.pass_checkBox4);
        pass_radioGroup = findViewById(R.id.pass_radioGroup);
        pass_btnSUbmit = findViewById(R.id.pass_btnSubmit);
        pass_result = findViewById(R.id.pass_result);


        volume_convert = findViewById(R.id.volume_convert);
        volume_et1 = findViewById(R.id.volume_et1);
        volume_sp1 = findViewById(R.id.volume_sp1);
        volume_sp2 = findViewById(R.id.volume_sp2);
        volume_et2 = findViewById(R.id.volume_et2);

        mass_convert = findViewById(R.id.mass_convert);
        mass_et1 = findViewById(R.id.mass_et1);
        mass_sp1 = findViewById(R.id.mass_sp1);
        mass_sp2 = findViewById(R.id.mass_sp2);
        mass_et2 = findViewById(R.id.mass_et2);

        length_convert = findViewById(R.id.length_convert);
        length_et1 = findViewById(R.id.length_et1);
        length_sp1 = findViewById(R.id.length_sp1);
        length_sp2 = findViewById(R.id.length_sp2);
        length_et2 = findViewById(R.id.length_et2);

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
        country_numtoword = findViewById(R.id.country_numtoword);
        tv_word_result = findViewById(R.id.tv_word_result);
        select_DateTime = findViewById(R.id.select_DateTime);
        dateTime_result = findViewById(R.id.dateTime_result);
        num_et1 = findViewById(R.id.num_et1);
        num_et2 = findViewById(R.id.num_et2);
        num_sp1 = findViewById(R.id.num_sp1);
        num_sp2 = findViewById(R.id.num_sp2);
        num_convert = findViewById(R.id.num_convert);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}