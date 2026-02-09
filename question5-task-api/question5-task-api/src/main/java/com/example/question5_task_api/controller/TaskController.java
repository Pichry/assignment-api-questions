package com.example.question5_task_api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.question5_task_api.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class TaskController {


        private List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task(1L, "Study", "Prepare for exam", false, "HIGH", "2026-02-15"),
            new Task(2L, "Assignment", "Submit API project", false, "MEDIUM", "2026-02-10"),
            new Task(3L, "Workout", "Morning run", true, "LOW", "2026-02-09")
    ));

    @GetMapping
    public List<Task> getAll() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return tasks.stream()
                .filter(t -> t.getTaskId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/status")
    public List<Task> byStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) result.add(t);
        }
        return result;
    }

    @GetMapping("/priority/{priority}")
    public List<Task> byPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) result.add(t);
        }
        return result;
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updated) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(id)) {
                t.setTitle(updated.getTitle());
                t.setDescription(updated.getDescription());
                t.setPriority(updated.getPriority());
                t.setDueDate(updated.getDueDate());
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/{id}/complete")
    public void complete(@PathVariable Long id) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(id)) {
                t.setCompleted(true);
            }
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tasks.removeIf(t -> t.getTaskId().equals(id));
    }


}
