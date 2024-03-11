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

## Sample:
![Form Validation](https://i.imgur.com/7EYhjz9.mp4)
![Validation1](https://i.imgur.com/1hv6ZOa.mp4)
![Validation2](https://i.imgur.com/Edssw2g.mp4)

## How to Use Module:

![Password](https://i.imgur.com/9ap0XDQ.jpeg)
* Capital, Small, Number, Special.
* 8 letter, 10 letter, 12 letter.

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
            Log.e("CHIRAG_PASS", "your password is :>--> " + password + "");
        }
    });
}

public static String getRandomPasswordCharacters(int pos) {
    String NUMBERS = "0123456789";
    String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    String SPECIALCHARACTERS = "@#$%&*";
    Random randomNum = new Random();
    StringBuilder randomChar = new StringBuilder();
    switch (pos) {
        case 0:
            randomChar.append(NUMBERS.charAt(randomNum.nextInt(NUMBERS.length() - 1)));
            break;
        case 1:
            randomChar.append(UPPER_ALPHABETS.charAt(randomNum.nextInt(UPPER_ALPHABETS.length() - 1)));
            break;
        case 2:
            randomChar.append(SPECIALCHARACTERS.charAt(randomNum.nextInt(SPECIALCHARACTERS.length() - 1)));
            break;
        case 3:
            randomChar.append(LOWER_ALPHABETS.charAt(randomNum.nextInt(LOWER_ALPHABETS.length() - 1)));
            break;
    }
    return randomChar.toString();
}
```
