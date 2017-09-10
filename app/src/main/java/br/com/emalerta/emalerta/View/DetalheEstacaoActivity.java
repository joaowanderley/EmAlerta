package br.com.emalerta.emalerta.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.emalerta.emalerta.R;

public class DetalheEstacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_estacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Detalhes da estação"); // Titulo para ser exibido
        // Fim implementação botão voltar

        //associa os campos criados na activity
        TextView tvEstacaoDetalhe = (TextView)findViewById(R.id.txtEstacaoDetalhe);
        TextView tvMunicipioDetalhe = (TextView)findViewById(R.id.txtMunicipioDetalhe);
        TextView tvRioDetalhe = (TextView)findViewById(R.id.txtRioDetalhe);
        ImageView tvImagem = (ImageView) findViewById(R.id.imgEstacaoDetalhe);


        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        tvEstacaoDetalhe.setText(valores.getStringExtra("nomeestacao"));
        tvMunicipioDetalhe.setText(valores.getStringExtra("municipio"));
        tvRioDetalhe.setText(valores.getStringExtra("rio"));
        tvImagem.setImageResource(Integer.parseInt(valores.getStringExtra("img")));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando. como está, deve funcionar
                startActivity(new Intent(this, EstacaoActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

}
