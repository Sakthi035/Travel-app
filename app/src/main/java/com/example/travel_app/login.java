package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    View loginBtn;
    private LottieAnimationView animationView;
    private TextView gotoSignUp;
    private TextInputLayout TextInputLayout;
    private TextInputEditText Password,userEmail;
    private LinearLayout Logging;
    private Handler handler;
    private Runnable runnable;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), NavActivity.class);
            startActivity(i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.loginBtn);
        userEmail = findViewById(R.id.userEmail);
        Password = findViewById(R.id.password);
        gotoSignUp = findViewById(R.id.lsignUp);
        TextInputLayout = findViewById(R.id.passwordLayout);
//        animationView = findViewById(R.id.animation_view);
        Logging = findViewById(R.id.loginPg);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password,name;
                email = String.valueOf(userEmail.getText());
                password = String.valueOf(Password.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Enter Email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            LoginButton loginButton = new LoginButton(login.this, loginBtn);
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    loginButton.buttonActivated();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            loginButton.buttonFinishedCorrect();
                                            Handler handler1 = new Handler();
                                            handler1.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    loginButton.buttonNormal();
                                                    Toast.makeText(login.this, "LOGGING SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(login.this, NavActivity.class);
                                                    startActivity(i);
//                                                    Handler handler3 = new Handler();
//                                                    handler3.postDelayed(new Runnable() {
//                                                        @Override
//                                                        public void run() {
//                                                            animationView.setVisibility(View.VISIBLE);
//                                                            Logging.setVisibility(View.GONE);
//                                                            animationView.playAnimation();
//
//
//                                                        }
//                                                    },3000);
                                                }
                                            }, 1000);

                                            }
                                        }, 3000);
                                } else {
                                    loginButton.buttonFinishedWrong();
                                    Handler handler2 = new Handler();
                                    handler2.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            loginButton.buttonNormal();
                                            Toast.makeText(login.this, "Authentication failed 'USER NAME OR PASSWORD INCORRECT'",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    },1000);
                                }
                            }
                        });
            }
        });


        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Sign_Up.class);
                startActivity(i);

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
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