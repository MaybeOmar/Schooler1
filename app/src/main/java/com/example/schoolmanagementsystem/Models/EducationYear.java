package com.example.schoolmanagementsystem.Models;

public class EducationYear {
    private String subject_name;

    private String teacher;

    public EducationYear(){

    }

    public EducationYear(String subject_name, String teacher) {
        this.subject_name = subject_name;
        this.teacher = teacher;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
