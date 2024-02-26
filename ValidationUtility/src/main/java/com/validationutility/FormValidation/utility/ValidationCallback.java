package com.validationutility.FormValidation.utility;

import com.validationutility.FormValidation.ValidationHolder;

import java.util.regex.Matcher;

public interface ValidationCallback {

    void execute(ValidationHolder validationHolder, Matcher matcher);

}