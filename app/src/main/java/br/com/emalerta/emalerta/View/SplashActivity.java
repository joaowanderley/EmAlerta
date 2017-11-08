package br.com.emalerta.emalerta.View;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.emalerta.emalerta.View.MainActivity;
import br.com.emalerta.emalerta.R;

//Classe criada para apresentação da logo do aplicativo

public class SplashActivity extends AppCompatActivity implements Runnable {

    SQLiteDatabase db;

    private void criarBanco(){

        //Cria ou abre um banco de dados

        StringBuilder sql2 = new StringBuilder();

        sql2.append("CREATE TABLE IF NOT EXISTS tb_estacao(");
        sql2.append("_id integer primary key autoincrement,");
        sql2.append("cod_estacao varchar(100) NOT NULL,");
        sql2.append("nome varchar(100),");
        sql2.append("municipio varchar(50))");

        try{
            //Executa um comando SQL, neste caso a StringBuilderSQL
            db.execSQL(sql2.toString());
        }catch(Exception ex){
            Toast.makeText(getBaseContext(),"Error = " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(this, 2000);

        db = openOrCreateDatabase("estacao_banco.db", Context.MODE_PRIVATE, null);
        criarBanco();
    }

    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}