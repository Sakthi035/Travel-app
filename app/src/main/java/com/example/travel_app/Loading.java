package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Loading extends AppCompatActivity {

//    private Handler handler;
//
//    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

//        runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i = new Intent(Loading.this, NavActivity.class);
//                startActivity(i);
//                finish();
//            }
//        };

//        handler.postDelayed(runnable,5000);
    }

//    protected void onDestroy(){
//        super.onDestroy();
//        handler.removeCallbacks(runnable);
//    }
}