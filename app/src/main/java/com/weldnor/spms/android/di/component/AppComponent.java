package com.weldnor.spms.android.di.component;

import com.weldnor.spms.android.di.module.CommonModule;
import com.weldnor.spms.android.di.module.RestServiceModule;
import com.weldnor.spms.android.ui.activity.home.HomeActivity;
import com.weldnor.spms.android.ui.activity.login.LoginActivity;
import com.weldnor.spms.android.ui.activity.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RestServiceModule.class, CommonModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeActivity homeActivity);
}
