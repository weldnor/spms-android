package com.weldnor.spms.android.di.module;

import android.util.Log;

import com.weldnor.spms.android.entity.User;
import com.weldnor.spms.android.rest.UserApi;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class RestServiceModule {
    private static final String BASE_API_URL = "http://192.168.1.13:8000";

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    public UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    public OkHttpClient provideLogClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.i("RestServiceModule", chain.request().toString());
//                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + token)
//                        .build();
                return chain.proceed(chain.request());
            }
        }).build();
    }
}
