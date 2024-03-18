package com.miracle.validationutility.FormValidation.utility;

import com.miracle.validationutility.FormValidation.ValidationHolder;

import java.util.regex.Matcher;

public interface ValidationCallback {

    void execute(ValidationHolder validationHolder, Matcher matcher);

}