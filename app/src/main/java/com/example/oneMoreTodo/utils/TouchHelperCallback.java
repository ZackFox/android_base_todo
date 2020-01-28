package com.example.oneMoreTodo.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneMoreTodo.ui.tasks.TaskListAdapter;

public class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {

    private TaskListAdapter mAdapter;

    public TouchHelperCallback(TaskListAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.deleteItem(viewHolder.getAdapterPosition());
    }
}
