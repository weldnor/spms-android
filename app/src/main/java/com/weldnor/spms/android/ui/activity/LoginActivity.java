package com.weldnor.spms.android.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.R;
import com.weldnor.spms.android.di.component.DaggerAppComponent;
import com.weldnor.spms.android.manager.AuthManager;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Inject
    AuthManager authManager;

    private EditText loginInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAppComponent.create().inject(this);

        setContentView(R.layout.activity_login);

        loginInput = (EditText) findViewById(R.id.input_login);
        passwordInput = (EditText) findViewById(R.id.input_password);
    }

    public void OnLoginButtonClick(View view) {
        Log.i(TAG, String.valueOf(loginInput.getText()));
        Log.i(TAG, String.valueOf(passwordInput.getText()));
        try {
            authManager.login(String.valueOf(loginInput.getText()), String.valueOf(passwordInput.getText()));
        } catch (Exception e) {

        }

    }
}