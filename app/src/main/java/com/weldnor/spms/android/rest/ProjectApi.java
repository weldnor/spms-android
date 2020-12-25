package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.Project;
import com.weldnor.spms.android.entity.Task;
import com.weldnor.spms.android.rest.dto.NewProjectDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProjectApi {
    @GET("api/projects")
    Single<List<Project>> getAllProjects();

    @GET("api/projects/{projectId}")
    Single<Project> getProject(@Path("projectId") long projectId);

    @PUT("api/projects")
    Single<Project> addProject(@Body NewProjectDto projectDto);

    @GET("api/projects/{projectId}/tasks")
    Single<List<Task>> getProjectTasks(@Path("projectId") long projectId);
}
