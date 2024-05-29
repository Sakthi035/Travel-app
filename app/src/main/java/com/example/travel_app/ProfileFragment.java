package com.example.travel_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    Activity context;
    ImageView editProfileBtn;
    CircleImageView profilePic;
    String fullName,email,doB,gender,mobile,state,language;
    MaterialCardView logoutBtn;
    FirebaseAuth authProfile;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    TextView textViewProfileName,textViewProfileEmail,textViewProfileDob,
            textViewProfileGender,textViewProfileMobile,textViewProfileState, textViewProfileWelcome,textViewProfileLanguage;

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
        textViewProfileEmail = context.findViewById(R.id.t_profile_Email);
        textViewProfileLanguage = context.findViewById(R.id.t_profile_Language);
        textViewProfileDob = context.findViewById(R.id.t_profile_dob);
        textViewProfileGender = context.findViewById(R.id.t_profile_gender);
        textViewProfileMobile = context.findViewById(R.id.t_profile_mobile);
        textViewProfileState = context.findViewById(R.id.t_profile_state);
        textViewProfileWelcome = context.findViewById(R.id.text_show_welcome);
        profilePic = context.findViewById(R.id.profile_pic);

        editProfileBtn = context.findViewById(R.id.edit_profile_btn);


        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,UploadProfilePic.class);
                startActivity(i);
            }
        });


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
//    @Override
    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Handle the result from the Activity
            if (data != null) {
                // Retrieve data from the Intent
                String resultData = data.getStringExtra("key");
                // Process the data as needed
            }
        }
    }*/

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
                    email = firebaseUser.getEmail().toString();
                    language = documentSnapshot.getString("Language");
                    mobile = documentSnapshot.getString("Mobile No");
                    fullName = documentSnapshot.getString("Name");
                    doB = documentSnapshot.getString("Date of Birth");
                    gender = documentSnapshot.getString("Gender");
                    state = documentSnapshot.getString("State");

                    textViewProfileWelcome.setText("Welcome  " + fullName + " !");

                    textViewProfileEmail.setText(email);
                    textViewProfileLanguage.setText(language);
                    textViewProfileName.setText(fullName);
                    textViewProfileMobile.setText(mobile);
                    textViewProfileDob.setText(doB);
                    textViewProfileGender.setText(gender);
                    textViewProfileState.setText(state);

                    Uri uri = firebaseUser.getPhotoUrl();
                    //set the Image which is form the DataBase to the Image view
                    Picasso.get().load(uri).into(profilePic);

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
