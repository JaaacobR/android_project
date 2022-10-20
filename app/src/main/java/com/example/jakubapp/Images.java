package com.example.jakubapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

public class Images extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        linearLayout = findViewById(R.id.imageLayout);

        Bundle bundle = getIntent().getExtras();
        String  folder = bundle.getString("folder").toString();
        Log.d("XXX", folder);

        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir = new File(pic, "RyszkaJakub/" + folder);
        Log.d("bbb" , dir.getPath());
        dir.mkdir();

        for(int i=0; i< dir.listFiles().length; i++){
            String imagePath = dir.listFiles()[i].getPath();
            Bitmap bmp = betterImageDecode(imagePath);
            ImageView img = new ImageView(Images.this);
            img.setImageBitmap(bmp);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            linearLayout.addView(img);
        }



//        for(File file : dir.listFiles()){
//            //String imagePath = file.getPath();
//            //Bitmap bmp = betterImageDecode(imagePath);
//            //ImageView img = new ImageView(Images.this);
//            //img.setImageBitmap(bmp);
//            //linearLayout.addView(img);
//        }

    }

    private Bitmap betterImageDecode(String filePath){
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}