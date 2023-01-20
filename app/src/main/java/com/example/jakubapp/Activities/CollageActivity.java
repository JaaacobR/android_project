package com.example.jakubapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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

        collageFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                Log.d("xxxx", size.x + " ; " + size.y);
                imageDataArrayList.clear();
                imageDataArrayList.add(new ImageData(0,0,size.x/2,size.y));
                imageDataArrayList.add(new ImageData(size.x/2,0, size.x/2, size.y/2));
                imageDataArrayList.add(new ImageData(size.x/2, size.y/2, size.x/2, size.y/2));
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list", imageDataArrayList);
                startActivity(intent);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}