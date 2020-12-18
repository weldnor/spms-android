package com.weldnor.spms.android.manager;

import android.util.Log;

import com.weldnor.spms.android.rest.LoginApi;
import com.weldnor.spms.android.rest.dto.BasicAuthRequest;

import javax.inject.Inject;

public class AuthManager {
    private static final String TAG = "AuthManager";

    @Inject
    LoginApi loginApi;

    private String token;


    public void login(String username, String password) throws InterruptedException {
        BasicAuthRequest authRequest = new BasicAuthRequest(username, password);

        loginApi.basicLogin(authRequest).subscribe((basicAuthResponse) -> {
                    Log.i(TAG, String.valueOf(basicAuthResponse));
                },
                throwable -> {
                    Log.e(TAG, "oops", throwable);
                }).wait();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Inject
    public void setLoginApi(LoginApi loginApi) {
        this.loginApi = loginApi;
    }
}
