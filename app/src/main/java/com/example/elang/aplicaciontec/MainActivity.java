package com.example.elang.aplicaciontec;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

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
    static class Carrera{
        String idCarrera;
        String Nombre;
        String Escuela;
    }
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
                    Runnable myRunnable = createRunnable(carrera);
                    runOnUiThread(myRunnable);
                }
            }
        });
    }

    private Runnable createRunnable(final Carrera carrera[]){

        Runnable aRunnable = new Runnable(){
            public void run(){
                Carrera iterator;
                LinearLayout layout = (LinearLayout) findViewById(R.id.main_page);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 2, 0, 0);
                for(int j = 0; j < carrera.length; j++)
                {
                    iterator = carrera[j];
                    Button button = new Button(getApplicationContext());
                    button.setId(Integer.parseInt(iterator.idCarrera));
                    button.setText(iterator.Nombre);
                    layout.addView(button);
                }
            }
        };

        return aRunnable;
    }
}
