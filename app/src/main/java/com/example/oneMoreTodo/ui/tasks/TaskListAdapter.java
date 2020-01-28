package com.example.oneMoreTodo.ui.tasks;

import android.content.res.Resources;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.model.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private static final String TAG = "TaskListAdapter";
    private List<Task> taskList;

    public TaskListAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textView;

        public TaskViewHolder(@NonNull CardView view) {
            super(view);
            this.cardView = view;
            textView = this.cardView.findViewById(R.id.task_text);
        }

        public void showCardState(boolean isCompleted) {
            CardView cardView = this.cardView;
            Resources res = cardView.getContext().getResources();
            TextView textView = this.textView;

            if(isCompleted){
                cardView.setCardBackgroundColor(res.getColor(R.color.completed));
                textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                cardView.setCardBackgroundColor(res.getColor(R.color.active));
                textView.setPaintFlags(0);
            }
        }

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent,false);
        return new TaskViewHolder(cardView);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position) {
        final Task task = taskList.get(position);
        holder.textView.setText(task.getText());
        holder.showCardState(task.isCompleted());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "isCompleted: " + task.isCompleted());

                task.setCompleted(!task.isCompleted());
                holder.showCardState(task.isCompleted());
            }
        });
    }

    public void updateTaskList (List<Task> list){
        this.taskList = list;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.taskList.remove(position);
        notifyDataSetChanged();
    }
}
