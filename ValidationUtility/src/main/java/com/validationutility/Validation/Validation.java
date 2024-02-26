package com.validationutility.Validation;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.Calendar;

public class Validation {

    private static double[][] conversionMatrix = {
            {1, 1000, 1000000, 1000000000, 1000000000000.0, 0.9842065286, 1.1023113109, 157.4730444178, 2204.6226218488, 35273.96194958},
            {0.001, 1, 1000, 1000000, 1000000000, 0.0009842065, 0.0011023113, 0.1574730444, 2.2046226218, 35.2739619496},
            {0.000001, 0.001, 1, 1000, 1000000, 0.0000009842, 0.0000011023, 0.000157473, 0.0022046226, 0.0352739619},
            {0.000000001, 0.000001, 0.001, 1, 1000, 0.000000001, 0.0000000011, 0.0000001575, 0.0000022046, 0.000035274},
            {0.000000000001, 0.000000001, 0.000001, 0.001, 1, 0.000000000001, 0.0000000000011, 0.0000000001575, 0.0000000022046, 0.000000035274},
            {1.016e-6, 0.0010160469, 0.0010160469, 1.0160469088, 1016.0469088, 1, 1.12, 160, 2240, 35840},
            {0.90718474, 907.18474, 907184.74, 907184740, 9.0718474e+11, 0.8928571429, 1, 142.8571428571, 2000, 32000},
            {0.00635029318, 6.35029318, 6350.29318, 6350293.18, 6.35029318e+9, 0.00625, 0.007, 1, 14, 224},
            {0.00045359237, 0.45359237, 453.59237, 453592.37, 453592370, 0.0004464286, 0.0005, 0.0714285714, 1, 16},
            {0.00002834952, 0.0283495231, 28.349523125, 28349.523125, 28349523.125, 2.7777777778e-5, 3.125e-5, 0.0044642857, 0.0625, 1}
    };

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

    public static double MassConvert(String mass_str, String fromUnit, String toUnit) {
        int fromIndex = getIndexFromUnit(fromUnit);
        int toIndex = getIndexFromUnit(toUnit);
        double inputValue = Double.parseDouble(mass_str);
        return inputValue * conversionMatrix[fromIndex][toIndex];
    }

    public static int getIndexFromUnit(String unit) {
        switch (unit) {
            case "Tonne":
                return 0;
            case "Kilogram":
                return 1;
            case "Gram":
                return 2;
            case "Milligram":
                return 3;
            case "Microgram":
                return 4;
            case "Imperial Ton":
                return 5;
            case "US Ton":
                return 6;
            case "Stone":
                return 7;
            case "Pound":
                return 8;
            case "Ounce":
                return 9;
            default:
                return -1;
        }
    }


    public static double LengthConvert(double value, String fromUnit, String toUnit) {
        double meterValue = 0.0;
        switch (fromUnit) {
            case "Kilometer":
                meterValue = value * 1000.0;
                break;
            case "Meter":
                meterValue = value;
                break;
            case "Centimeter":
                meterValue = value * 0.01;
                break;
            case "Millimeter":
                meterValue = value * 0.001;
                break;
            case "Micrometer":
                meterValue = value * 0.000001;
                break;
            case "Nanometer":
                meterValue = value * 0.000000001;
                break;
            case "Mile":
                meterValue = value * 1609.344;
                break;
            case "Yard":
                meterValue = value * 0.9144;
                break;
            case "Foot":
                meterValue = value * 0.3048;
                break;
            case "Inch":
                meterValue = value * 0.0254;
                break;
            case "Nautical Mile":
                meterValue = value * 1852.0;
                break;
        }

        // Convert meters to the desired output unit
        double result = 0.0;
        switch (toUnit) {
            case "Kilometer":
                result = meterValue / 1000.0;
                break;
            case "Meter":
                result = meterValue;
                break;
            case "Centimeter":
                result = meterValue / 0.01;
                break;
            case "Millimeter":
                result = meterValue / 0.001;
                break;
            case "Micrometer":
                result = meterValue / 0.000001;
                break;
            case "Nanometer":
                result = meterValue / 0.000000001;
                break;
            case "Mile":
                result = meterValue / 1609.344;
                break;
            case "Yard":
                result = meterValue / 0.9144;
                break;
            case "Foot":
                result = meterValue / 0.3048;
                break;
            case "Inch":
                result = meterValue / 0.0254;
                break;
            case "Nautical Mile":
                result = meterValue / 1852.0;
                break;
        }

        return result;
    }

}
