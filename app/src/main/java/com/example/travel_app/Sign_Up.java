package com.example.travel_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_Up extends AppCompatActivity {

    private Button signupBtn;
    private TextView goToLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBtn = findViewById(R.id.signUpBtn);

        goToLogin = findViewById(R.id.sloginBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin = new Intent(Sign_Up.this, login.class);
                startActivity(gotologin);
                Toast.makeText(Sign_Up.this, "SIGN UP SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            }
        });

        goToLogin.setClickable(true);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologin = new Intent(Sign_Up.this, login.class);
                startActivity(gotologin);

            }
        });



    }
}