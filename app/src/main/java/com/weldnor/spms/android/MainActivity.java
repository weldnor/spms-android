package com.weldnor.spms.android;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.di.component.DaggerAppComponent;
import com.weldnor.spms.android.rest.UserApi;

import javax.inject.Inject;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Inject
    UserApi userApi;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerAppComponent.create().inject(this);

        userApi.getAllUsers().enqueue(new retrofit2.Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.i("MainActivity", "yeap! response");
                //
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("MainActivity", "oh no", t);
                //
            }
        });
        Log.i("MainActivity", String.valueOf(userApi.getAllUsers()));
    }
}