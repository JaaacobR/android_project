package com.example.jakubapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jakubapp.ImageData;
import com.example.jakubapp.R;

import java.util.ArrayList;

public class CollageMakerActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ArrayList<ImageView> imgViewList;
    private int index = 0;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_maker);

        frameLayout = findViewById(R.id.frameLayout);
        imgViewList = new ArrayList<>();

        ArrayList<ImageData> list = (ArrayList<ImageData>) getIntent().getExtras().getSerializable("list");
        for(int i=0; i< list.size(); i = i + 1){

            ImageView iv = new ImageView(CollageMakerActivity.this);
            iv.setX(list.get(i).getX());
            iv.setY(list.get(i).getY());
            iv.setImageResource(R.drawable.baseline_photo_camera_white_36);
            iv.setLayoutParams(new FrameLayout.LayoutParams(list.get(i).getW(),list.get(i).getH()));
            int finalI = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = finalI;
                    AlertDialog.Builder alert = new AlertDialog.Builder(CollageMakerActivity.this);
                    alert.setTitle("Wybirz źródło zdjęcia!");
                    String[] opcje = {"Aparat" , "Galeria"};
                    alert.setItems(opcje, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(opcje[i] == "Aparat"){
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if(intent.resolveActivity(getPackageManager()) != null){
                                    startActivityForResult(intent,200);
                                }
                            }else{
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 100);
                            }
                        }
                    });
                    alert.show();
                }
            });
            imgViewList.add(iv);
            frameLayout.addView(iv);
        }

    }
}