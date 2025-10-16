package com.example.habit_tracker.models;

public class TodoItem {
    public long id;
    public String text;
    public boolean isCompleted;

    // A default constructor is needed for Gson to work when creating new items
    public TodoItem() {}

    public TodoItem(long id, String text) {
        this.id = id;
        this.text = text;
        this.isCompleted = false;
    }
}