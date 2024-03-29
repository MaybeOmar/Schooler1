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


public class student_home_page extends AppCompatActivity {
    private List<Model> modelList;
    RecyclerView RV;
    Adapter adapter;
    Intent intentprofile,intentsettings;
    Button profile_btn,settings_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        modelList = new ArrayList<>();
        modelList.add(new Model(R.drawable.a_attend, login_page.class,"Attendance"));
        modelList.add(new Model(R.drawable.assignment_kbeer, ViewAssignments.class,"Assignments"));
        modelList.add(new Model(R.drawable.a_grades, student_home_page.class,"Grades"));
        modelList.add(new Model(R.drawable.a_behavioral, login_page.class,"Reports"));
        modelList.add(new Model(R.drawable.a_exit_permits, login_page.class,"Exit Permits"));
        modelList.add(new Model(R.drawable.a_fees, login_page.class,"Tuition Fees"));
        modelList.add(new Model(R.drawable.subjectwork1, ViewSubjectMaterial.class,"Subject Work"));
        modelList.add(new Model(R.drawable.a_callparent, login_page.class,"Call Parent"));
        modelList.add(new Model(R.drawable.a_callparent, login_page.class,"Call Parent"));


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
                intentsettings = new Intent(student_home_page.this,settings.class);
                startActivity(intentsettings);
            }
        });
//profile button

        profile_btn=findViewById(R.id.profile_button);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentprofile = new Intent(student_home_page.this,profile.class);
                startActivity(intentprofile);
            }
        });



    }


}