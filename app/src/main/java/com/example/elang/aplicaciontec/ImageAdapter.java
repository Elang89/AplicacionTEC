package com.example.elang.aplicaciontec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Carrera>  items;
    LayoutInflater mInflater;

    public ImageAdapter(Context context, ArrayList<Carrera> items) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int i) {

        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        long id;
        Carrera carrera = items.get(i);
        id = Long.parseLong(carrera.idCarrera);
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if(v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Carrera carrera = (Carrera) getItem(i);

        picture.setImageResource(R.mipmap.placeholder1);
        name.setText(carrera.Nombre);

        return v;
    }
}
