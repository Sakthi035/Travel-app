package com.example.travel_app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.travel_app.home_page_activities.BigTemple;
import com.example.travel_app.home_page_activities.BoatHouse;
import com.example.travel_app.home_page_activities.Goa;
import com.example.travel_app.home_page_activities.IshaYoga;
import com.example.travel_app.home_page_activities.Manali;
import com.example.travel_app.R;
import com.example.travel_app.home_page_activities.TajMahal;

public class HomeFragment extends Fragment {

    Activity context;

    private ImageView manali, redFort, tajMahla, ishaYoga, bigTemple,ladakh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    public void onStart() {

        super.onStart();

        manali = context.findViewById(R.id.manali);
        redFort = context.findViewById(R.id.goa_beach);
        tajMahla = context.findViewById(R.id.tajMahla);
        ishaYoga = context.findViewById(R.id.isha_yoga);
        bigTemple = context.findViewById(R.id.bigTemple);
        ladakh = context.findViewById(R.id.boat_house);

        manali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, Manali.class);
                startActivity(m);
            }
        });

        redFort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, Goa.class);
                startActivity(m);
            }
        });

        tajMahla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, TajMahal.class);
                startActivity(m);
            }
        });

        ishaYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, IshaYoga.class);
                startActivity(m);
            }
        });

        ladakh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, BoatHouse.class);
                startActivity(m);
            }
        });

        bigTemple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(context, BigTemple.class);
                startActivity(m);
            }
        });
    }
}