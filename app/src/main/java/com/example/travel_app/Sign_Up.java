package com.example.travel_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_Up extends AppCompatActivity {

    FirebaseAuth mAuth;
    View signupBtn;

//    private Button signupBtn;
    private TextView goToLogin;

    private TextInputLayout PasswordLayout,ConfirmPasswordLayout;

    private TextInputEditText Password,confirmPassword,UserEmail,Name;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), NavActivity.class);
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
        Name = findViewById(R.id.name);
        Password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        goToLogin = findViewById(R.id.sloginBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password,name;
                email = String.valueOf(UserEmail.getText());
                password = String.valueOf(Password.getText());
                name = String.valueOf(Name.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Sign_Up.this, "Enter Email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Sign_Up.this, "Enter Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Sign_Up.this, "Enter Name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

//                                    Toast.makeText(Sign_Up.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
//                                    Intent i = new Intent(getApplicationContext(), NavActivity.class);
//                                    startActivity(i);
                                    //Sign in success, update UI with the signed-in user's information
                                    SignupButton signupButton = new SignupButton(Sign_Up.this, signupBtn);
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
                                                    signupButton.buttonNormal();
                                                    Intent gotologin = new Intent(Sign_Up.this, login.class);
                                                    startActivity(gotologin);
                                                    Toast.makeText(Sign_Up.this, "SIGN UP SUCCESSFULLY",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            },500);
                                        }
                                    },3000);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Sign_Up.this, "Authentication failed.",
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