package com.br.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retirando barra superior
        Objects.requireNonNull(getSupportActionBar()).hide();

        Log.d("============Start: " , "App iniciado com sucesso!");



    }
}
