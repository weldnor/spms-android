package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("api/users")
    Call<List<User>> getAllUsers();
}
