package com.weldnor.spms.android.ui.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.adapter.TaskAdapter;
import com.weldnor.spms.android.entity.Task;
import com.weldnor.spms.android.rest.ProjectApi;
import com.weldnor.spms.android.ui.activity.task.AddTaskActivity;
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

    private Long projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        listView = findViewById(R.id.tasks_list);

        projectId = getIntent().getExtras().getLong("projectId");

        projectApi.getProject(projectId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(project -> {
                    name.setText(project.getName());
                    description.setText(project.getDescription());
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
            goToTaskActivity(currentTask.getTaskId());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project, menu);// Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        goToAddTaskActivity();
        return true; //FIXME
    }

    private void goToAddTaskActivity() {
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }

    private void goToTaskActivity(Long taskId) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("taskId", taskId);
        startActivity(intent);
    }


}