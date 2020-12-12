package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.GlobalRole;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface GlobalRoleApi {
    @GET("api/global_roles")
    Single<List<GlobalRole>> getAllGlobalRoles();
}
