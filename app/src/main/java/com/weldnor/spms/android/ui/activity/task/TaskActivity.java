package com.weldnor.spms.android.ui.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.weldnor.spms.android.MyApplication;
import com.weldnor.spms.android.R;
import com.weldnor.spms.android.rest.TaskApi;
import com.weldnor.spms.android.rest.UserApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class TaskActivity extends AppCompatActivity {

    private static final String TAG = "TaskActivity";

    @Inject
    TaskApi taskApi;

    @Inject
    UserApi userApi;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        ((MyApplication) getApplication()).getAppComponent().inject(this);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView creatorUsername = findViewById(R.id.creator_username);
        listView = findViewById(R.id.tasks_list);

        long taskId = getIntent().getExtras().getLong("taskId");

        taskApi.getTask(taskId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(project -> {
                    name.setText(project.getName());
                    description.setText(project.getDescription());
                }, throwable -> {
                    Log.e(TAG, "onCreate: oops", throwable);
                });
    }

    public void OnShareButtonClick(View view) {
        Intent intent = new Intent(); intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Your text here" );
        startActivity(Intent.createChooser(intent, "Share via"));
    }

}



