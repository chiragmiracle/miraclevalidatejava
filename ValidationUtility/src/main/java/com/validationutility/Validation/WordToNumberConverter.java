package com.validationutility.Validation;

import java.util.HashMap;
import java.util.Map;

public class WordToNumberConverter {

    private static final Map<String, Long> NUMBER_WORDS_MAP = new HashMap<>();

    static {
        NUMBER_WORDS_MAP.put("zero", 0L);
        NUMBER_WORDS_MAP.put("one", 1L);
        NUMBER_WORDS_MAP.put("two", 2L);
        NUMBER_WORDS_MAP.put("three", 3L);
        NUMBER_WORDS_MAP.put("four", 4L);
        NUMBER_WORDS_MAP.put("five", 5L);
        NUMBER_WORDS_MAP.put("six", 6L);
        NUMBER_WORDS_MAP.put("seven", 7L);
        NUMBER_WORDS_MAP.put("eight", 8L);
        NUMBER_WORDS_MAP.put("nine", 9L);
        NUMBER_WORDS_MAP.put("ten", 10L);
        NUMBER_WORDS_MAP.put("eleven", 11L);
        NUMBER_WORDS_MAP.put("twelve", 12L);
        NUMBER_WORDS_MAP.put("thirteen", 13L);
        NUMBER_WORDS_MAP.put("fourteen", 14L);
        NUMBER_WORDS_MAP.put("fifteen", 15L);
        NUMBER_WORDS_MAP.put("sixteen", 16L);
        NUMBER_WORDS_MAP.put("seventeen", 17L);
        NUMBER_WORDS_MAP.put("eighteen", 18L);
        NUMBER_WORDS_MAP.put("nineteen", 19L);
        NUMBER_WORDS_MAP.put("twenty", 20L);
        NUMBER_WORDS_MAP.put("thirty", 30L);
        NUMBER_WORDS_MAP.put("forty", 40L);
        NUMBER_WORDS_MAP.put("fifty", 50L);
        NUMBER_WORDS_MAP.put("sixty", 60L);
        NUMBER_WORDS_MAP.put("seventy", 70L);
        NUMBER_WORDS_MAP.put("eighty", 80L);
        NUMBER_WORDS_MAP.put("ninety", 90L);
        NUMBER_WORDS_MAP.put("hundred", 100L);
        NUMBER_WORDS_MAP.put("thousand", 1000L);
        NUMBER_WORDS_MAP.put("million", 1000000L);
        NUMBER_WORDS_MAP.put("billion", 1000000000L);
    }

    public static long convertWordToNumber(String words) {
        if (words == null || words.isEmpty()) {
            return -1;
        }

        long result = 0;
        long tempResult = 0;
        long scale = 1;

        String[] parts = words.trim().toLowerCase().split("\\s+");

        for (String part : parts) {
            if ("and".equals(part)) {
                continue;
            }
            if (!NUMBER_WORDS_MAP.containsKey(part)) {
                return -1;
            }
            long number = NUMBER_WORDS_MAP.get(part);
            if (number == 100) {
                tempResult *= number;
            } else if (number >= 1000) {
                result += tempResult * number;
                tempResult = 0;
                scale *= number;
            } else {
                tempResult += number;
            }
        }

        return result + tempResult;
    }
}
