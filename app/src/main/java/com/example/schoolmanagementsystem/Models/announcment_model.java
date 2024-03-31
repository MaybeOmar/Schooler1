package com.example.schoolmanagementsystem.Models;

public class announcment_model {
    String id,title,discription;

    public announcment_model() {

    }

    public announcment_model(String id, String title, String discription) {
        this.id = id;
        this.title = title;
        this.discription = discription;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
