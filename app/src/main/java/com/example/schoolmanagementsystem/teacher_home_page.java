package com.example.schoolmanagementsystem;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.schoolmanagementsystem.Models.Model;
import com.example.schoolmanagementsystem.adapters.Adapter;


public class teacher_home_page extends AppCompatActivity {
    private List<Model> modelList;
    RecyclerView RV;
    Adapter adapter;
    Intent intentprofile,intentsettings;

    Button profile_btn,settings_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        modelList = new ArrayList<>();
        modelList.add(new Model(R.drawable.subjectwork1, SubjectWorkActivity.class,"Class Work"));
        modelList.add(new Model(R.drawable.generateexam, student_home_page.class,"Generate Exam"));
        modelList.add(new Model(R.drawable.announcement, login_page.class,"Announcements"));





        //recyclerView
        RV = findViewById(R.id.id_recyclerview);
        RV.setLayoutManager(new GridLayoutManager(this,2));


        adapter = new Adapter(modelList);
        RV.setAdapter(adapter);

        //settings button
        settings_btn=findViewById(R.id.settings_button);
        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentsettings = new Intent(teacher_home_page.this,settings.class);
                startActivity(intentsettings);
            }
        });




        //profile button

        profile_btn=findViewById(R.id.profile_button);
       profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentprofile = new Intent(teacher_home_page.this,profile.class);
                startActivity(intentprofile);
            }
        });




    }


}