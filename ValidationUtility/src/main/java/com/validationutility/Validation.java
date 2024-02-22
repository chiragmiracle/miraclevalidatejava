package com.validationutility;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

public class Validation {


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
        // Remove any non-numeric characters from phone number
        String normalizedPhoneNumber = PhoneNumberUtils.stripSeparators(phoneNumber);

        // Remove any non-numeric characters from country code
        String normalizedCountryCode = PhoneNumberUtils.stripSeparators(countryCode);

        // Check if the country code or phone number is empty or null
        if (normalizedCountryCode == null || normalizedCountryCode.isEmpty() ||
                normalizedPhoneNumber == null || normalizedPhoneNumber.isEmpty()) {
            return false;
        }

        // Check if the country code or phone number is too short or too long
        if (normalizedCountryCode.length() < 1 || normalizedCountryCode.length() > 3 ||
                normalizedPhoneNumber.length() < 5 || normalizedPhoneNumber.length() > 15) {
            return false;
        }

        // Additional custom validation logic can be added here if needed
        // If all checks pass, return true
        return true;
    }

}
