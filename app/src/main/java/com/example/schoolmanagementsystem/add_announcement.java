package com.example.schoolmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class add_announcement extends AppCompatActivity {


    EditText titleET,descriptionET;
    Button save,view_list;

    ProgressDialog pd;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
        titleET=findViewById(R.id.anns_TitleET);
        descriptionET=findViewById(R.id.anns_DiscriptionET);
        save=findViewById(R.id.anns_savebtn);
        view_list=findViewById(R.id.view_listbtn);


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Add data");

        pd=new ProgressDialog(this);
        db=FirebaseFirestore.getInstance();
view_list.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(add_announcement.this,announcment_list.class));
        finish();
    }
});
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleET.getText().toString().trim();
                String description=descriptionET.getText().toString().trim();
                Uploaddata(title,description);

            }
        });

    }

    private void Uploaddata(String title, String description) {

        pd.setTitle("Adding data to firestore");
        pd.show();
        String id= UUID.randomUUID().toString();
        Map<String, Object> announcement=new HashMap<>();
        announcement.put("id",id);
        announcement.put("Title",title);
        announcement.put("description",description);
        db.collection("Announcment").document(id).set(announcement).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(add_announcement.this,"Uploading...",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(add_announcement.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}