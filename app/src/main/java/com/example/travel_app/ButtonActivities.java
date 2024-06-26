package com.example.travel_app;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ButtonActivities {
    private CardView cardView;
    private ProgressBar progressBar;
    private TextView textView;
    private ConstraintLayout layout;

    Animation fad_in;

    ButtonActivities(Context ct, View view) {

        cardView = view.findViewById(R.id.loginCardView);
        layout = view.findViewById(R.id.loginLayout);
        progressBar = view.findViewById(R.id.logInProgressBar);
        textView = view.findViewById(R.id.loginTextView);



    }

    void buttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("PLEASE WAIT...");
    }

    void buttonFinishedCorrect(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.green));
        progressBar.setVisibility(View.GONE);
        textView.setText("DONE");
    }

    void buttonFinishedWrong(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.red));
        progressBar.setVisibility(View.GONE);
        textView.setText("FAILED");
    }


    void buttonNormalSignUp(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("SIGN UP");

    }

    void buttonNormalLogin(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("LOGIN");

    }
    void buttonNormalUpload(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("UPLOAD");
    }
    void buttonNormalReset(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("RESET");

    }
}
