package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
//    private LottieAnimationView animationView;

    private Button loginBtn;

    private EditText username, password;


    private TextView gotoSignUp;

//    private LinearLayout Logging;

//    private Handler handler;

//    private Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        gotoSignUp = findViewById(R.id.lsignUp);

//        animationView = findViewById(R.id.animation_view);

//        Logging = findViewById(R.id.loginPg);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String Password = password.getText().toString();

                if (userName.equals("sakthi") && Password.equals("sakthi")) {

                    Toast.makeText(login.this, "LOGGING SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, NavActivity.class);
                    startActivity(i);
//                    animationView.setVisibility(View.VISIBLE);
//                    Logging.setVisibility(View.GONE);
//                    animationView.playAnimation();

//                    Thread td = new Thread(){
//
//                        public void run(){
//                            try{
//                                sleep(5000);
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        }
//                    };td.start();

                }
                else{
                    Toast.makeText(login.this, "UserName or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Sign_Up.class);
                startActivity(i);

            }
        });

    }
//    protected void onDestroy(){
//        super.onDestroy();
//        handler.removeCallbacks(runnable);
//    }


}