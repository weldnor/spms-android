package com.weldnor.spms.android.di.module;

import android.util.Log;

import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.GlobalRoleApi;
import com.weldnor.spms.android.rest.LoginApi;
import com.weldnor.spms.android.rest.ProjectApi;
import com.weldnor.spms.android.rest.TaskApi;
import com.weldnor.spms.android.rest.UserApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class RestServiceModule {
    private static final String BASE_API_URL = "http://192.168.1.13:8000";

    @Provides
    @Singleton
    @Named("BasicRetrofit")
    public Retrofit provideBasicRetrofit(
            @Named("LogClient") OkHttpClient logClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(logClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("RetrofitWithAuth")
    public Retrofit extendedRetrofit(
            @Named("TokenClient") OkHttpClient tokenClient,
            @Named("LogClient") OkHttpClient logClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(logClient)
                .client(tokenClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    @Named("LogClient")
    public OkHttpClient provideLogClient() {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Log.i("RestServiceModule", chain.request().toString());
            return chain.proceed(chain.request());
        }).build();
    }

    @Provides
    @Singleton
    @Named("TokenClient")
    public OkHttpClient provideTokenClient(AuthManager authManager) {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            String token = authManager.getToken();
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }).build();
    }

    // ----------------------- API

    @Provides
    @Singleton
    public LoginApi provideLoginApi(@Named("BasicRetrofit") Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

    @Provides
    @Singleton
    public UserApi provideUserApi(@Named("RetrofitWithAuth") Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Singleton
    public GlobalRoleApi provideGlobalRoleApi(@Named("RetrofitWithAuth") Retrofit retrofit) {
        return retrofit.create(GlobalRoleApi.class);
    }

    @Provides
    @Singleton
    public ProjectApi provideProjectApi(@Named("RetrofitWithAuth") Retrofit retrofit) {
        return retrofit.create(ProjectApi.class);
    }

    @Provides
    @Singleton
    public TaskApi provideTaskApi(@Named("RetrofitWithAuth") Retrofit retrofit) {
        return retrofit.create(TaskApi.class);
    }

}
