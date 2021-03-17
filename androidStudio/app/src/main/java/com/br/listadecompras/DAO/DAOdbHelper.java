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
            "CREATE TABLE Produtos(id  LONG PRIMARY KEY ,descricao TEXT  , valor TEXT , quantidade TEXT);",
            "INSERT INTO Produtos VALUES('3' , 'duzia de banana' , '5' , '1');",
            "INSERT INTO Produtos VALUES('4' , 'alho' , '10' , '1');"
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
    public long InsertProduto(String produto, String valor, String quantidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("produto", produto);
        values.put("valor", valor);
        values.put("quantidade", quantidade);
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
        return db.rawQuery("SELECT * FROM Produtos", null);
    }

    public Cursor SelectByProduto(String produto) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Produtos WHERE produto=?", new String[]{produto});
    }
}
