package com.br.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoUtilizadorActivity extends AppCompatActivity {

    EditText edt_username , edt_password;
    Button bt_gravar , bt_cancelar;
    Intent i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_utilizador);

        Log.d("===========Novo Utilizador" , " ");

        edt_username = findViewById(R.id.edt_NovoUtilizador_username);
        edt_password = findViewById(R.id.edt_NovoUtilizador_password);
        bt_cancelar = findViewById(R.id.bt_Novo_Utilizador_cancelar);
        bt_gravar = findViewById(R.id.bt_gravar);

        i = getIntent();
        //necessário passar o context
        db = new DBHelper(this);

        //evento do botão cancelar
        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0 , i);
                finish();
            }
        });

        //evento do botão gravar
        bt_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pegando os valores dos campos
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();

                if(!username.trim().isEmpty() && !password.trim().isEmpty()){
                    long res = db.Insert_utilizador(username , password);
                    if(res>0) {
                        Toast.makeText(NovoUtilizadorActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                        //dando certo ele retorna pra activity main
                        setResult(1 , i);
                        finish();
                    }
                    else {
                        Toast.makeText(NovoUtilizadorActivity.this, "Erro ao tentar gravar!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
