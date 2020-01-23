package com.example.oneMoreTodo.model.repository;

import com.example.oneMoreTodo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository  {
    private static List<Task> tasks = new ArrayList<>();
    private static TaskRepository INSTANCE = null;

    public TaskRepository() {
       this.tasks = new ArrayList<>();
        tasks.add(new Task("Купить молочка"));
        tasks.add(new Task("Купить огурчики",true));
        tasks.add(new Task("Купить рыбку"));
        tasks.add(new Task("Купить пивка", true));
    }

    public static TaskRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public List<Task> getAllTask(){
       return this.tasks;
   }

    public void createTask(String text){
        Task task = new Task(text);
        this.tasks.add(task);
    }

    private List<Task> filterTasks (Boolean isCompleted){
        List<Task> filtered = new ArrayList();

        for(Task task : tasks){
            if(task.isCompleted() == isCompleted ){
                filtered.add(task);
            }
        }
        return filtered;
    }

    public List<Task> getActiveTasks (){
        return this.filterTasks(false);
    }

    public List<Task> getCompletedTasks (){
        return this.filterTasks(true);
    }
//
//    Task updateTask(){
//
//    }
//
//    Task deleteTask(){
//
//    }
}
