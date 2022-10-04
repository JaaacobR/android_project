package com.example.jakubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;

public class AlbumsActivity extends AppCompatActivity {

    private ListView listView;
    private LinearLayout addFolderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File dir = new File(pic, "RyszkaJakub");
        dir.mkdir();
        File dir1 = new File(pic, "RyszkaJakub/ludzie");
        dir1.mkdir();
        dir1 = new File(pic, "RyszkaJakub/rzeczy");
        dir1.mkdir();
        dir1 = new File(pic, "RyszkaJakub/miejsca");
        dir1.mkdir();

        File[] files = dir.listFiles();
        for(File file : dir.listFiles()){
            Log.d("TAG", "Nr klikanego wiersza : " + file.toString());
        }


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listViewID);
        String[] array = new String[dir.listFiles().length];
        for(int i=0; i< dir.listFiles().length; i++){
            array[i] = dir.listFiles()[i].getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AlbumsActivity.this,
                R.layout.list_view_elem,
                R.id.tv1,
                array
        );
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "Nr klikanego wiersza : " + i);
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}