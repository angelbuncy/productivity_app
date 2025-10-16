package com.example.habit_tracker.models;

import java.time.LocalDate;

public class Habit {
    String name;
    int currentStreak;
    // We use transient so Gson doesn't include this field in the JSON output
    transient LocalDate lastCompletedDate;

    public Habit(String name) {
        this.name = name;
        this.currentStreak = 0;
        this.lastCompletedDate = null;
    }
}