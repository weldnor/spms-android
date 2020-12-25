package com.weldnor.spms.android.rest;

import com.weldnor.spms.android.entity.Task;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TaskApi {
    @GET("api/tasks/{taskId}")
    Single<Task> getTask(@Path("taskId") long taskId);
}
