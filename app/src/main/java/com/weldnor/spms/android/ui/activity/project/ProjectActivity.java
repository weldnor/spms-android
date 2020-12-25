package com.weldnor.spms.android.ui.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.adapter.TaskAdapter;
import com.weldnor.spms.android.entity.Task;
import com.weldnor.spms.android.rest.ProjectApi;
import com.weldnor.spms.android.ui.activity.task.TaskActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class ProjectActivity extends AppCompatActivity {

    private static final String TAG = "ProjectActivity";

    @Inject
    ProjectApi projectApi;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        listView = findViewById(R.id.tasks_list);

        long projectId = getIntent().getExtras().getLong("projectId");

        projectApi.getProject(projectId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(project -> {
                    name.setText(project.getName());
                    description.setText(project.getDescription());
                }, throwable -> {
                    Log.e(TAG, "onCreate: oops", throwable);
                });

        projectApi.getProjectTasks(projectId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayTasks, throwable -> {
                    Log.e(TAG, "onCreate: oops", throwable);
                });


    }

    private void displayTasks(List<Task> tasks) {
        TaskAdapter taskAdapter = new TaskAdapter(this, new ArrayList<>(tasks));
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task currentTask = tasks.get((int) id);
            goToTask(currentTask.getTaskId());
        });
    }

    private void goToTask(Long taskId) {
        Log.i(TAG, "goToTask: " + taskId);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskId", taskId);
        startActivity(intent);
    }


}