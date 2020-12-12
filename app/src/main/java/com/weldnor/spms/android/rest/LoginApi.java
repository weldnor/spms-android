package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.rest.dto.BasicAuthRequest;
import com.weldnor.spms.android.rest.dto.BasicAuthResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginApi {
    @POST("api/public/login/basic")
    Single<BasicAuthResponse> basicLogin(@Body BasicAuthRequest basicAuthRequest);
}
