package com.example.travel_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    Activity context;

    Button logoutBtn;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

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
        auth = FirebaseAuth.getInstance();
        textView = context.findViewById(R.id.profileText);
        user = auth.getCurrentUser();

        if(user == null){
            Intent i = new Intent(context, login.class);
            startActivity(i);
        }else{
            textView.setText(user.getEmail());
        }
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(context, login.class);
                startActivity(i);
            }
        });
    }
}