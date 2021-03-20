package com.br.listadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static  int versao = 1;
    private static String nomeBD = "ExemploDB.db";

    //codigos sql que serão lidos corretamente
    String[] sql = {
            "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);"
    };

    public DBHelper(@Nullable Context context  ) {
        super(context, nomeBD, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //lendo os sql 1 por 1
        for (int i = 0 ; i < sql.length ; i++){
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS Utilizador");
        onCreate(db);
    }

    //funcionalidades do crud
    //==========INSERT
    public long Insert_utilizador(String username , String password){
        //permite fazer alteracoes no bd
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username" , username);
        values.put("password" , password);
        return db.insert("Utilizador" , null , values);
    }

    //==========UPDATE
    public long UpdteUtilizador(String username , String password){
        //permite fazer alteracoes no bd
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("username" , username);
        values.put("password" ,password);
        return db.update("Utilizador", values , "username=?" , new String[]{username});
    }

    //==========DELETE
    public long DeleteUtilizador(String username){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Utilizador" , "username=?" , new String[]{username});

    }

    //==========SELECT
    public Cursor SelectALL_Utilizador(){
        //sera apenas consulta
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Utilizador" , null);
    }

    public Cursor SelectByUsername_Utilizador(String username){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Utilizador WHERE username=?" , new String[]{username});
    }




}
