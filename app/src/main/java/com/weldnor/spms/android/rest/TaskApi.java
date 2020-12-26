package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.Task;
import com.weldnor.spms.android.rest.dto.NewTaskDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskApi {
    @GET("api/tasks/{taskId}")
    Single<Task> getTask(@Path("taskId") long taskId);

    @PUT("api/tasks")
    Single<Task> addTask(@Body NewTaskDto newTaskDto); //FIXME
}
