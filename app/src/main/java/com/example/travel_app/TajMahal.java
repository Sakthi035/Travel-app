package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class TajMahal extends AppCompatActivity {

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
                Intent i = new Intent(TajMahal.this,NavActivity.class);
                startActivity(i);
            }
        });

        imageSlider = findViewById(R.id.image_slider_manali);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.tajmahal_slide1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tajmahal_slide2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tajmahal_slide3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tajmahal_slide4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

    }
}