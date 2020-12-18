package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.Project;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ProjectApi {
    @GET("api/projects")
    Single<List<Project>> getAllProjects();
}
