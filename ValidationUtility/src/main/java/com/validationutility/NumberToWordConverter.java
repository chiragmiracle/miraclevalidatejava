package com.validationutility;

import android.text.TextUtils;

public class NumberToWordConverter {

    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public static String convertCountToWord(String number) {
        if (TextUtils.isEmpty(number)) {
            return "Please enter a valid number";
        }

        number = number.trim();

        try {
            long num = Long.parseLong(number);

            if (num < 0 || num > 999_999_999_999L) {
                return "Number out of range";
            }

            if (num == 0) {
                return "Zero";
            }

            StringBuilder result = new StringBuilder();

            int scale = 0;

            while (num > 0) {
                int lastThreeDigits = (int) (num % 1000);
                if (lastThreeDigits != 0) {
                    result.insert(0, convertThreeDigitsToWord(lastThreeDigits) + THOUSANDS[scale] + " ");
                }
                num /= 1000;
                scale++;
            }

            return result.toString().trim();
        } catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }

    private static String convertThreeDigitsToWord(int num) {
        StringBuilder result = new StringBuilder();

        int hundredDigit = num / 100;
        if (hundredDigit > 0) {
            result.append(UNITS[hundredDigit]).append(" Hundred ");
        }

        int lastTwoDigits = num % 100;
        if (lastTwoDigits < 10) {
            result.append(UNITS[lastTwoDigits]);
        } else if (lastTwoDigits < 20) {
            result.append(TEENS[lastTwoDigits - 10]);
        } else {
            int tensDigit = lastTwoDigits / 10;
            result.append(TENS[tensDigit]);
            int unitDigit = lastTwoDigits % 10;
            if (unitDigit > 0) {
                result.append(" ").append(UNITS[unitDigit]);
            }
        }

        return result.toString().trim();
    }
}
