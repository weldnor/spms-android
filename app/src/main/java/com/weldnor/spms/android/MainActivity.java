package com.weldnor.spms.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.di.component.DaggerAppComponent;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.GlobalRoleApi;
import com.weldnor.spms.android.ui.activity.LoginActivity;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
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

        DaggerAppComponent.create().inject(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}