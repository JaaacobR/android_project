package com.example.jakubapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends ArrayAdapter {


    public TestAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    private ArrayList<String> _list;
    private Context _context;
    private int resource;

}
