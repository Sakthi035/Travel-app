package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;

    private EditText username, password;


    private TextView gotoSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        gotoSignUp = findViewById(R.id.lsignUp);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String Password = password.getText().toString();

                if (userName.equals("sakthi") && Password.equals("sakthi")) {
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, NavActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "UserName or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(i);

            }
        });

    }
}