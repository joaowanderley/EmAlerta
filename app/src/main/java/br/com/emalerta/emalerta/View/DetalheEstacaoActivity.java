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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.emalerta.emalerta.DAO.EstacaoFavorita;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.Model.EstacaoAdapter;
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
        final TextView tvEstacaoDetalhe = (TextView)findViewById(R.id.txtEstacaoDetalhe);
        TextView tvMunicipioDetalhe = (TextView)findViewById(R.id.txtMunicipioDetalhe);
        TextView tvRioDetalhe = (TextView)findViewById(R.id.txtRioDetalhe);
        ImageView tvImagem = (ImageView) findViewById(R.id.imgEstacaoDetalhe);


        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        tvEstacaoDetalhe.setText(valores.getStringExtra("nomeestacao"));
        final String codigoEstacao = valores.getStringExtra("codestacao");
        tvMunicipioDetalhe.setText(valores.getStringExtra("municipio"));
        tvRioDetalhe.setText(valores.getStringExtra("rio"));
        tvImagem.setImageResource(Integer.parseInt(valores.getStringExtra("img")));


        ImageView ImgNivelRio = (ImageView) findViewById(R.id.imgNivel);
        TextView TextNivelRio = (TextView) findViewById(R.id.txtNiveldoRio);


        ImgNivelRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                Intent telaNivelIMG = new Intent(DetalheEstacaoActivity.this, NivelActivity.class);
                telaNivelIMG.putExtras(bundle);
                startActivity(telaNivelIMG);
                finish();
            }
        });
        TextNivelRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaNivelTXT = new Intent(DetalheEstacaoActivity.this, NivelActivity.class);
                startActivity(telaNivelTXT);
                finish();
            }
        });


        ImageView ImgChuvaRio = (ImageView) findViewById(R.id.imgChuva);
        TextView TextChuvaRio = (TextView) findViewById(R.id.txtVolumedeChuva);


        ImgChuvaRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                Intent telaChuvaIMG = new Intent(DetalheEstacaoActivity.this, ChuvaActivity.class);
                telaChuvaIMG.putExtras(bundle);
                startActivity(telaChuvaIMG);
                finish();
            }
        });
        TextChuvaRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaChuvaTXT = new Intent(DetalheEstacaoActivity.this, ChuvaActivity.class);
                startActivity(telaChuvaTXT);
                finish();
            }
        });



        ImageView ImgGrafico = (ImageView) findViewById(R.id.imgGrafico);
        TextView TextGrafico = (TextView) findViewById(R.id.txtGrafico);

        ImgGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                Intent telaGraficoIMG = new Intent(DetalheEstacaoActivity.this, GraficoActivity.class);
                telaGraficoIMG.putExtras(bundle);
                startActivity(telaGraficoIMG);
                finish();
            }
        });
        TextGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaGraficoTXT = new Intent(DetalheEstacaoActivity.this, GraficoActivity.class);
                startActivity(telaGraficoTXT);
                finish();
            }
        });



    }

    // inicio implementação botão para selecionar estações favoritas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_favorita, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){
            startActivity(new Intent(this, EstacaoActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
            finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
            return true;
        }
        if (id == R.id.favorite){
            // inicio implementação lista de favoritos
            Toast.makeText(getApplicationContext(), "Estação adicionada aos favoritos", Toast.LENGTH_LONG).show();

            return  true;


            // fim implementação lista de favoritos
        }

        return super.onOptionsItemSelected(item);
    }

}
