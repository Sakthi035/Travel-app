package com.example.travel_app.home_page_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.travel_app.main.NavActivity;
import com.example.travel_app.R;

import java.util.ArrayList;

public class BigTemple extends AppCompatActivity {

    private ImageSlider imageSlider;
    private TextView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manali);
        backbtn = findViewById(R.id.backBtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BigTemple.this, NavActivity.class);
                startActivity(i);
            }
        });

        imageSlider = findViewById(R.id.image_slider_manali);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.big_temple_slide1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.big_temple_slide2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.big_temple_slide3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.big_temple_slide4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

    }
}