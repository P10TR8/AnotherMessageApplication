package com.example.ama;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userEmail;
    Button userPass,backButton;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        toolbar = findViewById(R.id.toolbar2);
        userEmail = findViewById(R.id.resetPassEmail);
        userPass = findViewById(R.id.resetPassEmail_btn);
        backButton = findViewById(R.id.backToLogin_btn);



        firebaseAuth = FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PasswordResetActivity.this,
                                    "Password send to your email",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(PasswordResetActivity.this,
                                    task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent phoneLoginIntent = new Intent(PasswordResetActivity.this, LoginActivity.class);
                startActivity(phoneLoginIntent);
            }
        });
    }

}
