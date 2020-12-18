package com.weldnor.spms.android.manager;

import android.util.Log;

import com.weldnor.spms.android.rest.LoginApi;
import com.weldnor.spms.android.rest.dto.BasicAuthRequest;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class AuthManager {
    private static final String TAG = "AuthManager";

    @Inject
    LoginApi loginApi;

    private String token;


    public Single<String> login(String username, String password) {
        BasicAuthRequest authRequest = new BasicAuthRequest(username, password);
        return loginApi.basicLogin(authRequest).map((basicAuthResponse) -> {
            Log.i(TAG, String.valueOf(basicAuthResponse));
            token = basicAuthResponse.getToken();
            return token;
        });
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthorized() {
        return token != null;
    }

    @Inject
    public void setLoginApi(LoginApi loginApi) {
        this.loginApi = loginApi;
    }
}
