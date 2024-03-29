package com.example.schoolmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {
    private Spinner educationYearSp, semesterSp;
    private Spinner courseTeacherSp,courseTitleSP;
    private EditText courseBatchET;
    private DatabaseReference teacherListRef,courseRef,courseTitleRef;
    private List<String> teacherList,batchList,teacherIDList,CourseTitleList,courseCodeList;
    private String intentedDep,intentedShift;
    private Button addCourseBtn;
    private String selected_batch,selected_teacher,selected_teacherID,selected_course_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        educationYearSp=findViewById(R.id.educationYearSp);
        courseTeacherSp=findViewById(R.id.courseTeacherSp);
        courseTitleSP=findViewById(R.id.courseTitleSp);
        courseBatchET=findViewById(R.id.courseBatch);
        addCourseBtn=findViewById(R.id.addCourseBtn);

        Intent intent=getIntent();
        intentedDep=intent.getStringExtra("CDEPT");
        intentedShift=intent.getStringExtra("CSHIFT");
        //SweetToast.success(getApplicationContext(),intentedShift);
        teacherList=new ArrayList<>();
        batchList=new ArrayList<>();
        teacherIDList=new ArrayList<>();
        CourseTitleList=new ArrayList<>();
        courseCodeList=new ArrayList<>();

        courseRef= FirebaseDatabase.getInstance().getReference().child("Department").child(intentedDep).child("Course").child(intentedShift);
        courseTitleRef=FirebaseDatabase.getInstance().getReference().child("Department").child(intentedDep).child("Courselist");
        courseTitleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CourseTitleList.clear();
                CourseTitleList.add(0,"Select course");
                courseCodeList.add(0,"Course Code");
                if (dataSnapshot.exists()){


                    for (DataSnapshot ds1:dataSnapshot.getChildren()){
                        String key=ds1.getKey();
                        String key1=ds1.getValue().toString();
                        CourseTitleList.add(key);
                        courseCodeList.add(key1);
                    }

                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(AddCourseActivity.this,android.R.layout.simple_list_item_1,CourseTitleList);

                    courseTitleSP.setAdapter(arrayAdapter);
                    courseTitleSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selected_course_title=parent.getItemAtPosition(position).toString();
                            courseCodeET.setText(courseCodeList.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}