package com.br.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.br.listadecompras.DAO.DAOdbHelper;
import com.br.listadecompras.Model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public TextView txtTotal;
    public EditText editDescricao;
    public EditText editQuantidade;
    public EditText editValor;
    public Button buttonADD;
    List<Produto> lista_Produtos;
    public ListView listView;

    DAOdbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retirando barra superior
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DAOdbHelper(this);
        lista_Produtos = new ArrayList<>();

        //componentes de tela:
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        editQuantidade = (EditText) findViewById(R.id.editQuantidade);
        editValor = (EditText) findViewById(R.id.editValor);
        listView = (ListView) findViewById(R.id.listResultado);
        buttonADD = (Button) findViewById(R.id.buttonADD);


        Log.d("============Start: " , "App iniciado com sucesso!");

        //ação ao apertar o botão
        buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("============ButtonADD:" , "..................");

            }
        });

        try{
            listarProdutos();
        }catch (Exception ex){
          Log.d("==========Error listarProduto(): " , ex.toString());
        };

    }

    private void listarProdutos() {
        Log.d("===========listarProdutos" , " =======//======//======");
        Cursor c = db.SelectAllProdutos();
        c.moveToFirst();
        String cTemp = String.valueOf(c.getCount());
        Log.d("==========c.getCount(): " , cTemp);
        if(c.getCount() > 0 ){
            do{
                String descricao = c.getString(c.getColumnIndex("descricao"));
                String valor = c.getString(c.getColumnIndex("valor"));
                String quantidade = c.getString(c.getColumnIndex("quantidade"));
                lista_Produtos.add(new Produto(descricao , quantidade , valor));
            }while (c.moveToNext());
        }
        //adaptando dados recuperados para entrar no listview
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , lista_Produtos);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("==========onResume: " , "Método onResume();");

    }
}
