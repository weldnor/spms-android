package com.weldnor.spms.android.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.adapter.ProjectAdapter;
import com.weldnor.spms.android.entity.Project;
import com.weldnor.spms.android.rest.ProjectApi;
import com.weldnor.spms.android.rest.UserApi;
import com.weldnor.spms.android.ui.activity.project.AddProjectActivity;
import com.weldnor.spms.android.ui.activity.project.ProjectActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Inject
    UserApi userApi;

    @Inject
    ProjectApi projectApi;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((MyApplication) getApplication()).getAppComponent().inject(this);
        listView = findViewById(R.id.listView);

        projectApi.getAllProjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayProjects, throwable -> Log.e(TAG, "oops", throwable));
    }

    private void displayProjects(List<Project> projects) {
        ProjectAdapter projectAdapter = new ProjectAdapter(this, new ArrayList<>(projects));
        listView.setAdapter(projectAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Project currentProject = projects.get((int) id);
            goToProjectActivity(currentProject.getProjectId());
        });
    }

    private void goToProjectActivity(long projectId) {
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);// Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, AddProjectActivity.class);
        startActivity(intent);
        return true; //FIXME
    }
}