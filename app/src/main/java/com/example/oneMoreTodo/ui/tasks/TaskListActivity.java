package com.example.oneMoreTodo.ui.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;
import com.example.oneMoreTodo.model.repository.TaskRepository;
import com.example.oneMoreTodo.ui.addTask.AddTaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private static final String TAG = "TaskListActivity";

    private RecyclerView tasksRecycler;
    private TaskListAdapter tasksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView addTaskBtn;

    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRepository = TaskRepository.getInstance();

        tasksRecycler = findViewById(R.id.todo_container);

        tasksAdapter = new TaskListAdapter(taskRepository.getAllTask());
        tasksRecycler.setAdapter(tasksAdapter);

        layoutManager = new LinearLayoutManager(this);
        tasksRecycler.setLayoutManager(layoutManager);

        addTaskBtn = findViewById(R.id.add_task_btn);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClickAddButton: ADD NEW TASK");

                FragmentManager manager = getSupportFragmentManager();
                AddTaskFragment addTaskDialog = new AddTaskFragment();
                addTaskDialog.show(manager, "NEWTASK");

            }
        });

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_menu);
        bottomNavView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.btn_all): {
                            tasksAdapter.updateTaskList(taskRepository.getAllTask());
                            Log.d(TAG, "ItemSelected: ALL");
                            return true;
                        }
                        case (R.id.btn_active): {
                            tasksAdapter.updateTaskList(taskRepository.getActiveTasks());
                            Log.d(TAG, "ItemSelected: ACTIVE");;
                            return true;
                        }
                        case (R.id.btn_completed): {
                            tasksAdapter.updateTaskList(taskRepository.getCompletedTasks());
                            Log.d(TAG, "ItemSelected: COMPLETED");
                            return true;
                        }
                    }

                    return false;
                }
            });
    }

    public void applyTaskText (String text){
        taskRepository.createTask(text);
        tasksAdapter.notifyDataSetChanged();
    }
}