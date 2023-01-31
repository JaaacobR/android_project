package com.example.jakubapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jakubapp.ImageData;
import com.example.jakubapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CollageMakerActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ImageView clickedImageView;
    private ArrayList<ImageView> imgViewList;
    private int index = 0;
    protected ImageView flip;
    protected ImageView rotate;
    protected ImageView done;

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
        flip = findViewById(R.id.flip);
        rotate = findViewById(R.id.rotate);
        done = findViewById(R.id.done);
        imgViewList = new ArrayList<>();

        frameLayout.setDrawingCacheEnabled(true);

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
                    clickedImageView = iv;
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

        rotate.setOnClickListener(v->{
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap oryginal = ((BitmapDrawable) clickedImageView.getDrawable()).getBitmap();


            Bitmap rotated = Bitmap.createBitmap(oryginal, 0, 0, oryginal.getWidth(), oryginal.getHeight(), matrix, true);

            clickedImageView.setImageBitmap(rotated);
        });

        flip.setOnClickListener(v->{
            if(clickedImageView != null){
                Matrix matrix = new Matrix();
                matrix.postScale(-1.0f, 1.0f);
                Bitmap oryginal = ((BitmapDrawable) clickedImageView.getDrawable()).getBitmap();


                Bitmap rotated = Bitmap.createBitmap(oryginal, 0, 0, oryginal.getWidth(), oryginal.getHeight(), matrix, true);

                clickedImageView.setImageBitmap(rotated);
            }
        });


        done.setOnClickListener(v->{
            Bitmap b = frameLayout.getDrawingCache(true);


            File pic = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES );
            File dir = new File(pic, "RyszkaJakub");
            dir.mkdir();

            File folder = new File(Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES ) + File.separator + "RyszkaJakub");

            File collages = new File(folder, "collages");
            collages.mkdir();




            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, stream); // kompresja, typ pliku jpg, png
            byte[] byteArray = stream.toByteArray();


            FileOutputStream fs = null;
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String d = df.format(new Date());
                fs = new FileOutputStream(Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES ) + File.separator + "RyszkaJakub" + File.separator + "collages"+ File.separator+d+".jpg");
                fs.write(byteArray);
                fs.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir = new File(pic, "RyszkaJakub");
        String[] opcje = new String[dir.listFiles().length];
        for(int i=0; i< dir.listFiles().length; i++){
            opcje[i] = dir.listFiles()[i].getName();
        }

        if(requestCode == 200){
            if(resultCode == RESULT_OK){
                Uri imgData = data.getData();
                try {
                    InputStream stream = getContentResolver().openInputStream(imgData);
                    Bitmap b = BitmapFactory.decodeStream(stream);
                    clickedImageView.setImageBitmap(b);
                    clickedImageView.setPadding(0, 0, 0, 0);
                    clickedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }catch(Exception x){

                }
            }
        }else if(requestCode == 100){
            if(resultCode == RESULT_OK){
                Uri imgData = data.getData();
                try {
                    InputStream stream = getContentResolver().openInputStream(imgData);
                    Bitmap b = BitmapFactory.decodeStream(stream);
                    clickedImageView.setImageBitmap(b);
                    clickedImageView.setPadding(0, 0, 0, 0);
                    clickedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }catch(Exception x){

                }

            }
        }

    }

}