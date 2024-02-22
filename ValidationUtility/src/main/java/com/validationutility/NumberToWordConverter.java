package com.validationutility;

import android.text.TextUtils;

public class NumberToWordConverter {
    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] teens = {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public static String convertCountToWord(String number) {
        if (number == null || number.isEmpty())
            return "Please enter a valid number";

        try {
            long num = Long.parseLong(number);
            if (num == 0)
                return "Zero";

            int scale = 0;
            StringBuilder result = new StringBuilder();

            while (num > 0) {
                if (num % 1000 != 0) {
                    StringBuilder groupResult = new StringBuilder();
                    convertThreeDigitsToWord((int) (num % 1000), groupResult);
                    result.insert(0, groupResult.append(thousands[scale]).append(" "));
                }
                num /= 1000;
                scale++;
            }
            return result.toString().trim();
        } catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }

    private static void convertThreeDigitsToWord(int number, StringBuilder result) {
        if (number >= 100) {
            result.append(units[number / 100]).append(" Hundred ");
            number %= 100;
        }
        if (number >= 11 && number <= 19) {
            result.append(teens[number - 10]).append(" ");
        } else if (number >= 20 || number == 10) {
            result.append(tens[number / 10]).append(" ");
            number %= 10;
        }
        if (number > 0 && number < 10) {
            result.append(units[number]).append(" ");
        }
    }
}
