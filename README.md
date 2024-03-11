# miraclevalidatejava

## This is User Validation application with functionalities

## Features:
* Form Validation
* Generate Random Password
* Automatic convert Email in lowercase
* Name first letter capital automatic
* Simple Phone Number Validation
* Country Code with Phone Number Validation
* Email Validation
* Number To Word Converter country wise Ex. US and IN
* Word To Number Converter
* Time & Date Compare to ago
* Mass Converter
* length Converter
* Volume Converter
* Money Number Converter

## Sample:
![Form Validation](https://i.imgur.com/7EYhjz9.mp4)
![Validation1](https://i.imgur.com/1hv6ZOa.mp4)
![Validation2](https://i.imgur.com/Edssw2g.mp4)

## How to Use Module:

### Form Validation ###

![Password](https://imgur.com/zvAje0M.jpeg)

How to use :`miraclevalidatejava\app\src\main\java\com\miracle\validation\Form_Validation.java`. Add Module in your project.

```
miracleValidation.addValidation(activity, R.id.ll_userid, "[a-zA-Z0-9_-]+", R.string.error_userid);
miracleValidation.addValidation(activity, R.id.ll_first_name, "[a-zA-Z\\s]+", R.string.error_name);
miracleValidation.addValidation(activity, R.id.ll_last_name, "[a-zA-Z\\s]+", R.string.error_name);
miracleValidation.addValidation(activity, R.id.ll_phone_number, RegexTemplate.TELEPHONE, R.string.error_tel);
miracleValidation.addValidation(activity, R.id.ll_email, Patterns.EMAIL_ADDRESS, R.string.error_email);
miracleValidation.addValidation(activity, R.id.ll_password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.error_password);
miracleValidation.addValidation(activity, R.id.ll_password1, R.id.ll_password, R.string.error_password_confirmation);
miracleValidation.addValidation(activity, R.id.ll_zip_code, "\\d+", R.string.error_zipcode);
```




### Generate Random Password ###

![Password](https://i.imgur.com/9ap0XDQ.jpeg)

* CheckBox - Capital, Small, Number, Special.
* RadioButton - 8 letter, 10 letter, 12 letter.

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `getRandomPasswordCharacters`.

```
 //Generate Random Password
CheckBox pass_chkCapital, pass_chkSmall, pass_chkNumber, pass_chkSpec;
AppCompatButton pass_btnSUbmit;
int MAX_CHAR = 0;
RadioGroup pass_radioGroup;
RadioButton pass_radiobtnm;
TextView pass_result;

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
        }
    });
}
```

### Automatic convert Email in lowercase ###

![Email Lowercase](https://i.imgur.com/kXEYBBH.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `validateAutoLowerCaseEmail`.

```
private AppCompatButton email_lower_validation;
private EditText et_email_lower;
private TextView email_lower_result;

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
```

### Name first letter capital automatic ###

![First Latter Capital](https://i.imgur.com/FFKNiOe.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `validateAutoCapitalizeFirstLetter`.

```
EditText et_name_cap = findViewById(R.id.et_name_cap);
Validation.validateAutoCapitalizeFirstLetter(et_name_cap);
```

### Simple Phone Number Validation ###

![Simple Phone Number Validation](https://imgur.com/girbHPA.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `validateEmail`.

```
AppCompatButton email_validation = findViewById(R.id.email_validation);
EditText et_email = findViewById(R.id.et_email);

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
```

### Country Code with Phone Number Validation ### 

![Country Code Number Validation](https://i.imgur.com/wMViBsY.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `validatePhoneNumber`.

```
EditText et_phone_number1;
AppCompatButton num_validation1;
Spinner country_code;

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

// arrays.xml
<string-array name="country_numtoword">
    <item>US</item>
    <item>IN</item>
</string-array>

```

### Email Validation ###

![Email Validation](https://imgur.com/mXw8rxv.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `validateEmail`.

```
AppCompatButton email_validation = findViewById(R.id.email_validation);
EditText et_email = findViewById(R.id.et_email);

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
```

### Number To Word Converter country wise Ex. US and IN ###

![Number To Word Converter US](https://imgur.com/gJO1DxU.jpeg)

![Number To Word Converter IN](https://imgur.com/VWHQeuc.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\NumberToWordConverter` or `src\main\java\com\validationutility\Validation\IN_NumberToWord`

```
String str_numtoword;

Spinner country_numtoword  = findViewById(R.id.country_numtoword);
AppCompatButton convert_validation = findViewById(R.id.convert_validation);
EditText et_convert_number = findViewById(R.id.et_convert_number);
TextView tv_result = findViewById(R.id.tv_result);

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

```

### Word To Number Converter ###

![Word To Number Converter](https://imgur.com/uCDrnKo.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\WordToNumberConverter`.

```
AppCompatButton convert_word_validation = findViewById(R.id.convert_word_validation);
EditText et_convert_word = findViewById(R.id.et_convert_word);
TextView tv_word_result = findViewById(R.id.tv_word_result);

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
```

### Time & Date Compare to ago ###

![Word To Number Converter](https://imgur.com/wpcQHbC.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `compareDateTime` or `compareDateTimeAgo`.

```
Calendar calendar;
SimpleDateFormat dateFormat;
SimpleDateFormat timeFormat;
Date currentDate;

AppCompatButton select_DateTime = findViewById(R.id.select_DateTime);
TextView dateTime_result = findViewById(R.id.dateTime_result);

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
```

### Mass Converter ###

![Mass Converter](https://imgur.com/rIZhCkd.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `MassConvert`.

```
AppCompatButton mass_convert;
EditText mass_et1;
TextView mass_et2;
Spinner mass_sp1, mass_sp2;

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

// arrays.xml
<string-array name="units_array">
    <item>Tonne</item>
    <item>Kilogram</item>
    <item>Gram</item>
    <item>Milligram</item>
    <item>Microgram</item>
    <item>Imperial Ton</item>
    <item>US Ton</item>
    <item>Stone</item>
    <item>Pound</item>
    <item>Ounce</item>
</string-array>

```

### Length Converter ###

![Length Converter](https://imgur.com/avOWJE8.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `LengthConvert`.

```
AppCompatButton length_convert;
EditText length_et1;
TextView length_et2;
Spinner length_sp1, length_sp2;

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

// arrays.xml
<string-array name="length_array">
    <item>Kilometer</item>
    <item>Meter</item>
    <item>Centimeter</item>
    <item>Millimeter</item>
    <item>Micrometer</item>
    <item>Nanometer</item>
    <item>Mile</item>
    <item>Yard</item>
    <item>Foot</item>
    <item>Inch</item>
    <item>Nautical Mile</item>
</string-array>
```

### Volume Converter ###

![Volume Converter](https://imgur.com/mmKs1F5.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `VolumeConvert`.

```
AppCompatButton volume_convert;
EditText volume_et1;
TextView volume_et2;
Spinner volume_sp1, volume_sp2;

ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversion_options, android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
volume_sp1.setAdapter(adapter);
volume_sp2.setAdapter(adapter);
volume_convert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
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
});

// arrays.xml
<string-array name="conversion_options">
    <item>US liquid gallon</item>
    <item>US liquid quart</item>
    <item>US liquid pint</item>
    <item>US legal cup</item>
    <item>US fluid ounce</item>
    <item>US tablespoon</item>
    <item>US teaspoon</item>
    <item>cubic meter</item>
    <item>liter</item>
    <item>milliliter</item>
    <item>imperial gallon</item>
    <item>imperial quart</item>
    <item>imperial pint</item>
    <item>imperial cup</item>
    <item>imperial fluid ounce</item>
    <item>imperial tablespoon</item>
    <item>imperial teaspoon</item>
    <item>cubic foot</item>
    <item>cubic inch</item>
</string-array>
```
### Money Number Converter ###

![Volume Converter](https://imgur.com/aRZBN1B.jpeg)

Adding Class in Module `src\main\java\com\validationutility\Validation\Validation` add Method `NumberConvertStr`.

```
AppCompatButton num_convert;
Spinner num_sp1, num_sp2;
EditText num_et1;
TextView num_et2;

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

// arrays.xml
<string-array name="conversion_units">
    <item>billion</item>
    <item>crore</item>
    <item>dozen</item>
    <item>hundred</item>
    <item>lakh</item>
    <item>million</item>
    <item>thousand</item>
    <item>trillion</item>
</string-array>
```