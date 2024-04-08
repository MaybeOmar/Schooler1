package com.example.schoolmanagementsystem.Models;

public class announcment_model {
    String id,title,Description;

    public announcment_model() {

    }

    public announcment_model(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDiscription(String discription) {
        this.Description = discription;
    }
}