package com.weldnor.spms.android.ui.activity.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.rest.ProjectApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class ProjectActivity extends AppCompatActivity {

    private static final String TAG = "ProjectActivity";

    @Inject
    ProjectApi projectApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);

        long projectId = getIntent().getExtras().getLong("projectId");

        projectApi.getProject(projectId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(project -> {
                    name.setText(project.getName());
                    description.setText(project.getDescription());
                }, throwable -> {
                    Log.e(TAG, "onCreate: oops", throwable);
                });

    }
}