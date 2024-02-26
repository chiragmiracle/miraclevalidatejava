package com.validationutility.FormValidation;

import android.app.Activity;

import com.validationutility.FormValidation.validators.BasicValidator;
import com.validationutility.FormValidation.validators.ColorationValidator;
import com.validationutility.FormValidation.validators.Validator;

import java.util.regex.Pattern;

public class MiracleFormValidation {

    private Validator mValidator = null;

    private static boolean autoFocusOnFirstFailure = true;

    public MiracleFormValidation(ValidationStyle style) {
        switch (style) {
            case SIMPLE:
                if (mValidator == null || !(mValidator instanceof BasicValidator)) {
                    mValidator = new BasicValidator();
                }
                return;
            case COLORATION:
                if (mValidator == null || !(mValidator instanceof ColorationValidator)) {
                    mValidator = new ColorationValidator();
                }
                return;
            default:
        }
    }

    public static boolean isAutoFocusOnFirstFailureEnabled() {
        return autoFocusOnFirstFailure;
    }

    private void checkIsColorationValidator() {
        if (!(mValidator instanceof ColorationValidator)) {
            throw new UnsupportedOperationException("Only supported by ColorationValidator.");
        }
    }

    public void setColor(int color) {
        checkIsColorationValidator();
        ((ColorationValidator) mValidator).setColor(color);
    }

    public void addValidation(Activity activity, int viewId, String regex, int errMsgId) {
        mValidator.set(activity, viewId, regex, errMsgId);
    }

    public void addValidation(Activity activity, int viewId, Pattern pattern, int errMsgId) {
        mValidator.set(activity, viewId, pattern, errMsgId);
    }

    public void addValidation(Activity activity, int confirmationViewId, int viewId, int errMsgId) {
        mValidator.set(activity, confirmationViewId, viewId, errMsgId);
    }

    public boolean validate() {
        return mValidator.trigger();
    }

    public void clear() {
        mValidator.halt();
    }

}
