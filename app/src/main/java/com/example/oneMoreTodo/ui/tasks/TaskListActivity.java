package com.example.oneMoreTodo.ui.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;
import com.example.oneMoreTodo.model.repository.TaskRepository;
import com.example.oneMoreTodo.ui.addTask.AddTaskFragment;
import com.example.oneMoreTodo.utils.TouchHelperCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TaskListActivity extends AppCompatActivity {

    private static final String TAG = "TaskListActivity";

    private RecyclerView tasksRecycler;
    private TaskListAdapter tasksAdapter;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView addTaskBtn;

    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRepository = TaskRepository.getInstance();

        // set  tasksRecycler
        tasksRecycler = findViewById(R.id.todo_container);
        tasksAdapter = new TaskListAdapter(taskRepository.getAllTask());
        layoutManager = new LinearLayoutManager(this);

        tasksRecycler.setAdapter(tasksAdapter);
        tasksRecycler.setLayoutManager(layoutManager);

        // add touch to tasksRecycler
        itemTouchHelper = new ItemTouchHelper(new TouchHelperCallback(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecycler);


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