package com.example.elang.aplicaciontec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class SectionMenuActivity extends AppCompatActivity {

    private TextView careerName;
    private String careerNameText;
    private long careerId;
    private Bundle bundle;
    private GridView gridSections;
    private ImageAdapter adapter;
    private ArrayList<String> names;
    private ArrayList<String> images;
    private ArrayList<Long> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        names = new ArrayList<>();
        ids = new ArrayList<>();
        images = new ArrayList<>();

        bundle = getIntent().getExtras();
        careerName = (TextView) findViewById(R.id.careerTitle);
        careerNameText = bundle.getString("CARRERA_NAME");
        careerId = bundle.getLong("CARRERA_ID");
        careerName.setText(careerNameText);

        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_desc.png");
        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_activities.png");
        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_payment.png");
        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_profile.png");
        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_learning.png");
        images.add("http://192.168.2.137/aplicacionTEC/Icons/ic_student.png");

        names.add("SECCION 1");
        names.add("SECCION 2");
        names.add("SECCION 3");
        names.add("SECCION 4");
        names.add("SECCION 5");
        names.add("SECCION 6");

        for(long i = 1; i <= 6; i++) {
            ids.add(i);
        }

        gridSections = (GridView) findViewById(R.id.gridSections);
        adapter = new ImageAdapter(getApplicationContext(),names, ids, null, images);
        gridSections.setAdapter(adapter);
    }
}
