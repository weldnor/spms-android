package com.weldnor.spms.android.di.module;

import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.LoginApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {
    @Provides
    @Singleton
    public AuthManager provideAuthManager(LoginApi loginApi) {
        AuthManager authManager = new AuthManager();
        authManager.setLoginApi(loginApi);
        return authManager;
    }
}
