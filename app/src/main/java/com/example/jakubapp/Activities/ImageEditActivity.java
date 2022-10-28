package com.example.jakubapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jakubapp.R;
import com.example.jakubapp.TestAdapter;

import java.util.ArrayList;

public class ImageEditActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");


        TestAdapter adapter = new TestAdapter(
                ImageEditActivity.this,
                R.layout.listviewedit,
                list
        );

    }
}