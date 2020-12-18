package com.weldnor.spms.android;

import com.weldnor.spms.android.di.component.AppComponent;
import com.weldnor.spms.android.di.component.DaggerAppComponent;

public class MyApplication extends android.app.Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
