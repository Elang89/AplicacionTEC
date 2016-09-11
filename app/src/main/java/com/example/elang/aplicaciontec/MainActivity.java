package com.example.elang.aplicaciontec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    /* ---------------IMPORTANTE-------------------------------
        CAMBIE LA LINEA DE ABAJO A LA IP DE SU COMPU O SINO SU TELEFONO NO SE VA A CONECTAR AL WEB SERVICE
     */
    private String url = "http://192.168.2.137/AplicacionTEC/carrera.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                else {
                    Carrera carrera[] = gson.fromJson(response.body().charStream(),Carrera[].class);
                    Runnable runnable = createRunnable(carrera);
                    runOnUiThread(runnable);
                }
            }
        });
    }

    private Runnable createRunnable(final Carrera carrera[]){

        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(getApplicationContext(), SectionMenuActivity.class);
                long carreraId;
                int i;
                Carrera iterator;
                GridView gridView = (GridView) findViewById(R.id.career_grid);
                ArrayList<String> names = new ArrayList<>();
                ArrayList<Long> ids = new ArrayList<>();
                ArrayList<String> schools = new ArrayList<>();
                ArrayList<String> images = new ArrayList<>();

                for(i = 0; i < carrera.length; i++)
                {
                    iterator = carrera[i];
                    carreraId = Long.parseLong(iterator.idCarrera);
                    names.add(iterator.Nombre);
                    images.add(iterator.Image);
                    ids.add(carreraId);
                    schools.add(iterator.Escuela);

                }

                final ImageAdapter adapter = new ImageAdapter(getApplicationContext(),
                        names, ids, schools, images);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                            long id) {
                        final String name = adapter.getName(position);
                        intent.putExtra("CARRERA_NAME",name);
                        intent.putExtra("CARRERA_ID",id);
                        startActivity(intent);
                    }
                });
            }
        };
        return aRunnable;
    }
}
