package com.br.listadecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                Log.d("============ButtonADD:" , " ");

                String descricao = editDescricao.getText().toString();
                String quantidade = editQuantidade.getText().toString();
                String valor = editValor.getText().toString();

                if (!descricao.trim().isEmpty() && !quantidade.trim().isEmpty() && !valor.trim().isEmpty()){
                    long resp = db.InsertProduto(descricao , quantidade , valor);
                    if(resp>0) {
                        Toast.makeText(MainActivity.this, "Produto inserido com sucesso!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Não foi possível inserir o produto!", Toast.LENGTH_LONG).show();
                    }
                }
                listarProdutos();
            }

        });
        //ação de segurar o botao da list
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("==========Click LONGO" , " ");
                //todo continuar o evento do click LONGO!
                return false;
            }
        });

        listarProdutos();

        }





    private void listarProdutos() {
        try {
            Log.d("===========listarProdutos", " =======//======//======");
            Cursor c = db.SelectAllProdutos();
            c.moveToFirst();
            if (c.getCount() > 0) {
                do {
                    String descricao = c.getString(c.getColumnIndex("descricao"));
                    String quantidade = c.getString(c.getColumnIndex("quantidade"));
                    String valor = c.getString(c.getColumnIndex("valor"));
                    lista_Produtos.add(new Produto(descricao, quantidade, valor));
                } while (c.moveToNext());
            } else {
                Log.d("===========ELSE ", "pois o count é menor do que zero ");
            }
            //adaptando dados recuperados para entrar no listview
            ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_Produtos);
            listView.setAdapter(adapter);
        }catch (Exception ex){
            Log.d("==========Listar Produtos ERROR: " , ex.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("==========onResume: " , "Método onResume();");

    }
}
