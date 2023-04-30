package com.example.madmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button Signin, Login;
    EditText em,pw;
    FirebaseAuth mAuth;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Signin=findViewById(R.id.signin);
        Login=findViewById(R.id.Login);
        em=findViewById(R.id.emailL);
        pw=findViewById(R.id.passwordL);
        mAuth=FirebaseAuth.getInstance();

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), signup.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        String Email=em.getText().toString();
        String Password=pw.getText().toString();

        if(TextUtils.isEmpty(Email)){
            em.setError("Email can't be empty");
            em.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            pw.setError("Password can't be empty");
            pw.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d("String", "hey! here in succesful! ");
                        Toast.makeText(login.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,homeScreenActivity.class));
                    }else{
                        Log.d("String", "hey! here in Unsuccesful! ");

                        Toast.makeText(login.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}