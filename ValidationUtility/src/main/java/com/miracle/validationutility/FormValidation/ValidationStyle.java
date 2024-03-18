package com.miracle.validationutility.FormValidation;

public enum ValidationStyle {

    SIMPLE(0),
    COLORATION(1);

    private int mValue;

    ValidationStyle(int value) {
        mValue = value;
    }


    public static ValidationStyle fromValue(int value) {
        switch (value) {
            case 0:
                return ValidationStyle.SIMPLE;
            case 1:
                return ValidationStyle.COLORATION;
            default:
                throw new IllegalArgumentException("Unknown ValidationStyle value.");
        }
    }

}