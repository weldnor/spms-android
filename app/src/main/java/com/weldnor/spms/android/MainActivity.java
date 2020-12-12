package com.weldnor.spms.android;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.di.component.DaggerAppComponent;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.GlobalRoleApi;

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
        setContentView(R.layout.activity_main);

        DaggerAppComponent.create().inject(this);

        authManager.setToken("...");

        Log.i("MainActivity", authManager.getToken());
        globalRoleApi.getAllGlobalRoles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(globalRoles -> {
                    Log.i("MainActivity", globalRoles.toString());
                }, throwable -> {
                    Log.i("MainActivity", "oops", throwable);
                });

    }
}