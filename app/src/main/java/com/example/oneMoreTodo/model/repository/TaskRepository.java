package com.example.oneMoreTodo.model.repository;

import com.example.oneMoreTodo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository  {

   private List<Task> tasks;

   public TaskRepository() {
       this.tasks = new ArrayList<>();
   }

   public List<Task> getAllTask(){
       tasks.add(new Task("купить молочка"));
       tasks.add(new Task("купить огурчики"));
       tasks.add(new Task("купить картошечку"));
       tasks.add(new Task("купить рыбку"));
       tasks.add(new Task("купить пивка"));
       tasks.add(new Task("купить памперсы"));
       tasks.add(new Task("купить носки"));
       tasks.add(new Task("купить противозачаточные"));
       return tasks;
   }
//
//    Task createTask(){
//
//    }
//
//    Task updateTask(){
//
//    }
//
//    Task deleteTask(){
//
//    }
}
