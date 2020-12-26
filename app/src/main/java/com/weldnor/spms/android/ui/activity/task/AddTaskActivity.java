package com.weldnor.spms.android.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.manager.AuthManager;
import com.weldnor.spms.android.rest.TaskApi;
import com.weldnor.spms.android.rest.dto.NewTaskDto;
import com.weldnor.spms.android.ui.activity.project.ProjectActivity;

import javax.inject.Inject;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";
    private static final long DEFAULT_STATUS_ID = 1;

    @Inject
    AuthManager authManager;

    private EditText nameEditText;
    private EditText descriptionEditText;

    long projectId;
    long userId;

    @Inject
    TaskApi taskApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        nameEditText = findViewById(R.id.name);
        descriptionEditText = findViewById(R.id.description);

        projectId = getIntent().getExtras().getLong("projectId");
        userId = authManager.getUserId();
    }

    public void OnAddTaskButtonClick(View view) {
        try {
            String name = String.valueOf(nameEditText.getText());
            String description = String.valueOf(descriptionEditText.getText());

            NewTaskDto newTaskDto = new NewTaskDto(projectId, userId, name, description, DEFAULT_STATUS_ID);
            Log.i(TAG, "OnAddTaskButtonClick: " + newTaskDto);

            taskApi.addTask(newTaskDto).subscribe(task -> {
                goToProjectActivity();
            });
        } catch (Exception e) {
            Log.e(TAG, "OnAddTaskButtonClick: ", e);
        }
    }

    private void goToProjectActivity() {
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }
}
