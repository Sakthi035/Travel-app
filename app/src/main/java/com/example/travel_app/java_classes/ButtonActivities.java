package com.example.travel_app.java_classes;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.travel_app.R;

public class ButtonActivities {
    private CardView cardView;
    private ProgressBar progressBar;
    private TextView textView;
    private ConstraintLayout layout;

    Animation fad_in;

    public ButtonActivities(Context ct, View view) {

        cardView = view.findViewById(R.id.loginCardView);
        layout = view.findViewById(R.id.loginLayout);
        progressBar = view.findViewById(R.id.logInProgressBar);
        textView = view.findViewById(R.id.loginTextView);



    }

    public void buttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("PLEASE WAIT...");
    }

    public void buttonFinishedCorrect(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.green));
        progressBar.setVisibility(View.GONE);
        textView.setText("DONE");
    }

    public void buttonFinishedWrong(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.red));
        progressBar.setVisibility(View.GONE);
        textView.setText("FAILED");
    }


    public void buttonNormalUpload(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("UPLOAD");

    }

    public void buttonNormalSignUp(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("SIGN UP");

    }

    public void buttonNormalLogin(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.lavender));
        progressBar.setVisibility(View.GONE);
        textView.setText("LOGIN");

    }
}
