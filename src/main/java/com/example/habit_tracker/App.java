package com.example.habit_tracker; // <-- ADD THIS LINE

import com.example.habit_tracker.models.Habit;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {

    private static final Map<String, Habit> habits = new HashMap<>();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        port(4567);

        // --- Sample Data ---
        habits.put("Read for 15 minutes", new Habit("Read for 15 minutes"));
        habits.put("Go for a walk", new Habit("Go for a walk"));
        habits.put("Drink 8 glasses of water", new Habit("Drink 8 glasses of water"));

        // Enable CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS"); // Add DELETE
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // GET all habits (no change)
        get("/api/habits", (req, res) -> {
            res.type("application/json");
            return gson.toJson(habits.values());
        });

        // POST to complete a habit (no change)
        post("/api/habits/:name/complete", (req, res) -> {
            String habitName = req.params(":name");
            Habit habit = habits.get(habitName);
            if (habit != null) {
                LocalDate today = LocalDate.now();
                if (habit.lastCompletedDate != null && habit.lastCompletedDate.isEqual(today)) {
                    return "Habit already completed today.";
                }
                if (habit.lastCompletedDate != null && habit.lastCompletedDate.isEqual(today.minusDays(1))) {
                    habit.currentStreak++;
                } else {
                    habit.currentStreak = 1;
                }
                habit.lastCompletedDate = today;
                return gson.toJson(habit);
            }
            res.status(404);
            return "Habit not found.";
        });

        // --- NEW: POST to add a new habit ---
        post("/api/habits", (req, res) -> {
            res.type("application/json");
            Habit newHabit = gson.fromJson(req.body(), Habit.class);
            
            if (habits.containsKey(newHabit.name)) {
                res.status(409); // 409 Conflict
                return "{\"error\":\"Habit with this name already exists.\"}";
            }

            habits.put(newHabit.name, newHabit);
            res.status(201); // 201 Created
            return gson.toJson(newHabit);
        });

        // --- NEW: DELETE a habit ---
        delete("/api/habits/:name", (req, res) -> {
            String habitName = req.params(":name");
            if (habits.containsKey(habitName)) {
                habits.remove(habitName);
                res.status(200); // OK
                return "Habit deleted successfully.";
            }
            res.status(404); // Not Found
            return "Habit not found.";
        });
    }
}