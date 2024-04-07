package com.example.schoolmanagementsystem;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolmanagementsystem.Models.announcment_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class annoouncment_List extends AppCompatActivity {
    List<announcment_model> modelList=new ArrayList<>();
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db;
    announcment_adapter adapter;

    FloatingActionButton addbtn;

   // ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_annoouncment_list);
        addbtn=findViewById(R.id.add_btn);
        mRecyclerview = findViewById(R.id.recyclerView_list);
        mRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);
        showData();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(annoouncment_List.this,add_announcement.class));
            }
        });

    }

    private void showData() {
       // pd.setTitle("Loading Data...");
        //pd.show();

        db.collection("Announcment")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //pd.dismiss();
                        for(DocumentSnapshot doc: task.getResult()){
                            announcment_model model=new announcment_model(doc.getString("id"),
                                    doc.getString("Title"),
                                    doc.getString("description"));
                            modelList.add(model);

                        }
                        adapter=new announcment_adapter(annoouncment_List.this,modelList);
                        mRecyclerview.setAdapter(adapter);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //pd.dismiss();
                        Toast.makeText(annoouncment_List.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}