package com.br.listadecompras.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DAOdbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String nomeBD = "meuBD.bd";

    //guardando todos os meus cod sql
    String[] sql = {
            "CREATE TABLE Produtos(id  LONG PRIMARY KEY ,descricao TEXT  , quantidade TEXT, valor TEXT );"
    };

    //minha classe que manipula todos acesso ao meu DB
    public DAOdbHelper(@Nullable Context context) {
        super(context, nomeBD, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        version++;
        db.execSQL("DROP TABLE IF EXISTS Produtos");
        onCreate(db);
    }


    //função insert
    public long InsertProduto(String descricao, String quantidade, String valor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", descricao);
        values.put("quantidade", quantidade);
        values.put("valor", valor);
        //se der certo ele me retorna a linha se der errado ele retorna -1
        return db.insert("Produtos", null, values);
    }

    //todo função update
    public long UpdateProduto(String produto, String valor, String quantidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("produto", produto);
        //values.put("valor" , valor);
        //values.put("quantidade" , quantidade);
        return db.update("Produtos", values, "produto=?", new String[]{String.valueOf(produto)});
    }

    //função delete
    public long DeleteProduto(String produto) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Produtos", "produto=?", new String[]{produto});
    }

    //função select
    public Cursor SelectAllProdutos() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Produtos ORDER BY descricao ASC", null);
    }

    //todo delet
    public Cursor DeletAllProdutos() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("DELETE FROM Produtos", null);
    }

    public Cursor SelectByProduto(String produto) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Produtos WHERE produto=?", new String[]{produto});
    }
}
