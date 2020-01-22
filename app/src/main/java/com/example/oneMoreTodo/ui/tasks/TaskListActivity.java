package com.example.oneMoreTodo.ui.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;
import com.example.oneMoreTodo.model.repository.TaskRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView tasksRecycler;
    private RecyclerView.Adapter tasksAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TaskRepository taskRepository = new TaskRepository();
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = taskRepository.getAllTask();
        layoutManager = new LinearLayoutManager(this);
        tasksAdapter = new TaskListAdapter(tasks);

        tasksRecycler = findViewById(R.id.todo_container);
        tasksRecycler.setLayoutManager(layoutManager);
        tasksRecycler.setAdapter(tasksAdapter);

        BottomNavigationView btmNavView = findViewById(R.id.bottom_menu);
        btmNavView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.btn_all): {
                            //taskRepository.getAllTask();
                            System.out.println("ALL task list");
                            return true;
                        }
                        case (R.id.btn_active): {
                            System.out.println("ACTIVE task list");
                            return true;
                        }
                        case (R.id.btn_completed): {
                            System.out.println("COMPLETED task list");
                            return true;
                        }
                    }

                    return false;
                }
            });
    }


}
