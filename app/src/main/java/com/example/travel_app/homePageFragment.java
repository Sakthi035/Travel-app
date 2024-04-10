package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class homePageFragment extends AppCompatActivity {

    private ImageView manali, redFort, tajMahla, ishaYoga, bigTemple,ladakh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        manali = findViewById(R.id.manali);
        redFort = findViewById(R.id.goa_beach);
        tajMahla = findViewById(R.id.tajMahla);
        ishaYoga = findViewById(R.id.isha_yoga);
        bigTemple = findViewById(R.id.bigTemple);
        ladakh = findViewById(R.id.boat_house);

        manali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, Manali.class);
                startActivity(m);
            }
        });

        redFort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, Goa.class);
                startActivity(m);
            }
        });

        tajMahla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, TajMahal.class);
                startActivity(m);
            }
        });

        ishaYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, IshaYoga.class);
                startActivity(m);
            }
        });

        ladakh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, BoatHouse.class);
                startActivity(m);
            }
        });

        bigTemple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(homePageFragment.this, BigTemple.class);
                startActivity(m);
            }
        });


    }




}