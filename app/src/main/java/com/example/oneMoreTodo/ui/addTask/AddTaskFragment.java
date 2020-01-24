package com.example.oneMoreTodo.ui.addTask;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.oneMoreTodo.R;
import com.example.oneMoreTodo.ui.tasks.TaskListActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskFragment extends DialogFragment {

    View dialog;
    TextInputEditText editText;
    TaskListActivity parentActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = getActivity().getLayoutInflater().inflate(R.layout.add_task_dialog, null);
        editText = dialog.findViewById(R.id.new_task_input);

        return new AlertDialog.Builder(getActivity())
            .setTitle("Новая задача")
            .setView(dialog)
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    parentActivity.applyTaskText(editText.getText().toString());
                }
            })
            .create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (TaskListActivity) context;
    }
}
