package com.example.travel_app.user_verification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app.java_classes.ButtonActivities;
import com.example.travel_app.main.NavActivity;
import com.example.travel_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_Up extends AppCompatActivity {

    FirebaseAuth mAuth;
    View signupBtn;

//    private Button signupBtn;
    private TextView goToLogin;

    private TextInputLayout PasswordLayout,ConfirmPasswordLayout;

    private TextInputEditText Password,confirmPassword,UserEmail;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), NavActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        signupBtn = findViewById(R.id.signUpBtn);
        PasswordLayout = findViewById(R.id.passwordLayout);
        ConfirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        UserEmail = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        goToLogin = findViewById(R.id.sloginBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password,confirmPass;
                email = String.valueOf(UserEmail.getText());
                password = String.valueOf(Password.getText());
                confirmPass = String.valueOf(confirmPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Sign_Up.this, "Please Enter Your Email",
                            Toast.LENGTH_SHORT).show();
                    UserEmail.setError("Email is required");
                    UserEmail.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Sign_Up.this, "Please Enter Your Password",
                            Toast.LENGTH_SHORT).show();
                    Password.setError("Password is required");
                    Password.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(confirmPass)){
                    Toast.makeText(Sign_Up.this, "Please Enter Your Confirm Password",
                            Toast.LENGTH_SHORT).show();
                    Password.setError(" Confirm Password is required");
                    Password.requestFocus();
                    return;
                }

                if(!password.equals(confirmPass)){
                    Toast.makeText(Sign_Up.this, "Please re-enter your Passwords",
                            Toast.LENGTH_SHORT).show();
                    Password.setError(" Password Doesn't Match");
                    Password.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Sign in success, update UI with the signed-in user's information
                                    ButtonActivities signupButton = new ButtonActivities(Sign_Up.this, signupBtn);
                                    signupButton.buttonActivated();

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            signupButton.buttonFinishedCorrect();
                                            Handler handler2 = new Handler();
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    signupButton.buttonNormalSignUp();
                                                    Intent gotologin = new Intent(Sign_Up.this, login.class);
                                                    startActivity(gotologin);
                                                    Toast.makeText(Sign_Up.this, "SIGN UP SUCCESSFULLY",
                                                            Toast.LENGTH_SHORT).show();
                                                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                                }
                                            },500);
                                        }
                                    },3000);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Sign_Up.this, "The Email is Already Registered",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String password = s.toString();
                if(password.length() >= 8)
                {
                    Pattern p = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher m = p.matcher(password);
                    boolean passwordMatch = m.find();
                    if(passwordMatch){
                        PasswordLayout.setHelperText("Your Password is Strong");
                        PasswordLayout.setError("");
                    }else{
                        PasswordLayout.setError("Mix of Letters(Upper and Lower case), Numbers and symbols");
                    }
                }else{
                    PasswordLayout.setHelperText("Password Must Contain 8 Characters");
                    PasswordLayout.setError("");
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String password = Password.getText().toString();
                String confirmPassword = s.toString();
                if(password.equals(confirmPassword)){
                    ConfirmPasswordLayout.setHelperText("Password Matched");
                    ConfirmPasswordLayout.setError("");
                }else{
                    ConfirmPasswordLayout.setError("Password Dosen't Matched");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}