package com.example.jakubapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jakubapp.ImageData;
import com.example.jakubapp.R;

import java.util.ArrayList;

public class CollageActivity extends AppCompatActivity {

    private ArrayList<ImageData> imageDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage);

    }
}