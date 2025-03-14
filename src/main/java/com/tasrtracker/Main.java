package com.tasrtracker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task Tracker started!");

        //Create a task
        Task task = new Task(1, "Buy Groceries");
        System.out.println(task);

        //Update the description
        task.setDescription("Buy groceries and cook dinner");
        System.out.println(task);

        //Mark as in progress
        task.setStatus("in-progress");
        System.out.println(task);
    }
}
