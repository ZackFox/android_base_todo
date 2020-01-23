package com.example.oneMoreTodo.ui.tasks;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskListAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent,false);
        return new TaskViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        CardView cardView =  holder.cardView;
        TextView textView = cardView.findViewById(R.id.task_text);
        textView.setText(taskList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public void updateTaskList (List<Task> list){
        this.taskList = list;
        notifyDataSetChanged();
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public TaskViewHolder(@NonNull CardView view) {
            super(view);
            cardView = view;
        }
    }
}
