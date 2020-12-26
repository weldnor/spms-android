package com.weldnor.spms.android.di.component;

import com.weldnor.spms.android.di.module.CommonModule;
import com.weldnor.spms.android.di.module.RestServiceModule;
import com.weldnor.spms.android.ui.activity.home.HomeActivity;
import com.weldnor.spms.android.ui.activity.login.LoginActivity;
import com.weldnor.spms.android.ui.activity.main.MainActivity;
import com.weldnor.spms.android.ui.activity.project.AddProjectActivity;
import com.weldnor.spms.android.ui.activity.project.ProjectActivity;
import com.weldnor.spms.android.ui.activity.task.AddTaskActivity;
import com.weldnor.spms.android.ui.activity.task.TaskActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RestServiceModule.class, CommonModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeActivity homeActivity);

    void inject(ProjectActivity projectActivity);

    void inject(AddProjectActivity addProjectActivity);

    void inject(TaskActivity taskActivity);

    void inject(AddTaskActivity addTaskActivity);
}
