package com.weldnor.spms.android.ui.activity.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.rest.ProjectApi;
import com.weldnor.spms.android.rest.dto.NewProjectDto;
import com.weldnor.spms.android.ui.activity.home.HomeActivity;

import javax.inject.Inject;

public class AddProjectActivity extends AppCompatActivity {

    private static final String TAG = "AddProjectActivity";

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText ownerIdEditText;

    @Inject
    ProjectApi projectApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        nameEditText = findViewById(R.id.name);
        descriptionEditText = findViewById(R.id.description);
        ownerIdEditText = findViewById(R.id.owner_id);
    }

    public void OnAddProjectButtonClick(View view) {
        String name = String.valueOf(nameEditText.getText());
        String description = String.valueOf(descriptionEditText.getText());
        long ownerId = Long.parseLong(String.valueOf(ownerIdEditText.getText()));

        NewProjectDto newProjectDto = new NewProjectDto(name, description, ownerId);

        projectApi.addProject(newProjectDto).subscribe((project, throwable) -> {
            if (throwable != null) {
                Log.e(TAG, "OnAddProjectButtonClick: ", throwable);
            }

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
    }
}