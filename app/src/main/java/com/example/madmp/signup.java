package com.example.madmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText email,password;
    Button signUp;
    FirebaseAuth mAuth;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email=findViewById(R.id.emailS);
        password=findViewById(R.id.passwordS);
        signUp=findViewById(R.id.Signup);
        mAuth=FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser(){
        String Email=email.getText().toString();
        String Password=password.getText().toString();

        if(TextUtils.isEmpty(Email)){
            email.setError("Email can't be empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            password.setError("Password can't be empty");
            password.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(signup.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,homeScreenActivity.class));
                    }else {
                        Toast.makeText(signup.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}