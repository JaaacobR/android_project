package com.example.jakubapp.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.jakubapp.R;
import com.example.jakubapp.adapters.NotesArrayAdapter;
import com.example.jakubapp.classes.DatabaseManager;
import com.example.jakubapp.classes.Note;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) actionBar.setDisplayHomeAsUpEnabled(true);
        ListView listView = findViewById(R.id.notesList);
        DatabaseManager db = new DatabaseManager (
                NotesActivity.this, // activity z galerią zdjęć
                "NotesRyszkaJakub.db", // nazwa bazy
                null,
                3 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
        );
        ArrayList<Note> list =  db.getAll();
        NotesArrayAdapter adapter = new NotesArrayAdapter (
                NotesActivity.this,
                R.layout.note_list_view_element,
                list
        );
        listView.setAdapter(adapter);




    }
}