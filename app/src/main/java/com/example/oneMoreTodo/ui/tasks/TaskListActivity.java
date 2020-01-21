package com.example.oneMoreTodo.ui.tasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;
import com.example.oneMoreTodo.model.repository.TaskRepository;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {
    private RecyclerView tasksRecycler;
    private RecyclerView.Adapter tasksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = (new TaskRepository()).getAllTask();
        layoutManager = new LinearLayoutManager(this);
        tasksAdapter = new TaskListAdapter(tasks);

        tasksRecycler = findViewById(R.id.todo_container);
        tasksRecycler.setLayoutManager(layoutManager);
        tasksRecycler.setAdapter(tasksAdapter);


    }
}
