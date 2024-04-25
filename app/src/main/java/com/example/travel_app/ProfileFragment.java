package com.example.travel_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    Activity context;
    MaterialCardView editProfileBtn;
    String fullName,email,doB,gender,mobile,state;
    MaterialCardView logoutBtn;
    FirebaseAuth authProfile;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    TextView textViewProfileName,textViewProfileEmail,textViewProfileDob,
            textViewProfileGender,textViewProfileMobile,textViewProfileState, textViewProfileWelcome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    public void onStart(){
        super.onStart();
        logoutBtn = context.findViewById(R.id.logOutBtn);
        textViewProfileName = context.findViewById(R.id.t_profile_full_name);
        textViewProfileEmail = context.findViewById(R.id.t_profile_email);
        textViewProfileDob = context.findViewById(R.id.t_profile_dob);
        textViewProfileGender = context.findViewById(R.id.t_profile_gender);
        textViewProfileMobile = context.findViewById(R.id.t_profile_mobile);
        textViewProfileState = context.findViewById(R.id.t_profile_state);
        textViewProfileWelcome = context.findViewById(R.id.text_show_welcome);

        editProfileBtn = context.findViewById(R.id.editProfileBtn);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        if(firebaseUser == null){
            Intent i = new Intent(context, login.class);
            startActivity(i);
        }else{
           showUserProfile(firebaseUser);
        }

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateProfile.class);
                startActivity(i);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(context, login.class);
                startActivity(i);
            }
        });
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        // Assuming you have already initialized Firebase in your app

// Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Construct a query to fetch user data
        DocumentReference userDocRef = db.collection("users").document(userID);
// Retrieve data asynchronously
        userDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // User data found, retrieve it
                    email = documentSnapshot.getString("Email");
                    mobile = documentSnapshot.getString("PhoneNumber");
                    fullName = documentSnapshot.getString("Name");
                    doB = documentSnapshot.getString("DoB");
                    gender = documentSnapshot.getString("Gender");
                    state = documentSnapshot.getString("State");

                    textViewProfileWelcome.setText("Welcome  " + fullName + " !");

                    textViewProfileEmail.setText(email);
                    textViewProfileName.setText(fullName);
                    textViewProfileMobile.setText(mobile);

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
