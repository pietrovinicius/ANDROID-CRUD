package com.br.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dadosUtilizadorActivity extends AppCompatActivity {

    EditText edt_username, edt_password;
    Button bt_modificar, bt_eliminar, bt_cancelar;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_utilizador);

        edt_username = findViewById(R.id.edt_DadosUtilizador_username);
        edt_password = findViewById(R.id.edt_DadosUtilizador_password);

        bt_modificar = findViewById(R.id.bt_DadosUtilizador_modificar);
        bt_cancelar = findViewById(R.id.bt_DadosUtilizador__cancelar);
        bt_eliminar = findViewById(R.id.bt_DadosUtilizador_eliminar);

        //buscando o intent que chegou até aqui
        i = getIntent();
        final String username = i.getExtras().getString("username");
        //passando o contexto
        db = new DBHelper(this);

        //carregar os dados clicados
        carregarDadosUtilizador(username);


        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nao faz nada, entao retorna
                setResult(0, i);
                finish();
                Log.d("===========Dados Utilizador button cancelar", "");
            }
        });

        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("===========Dados Utilizador button alterar", "");
                    if(!edt_username.getText().toString().trim().isEmpty()){
                        long res = db.UpdteUtilizador(username , edt_password.getText().toString());
                        if (res > 0) {
                            Toast.makeText(dadosUtilizadorActivity.this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                            setResult(1, i);
                            finish();
                        } else {
                            Toast.makeText(dadosUtilizadorActivity.this, "Error ao Alterar!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(dadosUtilizadorActivity.this , "Deve inserir um password" , Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Log.d("==========Button Modificar Error: ", ex.toString());
                }


            }
        });

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("===========Dados Utilizador button eliminar", "");
                    long res = db.DeleteUtilizador(username);
                    if (res > 0) {
                        Toast.makeText(dadosUtilizadorActivity.this, "Eliminado com sucesso!", Toast.LENGTH_SHORT).show();
                        setResult(2, i);
                        finish();
                    } else {
                        Toast.makeText(dadosUtilizadorActivity.this, "Error ao eliminar!", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception ex) {
                    Log.d("==========Button Eliminar Error: ", ex.toString());
                }

            }
        });
    }

    private void carregarDadosUtilizador(String username) {
        try {
            Log.d("===========Dados Utilizador", username);
            Cursor c = db.SelectByUsername_Utilizador(username);
            c.moveToFirst();
            if (c.getCount() == 1) {
                String password = c.getString(c.getColumnIndex("password"));
                edt_username.setText(username);
                edt_password.setText(password);
            } else if (c.getCount() == 0) {
                Toast.makeText(this, "Utilizador não existe!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Utilizador não existe!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.d("==========Error carregarDadosUtilizador()", "");
        }

    }
}
