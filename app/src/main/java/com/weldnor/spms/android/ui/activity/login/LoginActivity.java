package com.weldnor.spms.android.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.ui.activity.home.HomeActivity;

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

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_login);

        loginInput = findViewById(R.id.input_login);
        passwordInput = findViewById(R.id.input_password);
    }

    public void OnLoginButtonClick(View view) {
        String login = String.valueOf(loginInput.getText());
        String password = String.valueOf(passwordInput.getText());

        authManager.login(login, password).subscribe(token -> {
                    Log.i(TAG, "token: " + token);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                },
                throwable -> Log.i(TAG, "err", throwable));
    }
}