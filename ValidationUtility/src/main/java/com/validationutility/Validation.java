package com.validationutility;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.Calendar;

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

    public static String compareDateTimeAgo(Calendar selectedDateTime, Calendar currentDateTime) {
        long differenceInMillis = currentDateTime.getTimeInMillis() - selectedDateTime.getTimeInMillis();

        long secondsInMillis = 1000;
        long minutesInMillis = secondsInMillis * 60;
        long hoursInMillis = minutesInMillis * 60;
        long daysInMillis = hoursInMillis * 24;
        long weeksInMillis = daysInMillis * 7;
        long monthsInMillis = daysInMillis * 30;
        long yearsInMillis = daysInMillis * 365;

        long years = differenceInMillis / yearsInMillis;
        long months = differenceInMillis / monthsInMillis;
        long weeks = differenceInMillis / weeksInMillis;
        long days = differenceInMillis / daysInMillis;
        long hours = differenceInMillis / hoursInMillis;
        long minutes = differenceInMillis / minutesInMillis;
        long seconds = differenceInMillis / secondsInMillis;

        if (years > 0) {
            return years + " year's ago";
        } else if (months > 0) {
            return months + " month's ago";
        } else if (weeks > 0) {
            return weeks + " week's ago";
        } else if (days > 0) {
            return days + " day's ago";
        } else if (hours > 0) {
            return hours + " hour's ago";
        } else if (minutes > 0) {
            return minutes + " minute's ago";
        } else {
            return seconds + " second's ago";
        }
    }

    public static String NumberConvertStr(String number, String unit1, String unit2) {
        double num = Double.parseDouble(number);
        double multiplier = getMultiplier(unit1) / getMultiplier(unit2);
        double result = num * multiplier;
        return String.valueOf(Math.round(result));
    }

    public static double NumberConvert(String number, String unit1, String unit2) {
        double num = Double.parseDouble(number);
        double multiplier = getMultiplier(unit1) / getMultiplier(unit2);
        return num * multiplier;
    }

    public static double getMultiplier(String unit) {
        switch (unit) {
            case "billion":
                return 1e9;
            case "crore":
                return 1e7;
            case "dozen":
                return 12;
            case "hundred":
                return 100;
            case "lakh":
                return 1e5;
            case "million":
                return 1e6;
            case "thousand":
                return 1e3;
            case "trillion":
                return 1e12;
            default:
                return 1;
        }
    }
}
