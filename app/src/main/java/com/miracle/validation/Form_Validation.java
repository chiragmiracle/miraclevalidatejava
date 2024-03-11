package com.miracle.validation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.validationutility.FormValidation.MiracleFormValidation;
import com.validationutility.FormValidation.ValidationStyle;
import com.validationutility.FormValidation.utility.RegexTemplate;


public class Form_Validation extends AppCompatActivity {

    EditText ll_userid, ll_first_name, ll_last_name, ll_phone_number, ll_email, ll_password, ll_password1, ll_address, ll_zip_code;
    AppCompatButton ll_clear, ll_submit;
    private MiracleFormValidation miracleValidation;
    private LinearLayout mViewSuccess;
    private ScrollView mScrollView;
    RadioGroup rg_validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_validation);

        initView();

        clearValidation();
        miracleValidation = new MiracleFormValidation(ValidationStyle.SIMPLE);
        addValidationForEditText(Form_Validation.this);
        rg_validation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_simple) {
                    miracleValidation = new MiracleFormValidation(ValidationStyle.SIMPLE);
                    clearValidation();
                    addValidationForEditText(Form_Validation.this);
                } else if (checkedId == R.id.rb_color) {
                    miracleValidation = new MiracleFormValidation(ValidationStyle.COLORATION);
                    miracleValidation.setColor(Color.RED);
                    clearValidation();
                    addValidationForEditText(Form_Validation.this);
                }
            }
        });

        ll_clear.setOnClickListener(v -> {
            clearValidation();
        });

        ll_submit.setOnClickListener(v -> {
            submitValidation();
        });

    }

    private void addValidationForEditText(Activity activity) {
        miracleValidation.addValidation(activity, R.id.ll_userid, "[a-zA-Z0-9_-]+", R.string.error_userid);
        miracleValidation.addValidation(activity, R.id.ll_first_name, "[a-zA-Z\\s]+", R.string.error_name);
        miracleValidation.addValidation(activity, R.id.ll_last_name, "[a-zA-Z\\s]+", R.string.error_name);
        miracleValidation.addValidation(activity, R.id.ll_phone_number, RegexTemplate.TELEPHONE, R.string.error_tel);
        miracleValidation.addValidation(activity, R.id.ll_email, Patterns.EMAIL_ADDRESS, R.string.error_email);
        miracleValidation.addValidation(activity, R.id.ll_password, "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}", R.string.error_password);
        miracleValidation.addValidation(activity, R.id.ll_password1, R.id.ll_password, R.string.error_password_confirmation);
        miracleValidation.addValidation(activity, R.id.ll_zip_code, "\\d+", R.string.error_zipcode);
    }

    private void clearValidation() {
        if (miracleValidation != null) {
            miracleValidation.clear();
            mViewSuccess.setVisibility(View.GONE);
            ll_userid.getText().clear();
            ll_first_name.getText().clear();
            ll_last_name.getText().clear();
            ll_phone_number.getText().clear();
            ll_email.getText().clear();
            ll_password.getText().clear();
            ll_password1.getText().clear();
            ll_zip_code.getText().clear();
        }
    }

    private void submitValidation() {
        if (miracleValidation.validate()) {
            mScrollView.fullScroll(View.FOCUS_DOWN);
            mViewSuccess.setVisibility(View.VISIBLE);
        } else {
            mViewSuccess.setVisibility(View.GONE);
        }
    }

    private void initView() {
        ll_userid = findViewById(R.id.ll_userid);
        ll_first_name = findViewById(R.id.ll_first_name);
        ll_last_name = findViewById(R.id.ll_last_name);
        ll_phone_number = findViewById(R.id.ll_phone_number);
        ll_email = findViewById(R.id.ll_email);
        ll_password = findViewById(R.id.ll_password);
        ll_password1 = findViewById(R.id.ll_password1);
        ll_address = findViewById(R.id.ll_address);
        ll_zip_code = findViewById(R.id.ll_zip_code);
        ll_clear = findViewById(R.id.ll_clear);
        ll_submit = findViewById(R.id.ll_submit);
        mScrollView = findViewById(R.id.scroll_view);
        mViewSuccess = findViewById(R.id.container_success);
        rg_validation = findViewById(R.id.rg_validation);
    }
}