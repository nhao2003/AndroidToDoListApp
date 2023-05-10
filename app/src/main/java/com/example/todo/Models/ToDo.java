package com.example.todo.Models;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public class ToDo {
    private final String id;
    private String title;
    private final Date timeCreate;
    private String description;
    private boolean isDone;

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "Todo(title: " + title + ", description: " + description
                +", isDone: " + isDone + ")\n";
    }

    public ToDo(String title, String description){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.timeCreate = new Date();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
}
