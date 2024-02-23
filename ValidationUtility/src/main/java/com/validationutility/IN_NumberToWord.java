package com.validationutility;

public class IN_NumberToWord {

    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] GROUPS = {"", "Ten", "Hundred", "Thousand", "Lakh", "Crore"};

    public static String convertCountToWord(String number) {
        if (number == null || number.isEmpty()) {
            return "Please enter a valid number";
        }

        number = number.trim();

        try {
            long num = Long.parseLong(number);

            if (num == 0) {
                return "Zero";
            }

            if (num < 0 || num > 999_999_999_999L) {
                return "Number out of range. Please enter a number between 0 and 999,999,999,999.";
            }

            String result = "";

            int groupIndex = 0;
            while (num > 0) {
                int lastTwoDigits = (int) (num % 100);
                if (lastTwoDigits != 0) {
                    result = convertTwoDigitsToWord(lastTwoDigits) + (groupIndex > 1 ? "" : " ") + GROUPS[groupIndex] + " " + result;
                }
                num /= 100;
                groupIndex++;
            }

            return result.trim();
        } catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }

    private static String convertTwoDigitsToWord(int num) {
        if (num < 100) {
            if (num < 20) {
                return (num < 10) ? UNITS[num] : TEENS[num - 10];
            } else {
                String result = TENS[num / 10];
                int unitDigit = num % 10;
                if (unitDigit > 0) {
                    result += " " + UNITS[unitDigit];
                }
                return result;
            }
        } else {
            // Handle numbers greater than or equal to 100
            int hundredDigit = num / 100;
            int remainingTwoDigits = num % 100;
            String result = UNITS[hundredDigit] + " Hundred";
            if (remainingTwoDigits > 0) {
                result += " and " + convertTwoDigitsToWord(remainingTwoDigits);
            }
            return result;
        }
    }

//    public static String convertCountToWord(String number) {
//        if (number == null || number.isEmpty()) {
//            return "Please enter a valid number";
//        }
//
//        number = number.trim();
//
//        try {
//            long num = Long.parseLong(number);
//
//            if (num == 0) {
//                return "Zero";
//            }
//
//            if (num < 0 || num > 999_999_999_999L) {
//                return "Number out of range. Please enter a number between 0 and 999,999,999.";
//            }
//
//            String result = "";
//
//            int groupIndex = 0;
//            while (num > 0) {
//                int lastTwoDigits = (int) (num % 100);
//                if (lastTwoDigits != 0) {
//                    result = convertTwoDigitsToWord(lastTwoDigits) + (groupIndex > 1 ? "" : " ") + GROUPS[groupIndex] + " " + result;
//                }
//                num /= 100;
//                groupIndex++;
//            }
//
//            return result.trim();
//        } catch (NumberFormatException e) {
//            return "Invalid number format";
//        }
//    }
//
//    private static String convertTwoDigitsToWord(int num) {
//        if (num < 20) {
//            if (num < 10) {
//                return UNITS[num];
//            } else {
//                return TEENS[num - 10];
//            }
//        } else {
//            return TENS[num / 10] + ((num % 10 == 0) ? "" : " " + UNITS[num % 10]);
//        }
//    }

//    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
//    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
//    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
//
//
//    public static String convertCountToWord(String number) {
//        if (number == null || number.isEmpty()) {
//            return "Please enter a valid number";
//        }
//
//        number = number.trim();
//
//        try {
//            long num = Long.parseLong(number);
//
//            if (num < 0 || num > 999_999_999_999L) {
//                return "Number out of range. Please enter a number between 0 and 999,999,999,999.";
//            }
//
//            if (num == 0) {
//                return "Zero";
//            }
//
//            String result = "";
//
//            int scale = 0;
//            while (num > 0) {
//                int lastThreeDigits = (int) (num % 1000);
//                if (lastThreeDigits != 0) {
//                    result = convertThreeDigitsToWord(lastThreeDigits) + THOUSANDS[scale] + " " + result;
//                }
//                num /= 1000;
//                scale++;
//            }
//
//            return result.trim();
//        } catch (NumberFormatException e) {
//            return "Invalid number format";
//        }
//    }
//
//    private static String convertThreeDigitsToWord(int num) {
//        StringBuilder result = new StringBuilder();
//
//        int hundredDigit = num / 100;
//        if (hundredDigit > 0) {
//            result.append(UNITS[hundredDigit]).append(" Hundred ");
//        }
//
//        int lastTwoDigits = num % 100;
//        if (lastTwoDigits < 20) {
//            result.append(lastTwoDigits == 0 ? "" : TEENS[lastTwoDigits]);
//        } else {
//            int tensDigit = lastTwoDigits / 10;
//            result.append(TENS[tensDigit]);
//            int unitDigit = lastTwoDigits % 10;
//            if (unitDigit > 0) {
//                result.append(" ").append(UNITS[unitDigit]);
//            }
//        }
//
//        return result.toString().trim();
//    }
}

