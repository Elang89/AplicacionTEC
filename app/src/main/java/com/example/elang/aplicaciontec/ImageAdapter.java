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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String>  names;
    private ArrayList<Long> ids;
    private ArrayList<String> schools;
    private ArrayList<String> images;

    LayoutInflater mInflater;

    public ImageAdapter(Context context, ArrayList<String> names, ArrayList<Long> ids,
                        ArrayList<String> schools, ArrayList<String> images) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.schools = schools;
        this.names = names;
        this.ids = ids;
        this.images = images;
    }

    @Override
    public int getCount() {
        return ids.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    public String getName(int i){
        return names.get(i);
    }

    public String getSchools(int i){
        return schools.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ids.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;
        String career_name;
        String image_source;

        if(v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }


        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        career_name = names.get(i);
        image_source = images.get(i);

        Picasso.with(context).load(image_source).error(R.mipmap.placeholder1)
                .into(picture);
        name.setText(career_name);

        return v;
    }
}
