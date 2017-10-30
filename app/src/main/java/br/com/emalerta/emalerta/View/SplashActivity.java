package br.com.emalerta.emalerta.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.emalerta.emalerta.View.MainActivity;
import br.com.emalerta.emalerta.R;

//Classe criada para apresentação da logo do aplicativo

public class SplashActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}