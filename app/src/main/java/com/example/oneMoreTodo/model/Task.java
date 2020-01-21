package com.example.oneMoreTodo.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

public class Task {

    @NonNull
    private UUID id;

    @Nullable
    private String text ;

    private Boolean completed;

    public Task( String text) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.completed = false;
    }

    public Task( String text, Boolean completed) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.completed = completed;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
