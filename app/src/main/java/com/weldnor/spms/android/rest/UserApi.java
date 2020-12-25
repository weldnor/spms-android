package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface UserApi {
    @GET("api/users")
    Single<List<User>> getAllUsers();

    @GET("api/users/{userId}")
    Single<User> getUser(@Path("userId") long userId);
}
