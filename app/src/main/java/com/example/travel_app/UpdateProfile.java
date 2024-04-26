package com.example.travel_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.L;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateProfile extends AppCompatActivity {

    private FirebaseFirestore FStore;
    private FirebaseAuth UPauth;
    private FirebaseUser firebaseUser;

    private EditText editTextUProfileFullName, editTextUProfileLanguage, editTextUProfileDoB,
            editTextUProfileMobile, editTextUProfileState;

    private RadioGroup radioGroupUProfileGender;
    private RadioButton radioButtonUProfileGenderSelected;

    String FullName, Language, Dob, Mobile, State, Gender, userID;

    MaterialCardView updateProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        FStore = FirebaseFirestore.getInstance();
        UPauth = FirebaseAuth.getInstance();

        editTextUProfileFullName = findViewById(R.id.e_profile_full_name);
        editTextUProfileLanguage = findViewById(R.id.e_profile_language);
        editTextUProfileDoB = findViewById(R.id.e_profile_dob);
        editTextUProfileMobile = findViewById(R.id.e_profile_mobile_no);
        editTextUProfileState = findViewById(R.id.e_profile_state);
        radioGroupUProfileGender = findViewById(R.id.radio_group_profile_gender);

        updateProfileBtn = findViewById(R.id.profile_update_btn);

        UPauth = FirebaseAuth.getInstance();
        firebaseUser = UPauth.getCurrentUser();
        FStore = FirebaseFirestore.getInstance();

        if(firebaseUser == null){
            Intent i = new Intent(this, login.class);
            startActivity(i);
        }else{
            showUserProfile(firebaseUser);
        }


        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    updateProfile();
                }
            }
        });
    }
    /*@Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        // Add any data you want to pass back to the Fragment
        resultIntent.putExtra("key", value);
        setResult(Activity.RESULT_OK, resultIntent);
        super.onBackPressed();
    }*/


    private boolean validateInputs() {
        FullName = editTextUProfileFullName.getText().toString().trim();
        Language = editTextUProfileLanguage.getText().toString().trim();
        Dob = editTextUProfileDoB.getText().toString().trim();
        Mobile = editTextUProfileMobile.getText().toString().trim();
        State = editTextUProfileState.getText().toString().trim();

        if (TextUtils.isEmpty(FullName) || TextUtils.isEmpty(Language) || TextUtils.isEmpty(Dob) ||
                TextUtils.isEmpty(Mobile) || TextUtils.isEmpty(State)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.PHONE.matcher(Mobile).matches()) {
            editTextUProfileMobile.setError("Invalid mobile number");
            return false;
        }

        return true;
    }

    private void updateProfile() {
        int selectedGenderId = radioGroupUProfileGender.getCheckedRadioButtonId();
        radioButtonUProfileGenderSelected = findViewById(selectedGenderId);

        Gender = radioButtonUProfileGenderSelected.getText().toString();
        userID = UPauth.getCurrentUser().getUid();

        DocumentReference documentReference = FStore.collection("Registered Users").document(userID);
        Map<String, Object> users = new HashMap<>();
        users.put("Name", FullName);
        users.put("Language", Language);
        users.put("Mobile No", Mobile);
        users.put("Gender", Gender);
        users.put("Date of Birth", Dob);
        users.put("State", State);

        documentReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(UpdateProfile.this, "Profile updated successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        // Assuming you have already initialized Firebase in your app

// Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Construct a query to fetch user data
        DocumentReference userDocRef = db.collection("Registered Users").document(userID);
// Retrieve data asynchronously
        userDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // User data found, retrieve it
                    String language, fullName, mobile,doB,gender,state;
                    language = documentSnapshot.getString("Language");
                    mobile = documentSnapshot.getString("Mobile No");
                    fullName = documentSnapshot.getString("Name");
                    doB = documentSnapshot.getString("Date of Birth");
                    gender = documentSnapshot.getString("Gender");
                    state = documentSnapshot.getString("State");

                    editTextUProfileLanguage.setText(language);
                    editTextUProfileFullName.setText(fullName);
                    editTextUProfileMobile.setText(mobile);
                    editTextUProfileDoB.setText(doB);
                    editTextUProfileState.setText(state);

                    // Do something with the user data
                } else {
                    // User data not found
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle any errors
            }
        });


    }
}
