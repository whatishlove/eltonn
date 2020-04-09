package com.example.eltonn;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class UserValidation {
    private Context context;

    public UserValidation(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextFilled(EditText etUsername, EditText etPassword) {
        String value = etUsername.getText().toString().trim();
        String value1 = etPassword.getText().toString().trim();
        if(value.isEmpty() || value1.isEmpty()) {
            Toast.makeText(context, "Your username/password is not filled. Please try again...", Toast.LENGTH_LONG).show();
            //hide keyboard
            hideKeyboardFrom(etUsername);
            hideKeyboardFrom(etPassword);
            return false;
        } else {
            //proceed to HomePage
        }

        return true;
    }

    public boolean isInputEditTextEmail(EditText etEmail) {
        String value = etEmail.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            Toast.makeText(context, "Your email is not filled/incorrect. Please try again...", Toast.LENGTH_LONG).show();
            //hide keyboard
            hideKeyboardFrom(etEmail);
            return false;
        } else {
            //proceed to Login Page
        }
        return true;
    }

    public boolean isInputEditTextMatches(EditText etPassword1, EditText etPassword2) {
        String value1 = etPassword1.getText().toString().trim();
        String value2 = etPassword2.getText().toString().trim();
        if(!value1.contentEquals(value2)) {
            Toast.makeText(context, "Your password and confirm password is not filled/incorrect. Please try again...", Toast.LENGTH_LONG).show();
            //hide keyboard
            hideKeyboardFrom(etPassword1);
            hideKeyboardFrom(etPassword2);
            return false;
        } else {
            //proceed to Login Page
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
