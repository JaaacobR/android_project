package com.example.jakubapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends ArrayAdapter {
    public TestAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list= (ArrayList<File>) objects;
        this._context = context;
        this._resource = resource;
    }
    private ArrayList<File> _list;
    private Context _context;
    private int _resource;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        Uri uri = Uri.fromFile(new File(_list.get(position).getPath()));
        image.setImageURI(uri);
        image.setLayoutParams(new LinearLayout.LayoutParams(400,200));

        ImageView iv1 = (ImageView) convertView.findViewById(R.id.del);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXX","usuniecie");
                _list.get(position).delete();
                _list.remove(position);
                notifyDataSetChanged();
            }
        });
        ImageView iv2 = (ImageView) convertView.findViewById(R.id.edit);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXX","edycja");
            }
        });
        ImageView iv3 = (ImageView) convertView.findViewById(R.id.info);
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXX","info");
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return _list.size();
    }
}