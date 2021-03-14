package com.br.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public TextView txtTotal;
    public EditText editDescricao;
    public EditText editQuantidade;
    public EditText editValor;
    public Button buttonADD;
    public ListView listView;

    private SQLiteDatabase bancoDados;

    //criando meu banco de dados SQL lite
    public void criarBancodeDados(){
        try{
            //to criando ou abrindo meu banco de dados
            Log.d("==========criarBancodeDados: " , "método criar Banco de Dados.");
            bancoDados = openOrCreateDatabase("listaDeCompras", MODE_PRIVATE , null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS lista(" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    " , descricao VARCHAR)");
            bancoDados.close();
        }catch (Exception ex){
            Log.d("==========criarBancodeDados: " , ex.toString());
        }
    }

    public void listarDados() {
        try{
            //listar todos os dados
            Log.d("==========Listar Dados: " , "método listar dados.");
            bancoDados = openOrCreateDatabase("listaDeCompras" , MODE_PRIVATE , null);
            Cursor meuCursor = bancoDados.rawQuery("SELECT id , descricao from lista" , null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter meuAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    linhas
            );
            listView.setAdapter(meuAdapter);
            meuCursor.moveToFirst();
            while (meuCursor!=null){
                linhas.add(meuCursor.getString(1));
                meuCursor.moveToNext();
            }
        }catch (Exception ex){
            Log.d("==========listarDados: " , ex.toString());
        }
    }


    public void inserirDadosTemp(){

        try{
            Log.d("==========inserir Dados Temp: " , "método inserir dados.");
            bancoDados = openOrCreateDatabase("listaDeCompras" , MODE_PRIVATE , null);
            String sql = "INSERT INTO lista (descricao) VALUES (?) ";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);

            stmt.bindString(1 , "descricao 1");
            stmt.executeInsert();
            stmt.bindString(1 , "descricao 2");
            stmt.executeInsert();
            stmt.bindString(1 , "descricao 3");
            stmt.executeInsert();

            bancoDados.close();
        }catch (Exception ex){
            Log.d("==========listarDados: " , ex.toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retirando barra superior
        Objects.requireNonNull(getSupportActionBar()).hide();

        //componentes de tela:
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        editQuantidade = (EditText) findViewById(R.id.editQuantidade);
        editValor = (EditText) findViewById(R.id.editValor);
        listView = (ListView) findViewById(R.id.listResultado);

        Log.d("============Start: " , "App iniciado com sucesso!");

        //criarBancodeDados();
        inserirDadosTemp();
        listarDados();





    }
}
