package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    View ResetBtn;
    private TextView ResetBackBtn;
    private TextInputEditText ResetEmail;
    private FirebaseAuth mAuth;
    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ResetEmail = findViewById(R.id.resetPassEmail);
        ResetBtn = findViewById(R.id.resetBtn);
        ResetBackBtn = findViewById(R.id.resetBackBtn);
        mAuth = FirebaseAuth.getInstance();

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = ResetEmail.getText().toString().trim();
                if(!TextUtils.isEmpty(strEmail)){
                    ResetPassword();
                }else{
                    ResetEmail.setError("Please Enter your E-mail");
                }
            }
        });

        ResetBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotPassword.this, login.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void ResetPassword() {

        ButtonActivities resetButton = new ButtonActivities(ForgotPassword.this,ResetBtn);
        resetButton.buttonActivated();

        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.buttonFinishedCorrect();

                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resetButton.buttonNormalReset();
                                Toast.makeText(ForgotPassword.this, "Reset Password Link has been Send Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ForgotPassword.this, login.class);
                                startActivity(i);
                                finish();
                            }
                        },1000);
                    }
                },3000);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButton.buttonFinishedWrong();
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                resetButton.buttonNormalReset();
                                Toast.makeText(ForgotPassword.this, "Error While Sending link", Toast.LENGTH_SHORT).show();
                            }
                        },1000);

                    }
                },3000);

            }
        });

    }
}