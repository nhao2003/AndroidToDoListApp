package com.example.todo.Models;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public class ToDo {
    private final String id;
    private String title;
    private Date expiryDate;
    private String description;
    private boolean isDone;

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "Todo(title: " + title + ", description: " + description
                + ", isDone: " + isDone + ")\n";
    }

    public ToDo(String title, String description, Date expiryDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.expiryDate = expiryDate;
    }
    public ToDo(String title, String description, Date expiryDate, boolean isDone) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.expiryDate = expiryDate;
        this.isDone = isDone;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
}
