package com.example.travel_app;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {
    private FirebaseFirestore Fstore;
    private FirebaseAuth UProfileAuth;

    private EditText editTextUProfileFullName, editTextUProfileEmail, editTextUProfileDoB,
            editTextUProfileMobile, editTextUProfileState;

    private RadioGroup radioGroupUProfileGender;
    private RadioButton radioButtonUProfileGenderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Initialize Firestore and FirebaseAuth
        Fstore = FirebaseFirestore.getInstance();
        UProfileAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Update Profile");

        Toast.makeText(this, "You can Update Now", Toast.LENGTH_SHORT).show();

        editTextUProfileFullName = findViewById(R.id.e_profile_full_name);
        editTextUProfileEmail = findViewById(R.id.e_profile_email);
        editTextUProfileDoB = findViewById(R.id.e_profile_dob);
        editTextUProfileMobile = findViewById(R.id.e_profile_mobile_no);
        editTextUProfileState = findViewById(R.id.e_profile_state);

        radioGroupUProfileGender = findViewById(R.id.radio_group_profile_gender);
        radioGroupUProfileGender.clearCheck();

        Button buttonUProfile = findViewById(R.id.profile_update_btn);
        buttonUProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });
    }

    private void updateUserProfile() {
        String textFullName = editTextUProfileFullName.getText().toString();
        String textEmail = editTextUProfileEmail.getText().toString();
        String textDoB = editTextUProfileDoB.getText().toString();
        String textMobileNo = editTextUProfileMobile.getText().toString();
        String textState = editTextUProfileState.getText().toString();
        String textGender;

        int selectedGenderId = radioGroupUProfileGender.getCheckedRadioButtonId();
        radioButtonUProfileGenderSelected = findViewById(selectedGenderId);

        if (radioButtonUProfileGenderSelected != null) {
            textGender = radioButtonUProfileGenderSelected.getText().toString();
        } else {
            // No gender selected
            Toast.makeText(this, "Please select your Gender", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(textFullName)) {
            editTextUProfileFullName.setError("Full Name is required");
            editTextUProfileFullName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(textEmail)) {
            editTextUProfileEmail.setError("E-mail is required");
            editTextUProfileEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
            editTextUProfileEmail.setError("Valid E-mail is required");
            editTextUProfileEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(textDoB)) {
            editTextUProfileDoB.setError("Date of Birth is required");
            editTextUProfileDoB.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(textMobileNo)) {
            editTextUProfileMobile.setError("Mobile No is required");
            editTextUProfileMobile.requestFocus();
            return;
        } else if (textMobileNo.length() != 10) {
            editTextUProfileMobile.setError("Mobile No. Should be of 10 digits");
            editTextUProfileMobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(textState)) {
            editTextUProfileState.setError("State is required");
            editTextUProfileState.requestFocus();
            return;
        }

        // All input fields are valid, proceed to update profile
        String userID = UProfileAuth.getCurrentUser().getUid();
        DocumentReference documentReference = Fstore.collection("users").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("Name", textFullName);
        user.put("Email", textEmail);
        user.put("Date of Birth", textDoB);
        user.put("Gender", textGender);
        user.put("State", textState);
        user.put("Mobile No", textMobileNo);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(UpdateProfile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
