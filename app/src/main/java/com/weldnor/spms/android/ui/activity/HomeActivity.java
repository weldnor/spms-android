package com.weldnor.spms.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.di.component.DaggerAppComponent;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.GlobalRoleApi;
import com.weldnor.spms.android.rest.UserApi;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Inject
    UserApi userApi;

    @Inject
    GlobalRoleApi globalRoleApi;

    @Inject
    AuthManager authManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        globalRoleApi.getAllGlobalRoles().subscribe((roles, throwable) -> {
            Log.i(TAG, String.valueOf(roles));
        });
    }
}