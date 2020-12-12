package com.weldnor.spms.android.manager;

import com.weldnor.spms.android.rest.LoginApi;

import javax.inject.Inject;

public class AuthManager {

    private LoginApi loginApi;

    private String token;


    public void login(String username, String password) {
        // TODO
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
