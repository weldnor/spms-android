package com.weldnor.spms.android.di.component;

import com.weldnor.spms.android.MainActivity;
import com.weldnor.spms.android.di.module.CommonModule;
import com.weldnor.spms.android.di.module.RestServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RestServiceModule.class, CommonModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
