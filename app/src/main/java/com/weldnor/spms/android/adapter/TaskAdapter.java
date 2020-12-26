package com.weldnor.spms.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weldnor.spms.android.R;
import com.weldnor.spms.android.entity.Project;
import com.weldnor.spms.android.entity.Task;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Task> objects;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        ctx = context;
        objects = tasks;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_task, parent, false);
        }

        Task p = getTask(position);

        ((TextView) view.findViewById(R.id.name)).setText(p.getName());

        return view;
    }

    Task getTask(int position) {
        return ((Task) getItem(position));
    }
}
