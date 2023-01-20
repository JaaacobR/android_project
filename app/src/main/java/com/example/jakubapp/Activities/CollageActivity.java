package com.example.jakubapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.jakubapp.ImageData;
import com.example.jakubapp.R;

import java.util.ArrayList;

public class CollageActivity extends AppCompatActivity {

    private ArrayList<ImageData> imageDataArrayList = new ArrayList<>();
    private LinearLayout collageFirst;
    private LinearLayout collageSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage);

        collageFirst = findViewById(R.id.collageFirst);
        collageSecond = findViewById(R.id.collageSecond);

        imageDataArrayList.add(new ImageData(0,0,100,100));

    }
}