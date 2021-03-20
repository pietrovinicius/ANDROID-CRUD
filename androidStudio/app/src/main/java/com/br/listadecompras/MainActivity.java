package com.br.listadecompras;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_utilizadores;
    List<Utilizador> lista_Utilizadores;
    DBHelper db;
    Button bt_novoUtilizador;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        lista_Utilizadores = new ArrayList<>();
        lv_utilizadores = findViewById(R.id.listView_utilizadores);
        bt_novoUtilizador = findViewById(R.id.bt_novoUtilizador);

    //evento para mudar de activity
        bt_novoUtilizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , NovoUtilizadorActivity.class);
                //vamos pra outra janela mas com o intuito de voltar
                startActivityForResult(i , 1);
            }
        });

        listarUtilizadores();
    }

    private void listarUtilizadores() {
        //limpando a lista em memoria
        lista_Utilizadores.clear();

        Cursor c = db.SelectALL_Utilizador();
        c.moveToFirst();
        if(c.getCount()>0){
            do{
                String username = c.getString(c.getColumnIndex("username"));
                String password = c.getString(c.getColumnIndex("password"));
                lista_Utilizadores.add(new Utilizador(username,password));
                Log.d("===========Utilizadores" , username + password);
            }while (c.moveToNext());
        }
        ArrayAdapter<Utilizador> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , lista_Utilizadores);
        lv_utilizadores.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1){
            //desta forma recarrega a lista
            listarUtilizadores();
        }
    }
}
