package com.weldnor.spms.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.GlobalRoleApi;

import javax.inject.Inject;

import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    @Inject
    GlobalRoleApi globalRoleApi;

    @Inject
    AuthManager authManager;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}