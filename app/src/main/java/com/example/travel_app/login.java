package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    View loginBtn;
    private LottieAnimationView animationView;

//    private Button loginBtn;

    private EditText username;


    private TextView gotoSignUp;

    private TextInputLayout TextInputLayout;

    private TextInputEditText password;

    private LinearLayout Logging;

    private Handler handler;

    private Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        gotoSignUp = findViewById(R.id.lsignUp);

//        TextInputLayout = findViewById(R.id.passwordLayout);



//        animationView = findViewById(R.id.animation_view);

        Logging = findViewById(R.id.loginPg);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginButton loginButton = new LoginButton(login.this, loginBtn);

                loginButton.buttonActivated();
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        String userName = username.getText().toString();
                        String Password = password.getText().toString();

                        if (userName.equals("sakthi") && Password.equals("sakthi")) {
                            loginButton.buttonFinishedCorrect();
                            Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginButton.buttonNormal();
                                    Toast.makeText(login.this, "LOGGING SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(login.this, NavActivity.class);
                                    startActivity(i);

//                                  animationView.setVisibility(View.VISIBLE);
//                                  Logging.setVisibility(View.GONE);
//                                  animationView.playAnimation();
//
//                                  Thread td = new Thread(){
//                                      public void run(){
//                                          try{
//                                              sleep(2000);
//                                          }catch (Exception e){
//                                              e.printStackTrace();
//                                          }
//                                      }
//                                  };td.start();
//
                                }
                            }, 1000);
                        } else {
                            loginButton.buttonFinishedWrong();
                            Toast.makeText(login.this, "UserName or Password Incorrect", Toast.LENGTH_SHORT).show();
                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginButton.buttonNormal();
                                }
                            },1000);
                        }
                    }
                }, 3000);
            }
        });


        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Sign_Up.class);
                startActivity(i);

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


}