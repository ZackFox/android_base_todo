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

        public TaskViewHolder(@NonNull CardView view) {
            super(view);
            cardView = view;
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
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        final Task task = taskList.get(position);
        final CardView cardView =  holder.cardView;
        TextView textView = cardView.findViewById(R.id.task_text);
        textView.setText(taskList.get(position).getText());
        textView.setPaintFlags(0);
        showCardState(task,cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "isCompleted: " + task.isCompleted());

                task.setCompleted(!task.isCompleted());
                showCardState(task,cardView);
            }
        });
    }

    public void showCardState(Task task,CardView view) {
        Resources res = view.getContext().getResources();
        TextView textView = view.findViewById(R.id.task_text);

        if(task.isCompleted()){
            view.setCardBackgroundColor(res.getColor(R.color.completed));
            textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            view.setCardBackgroundColor(res.getColor(R.color.active));
            textView.setPaintFlags(0);
        }
    }

    public void updateTaskList (List<Task> list){
        this.taskList = list;
        notifyDataSetChanged();
    }
}
