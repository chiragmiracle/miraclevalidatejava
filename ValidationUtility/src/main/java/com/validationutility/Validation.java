package com.validationutility;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.Calendar;
import java.util.regex.Matcher;

public class Validation {

    public static boolean isEmptyEditText(EditText etText) {
        //If edittext is NULL
        if (etText == null)
            return true;
        return etText.getText().toString().trim().isEmpty();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        // If phone is empty
        if (TextUtils.isEmpty(phoneNumber.trim())) {
            return false;
        }
        String normalizedPhoneNumber = PhoneNumberUtils.stripSeparators(phoneNumber);
        if (normalizedPhoneNumber == null || normalizedPhoneNumber.isEmpty()) {
            return false;
        }
        if (normalizedPhoneNumber.length() < 10 || normalizedPhoneNumber.length() > 15) {
            return false;
        }
        return true;
    }

    public static boolean validatePhoneNumber(String countryCode, String phoneNumber) {
        String normalizedPhoneNumber = PhoneNumberUtils.stripSeparators(phoneNumber);
        String normalizedCountryCode = PhoneNumberUtils.stripSeparators(countryCode);
        if (normalizedCountryCode == null || normalizedCountryCode.isEmpty() ||
                normalizedPhoneNumber == null || normalizedPhoneNumber.isEmpty()) {
            return false;
        }
        if (normalizedCountryCode.length() < 1 || normalizedCountryCode.length() > 3 ||
                normalizedPhoneNumber.length() < 5 || normalizedPhoneNumber.length() > 15) {
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String compareDateTime(Calendar selectedDateTime, Calendar currentDateTime) {
        long diffMillis = currentDateTime.getTimeInMillis() - selectedDateTime.getTimeInMillis();
        long seconds = diffMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        long remainingHours = hours % 24;
        long remainingMinutes = minutes % 60;
        long remainingSeconds = seconds % 60;

        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(days).append(" days, ");
        }
        if (remainingHours > 0) {
            builder.append(remainingHours).append(" hours, ");
        }
        if (remainingMinutes > 0) {
            builder.append(remainingMinutes).append(" minutes, ");
        }
        if (remainingSeconds > 0) {
            builder.append(remainingSeconds).append(" seconds");
        }

        return builder.toString();
    }

}
