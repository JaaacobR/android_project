package com.example.jakubapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends ArrayAdapter {

    private ArrayList<String> _list;
    private Context _context;
    private int _resource;

    public TestAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list = (ArrayList<String>) objects;
        this._context = context;
        this._resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.remove);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXX" , "Klik");
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return _list.size();
    }
}
