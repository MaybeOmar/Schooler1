package com.example.schoolmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registeration extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    CheckBox isTeacherbox,isStudentbox,isAdminbox,isParentbox;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        fAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        isTeacherbox= findViewById(R.id.isTeacher);
        isAdminbox=findViewById(R.id.IsAdmin);
        isParentbox=findViewById(R.id.Isparent);
        isStudentbox=findViewById(R.id.isStudent);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_page.class));
                finish();
            }
        });

        isStudentbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isStudentbox.isChecked()){
                    isAdminbox.setChecked(false);
                    isParentbox.setChecked(false);
                    isTeacherbox.setChecked(false);
                }
            }
        });
        isAdminbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isAdminbox.isChecked()){
                    isStudentbox.setChecked(false);
                    isParentbox.setChecked(false);
                    isTeacherbox.setChecked(false);
                }
            }
        });
        isParentbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isParentbox.isChecked()){
                    isAdminbox.setChecked(false);
                    isStudentbox.setChecked(false);
                    isTeacherbox.setChecked(false);
                }
            }
        });
        isTeacherbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isTeacherbox.isChecked()){
                    isAdminbox.setChecked(false);
                    isParentbox.setChecked(false);
                    isStudentbox.setChecked(false);
                }
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);


                if(!(isTeacherbox.isChecked()||isAdminbox.isChecked()||isParentbox.isChecked()||isStudentbox.isChecked())){

                    Toast.makeText(Registeration.this,"select the Account type",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(valid){
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(Registeration.this,"Account created", Toast.LENGTH_SHORT).show();
                            DocumentReference df =fstore.collection("User").document(user.getUid());
                            Map<String,Object> userInfo= new HashMap<>();

                            userInfo.put("FullName",fullName.getText().toString());
                            userInfo.put("Email",email.getText().toString().trim());
                            userInfo.put("Password",password.getText().toString());
                            userInfo.put("Phone number",phone.getText().toString().trim());
                            //userInfo.put("isuser","1");
                            if(isAdminbox.isChecked()){
                                userInfo.put("isAdmin","1");
                            }
                            if(isParentbox.isChecked()){
                                userInfo.put("isParent","1");
                            }
                            if(isTeacherbox.isChecked()){
                                userInfo.put("isTeacher","1");
                            }
                            if(isStudentbox.isChecked()){
                                userInfo.put("isStudent","1");
                            }
                            df.set(userInfo);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registeration.this,"Faild to creat account", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }


}