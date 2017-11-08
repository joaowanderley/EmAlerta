package br.com.emalerta.emalerta.View;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.com.emalerta.emalerta.DAO.EstacaoDAO;
import br.com.emalerta.emalerta.DAO.EstacaoFavorita;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.R;

public class DetalheEstacaoActivity extends AppCompatActivity {

    SQLiteDatabase db;


    public void favoritarEstacao() {

        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        ImageView imgEstacao = (ImageView) findViewById(R.id.imgEstacaoDetalhe);
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos

        final String codigoEstacao = valores.getStringExtra("codestacao");

        StringBuilder sql = new StringBuilder();

        String codEstacao, nomeEstacao, municipio, latitude, altitude, codRio, nomeRio, status;
        codEstacao = codigoEstacao.toString();
        nomeEstacao = valores.getStringExtra("nomeestacao").toString();
        municipio = valores.getStringExtra("municipio").toString();
        nomeRio = valores.getStringExtra("rio").toString();
        imgEstacao.setImageResource(Integer.parseInt(valores.getStringExtra("img")));

       /* Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
        byte[] img = saida.toByteArray();*/


        sql.append("INSERT INTO tb_estacao(cod_estacao, nome, municipio, rio, img) VALUES (");
        sql.append("'"+ codEstacao +"'");
        sql.append(",");
        sql.append("'"+nomeEstacao+"'");
        sql.append(",");
        sql.append("'"+municipio+"'");
        sql.append(",");
        sql.append("'"+nomeRio+"'");
        sql.append(",");
        sql.append("'"+imgEstacao+"'");
        sql.append(")");

        try {
            db.execSQL(sql.toString());
            Toast.makeText(getBaseContext(), "Estação adicionada aos favoritos!", Toast.LENGTH_LONG).show();
        } catch (SQLException ex){
            Toast.makeText(getBaseContext(), sql.toString()+"Erro = "+ ex.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(),"ESSA ESTAÇÃO JÁ É UMA FAVORITA", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_estacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = openOrCreateDatabase("estacao_banco.db", Context.MODE_PRIVATE, null);
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
        TextView tvAbaixoNormal = (TextView) findViewById(R.id.txtDetalheEstaAbaixonormal);
        TextView tvNormal = (TextView) findViewById(R.id.txtDetalheEstanormal);
        TextView tvAlerta = (TextView) findViewById(R.id.txtDetalheEstaAlerta);
        TextView tvEmergencia = (TextView) findViewById(R.id.txtDetalheEstaEmergencia);

        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        tvEstacaoDetalhe.setText(valores.getStringExtra("nomeestacao"));
        final String codigoEstacao = valores.getStringExtra("codestacao");
        tvMunicipioDetalhe.setText(valores.getStringExtra("municipio"));
        tvRioDetalhe.setText(valores.getStringExtra("rio"));
        tvImagem.setImageResource(Integer.parseInt(valores.getStringExtra("img")));
        tvAbaixoNormal.setText("Abaixo do normal (menos que " + Integer.parseInt(valores.getStringExtra("abaixoCota")) + " cm)");

        tvNormal.setText("Nível normal (de " + Integer.parseInt(valores.getStringExtra("abaixoCota")) + " cm "
                + " à " + Integer.parseInt(valores.getStringExtra("alerta")) + " cm)");

        tvAlerta.setText("Alerta (de " + Integer.parseInt(valores.getStringExtra("alerta")) + " cm "
                + " à " + Integer.parseInt(valores.getStringExtra("emergencia")) + " cm)");

        tvEmergencia.setText("Emergência (acima de " + Integer.parseInt(valores.getStringExtra("emergencia")) + " cm)");

        ImageView ImgNivelRio = (ImageView) findViewById(R.id.imgNivel);
        TextView TextNivelRio = (TextView) findViewById(R.id.txtNiveldoRio);

        ImgNivelRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                bundle.putString("abaixoCota", String.valueOf(valores.getStringExtra("abaixoCota")));
                bundle.putString("alerta", String.valueOf(valores.getStringExtra("alerta")));
                bundle.putString("emergencia", String.valueOf(valores.getStringExtra("emergencia")));
                Intent telaNivelIMG = new Intent(DetalheEstacaoActivity.this, NivelActivity.class);
                telaNivelIMG.putExtras(bundle);
                startActivity(telaNivelIMG);
                finish();
            }
        });
        TextNivelRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                bundle.putString("abaixoCota", String.valueOf(valores.getStringExtra("abaixoCota")));
                bundle.putString("alerta", String.valueOf(valores.getStringExtra("alerta")));
                bundle.putString("emergencia", String.valueOf(valores.getStringExtra("emergencia")));
                Intent telaNivelTXT = new Intent(DetalheEstacaoActivity.this, NivelActivity.class);
                telaNivelTXT.putExtras(bundle);
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
                bundle.putString("abaixoCota", String.valueOf(valores.getStringExtra("abaixoCota")));
                bundle.putString("alerta", String.valueOf(valores.getStringExtra("alerta")));
                bundle.putString("emergencia", String.valueOf(valores.getStringExtra("emergencia")));
                Intent telaChuvaIMG = new Intent(DetalheEstacaoActivity.this, ChuvaActivity.class);
                telaChuvaIMG.putExtras(bundle);
                startActivity(telaChuvaIMG);
                finish();
            }
        });

        TextChuvaRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                bundle.putString("abaixoCota", String.valueOf(valores.getStringExtra("abaixoCota")));
                bundle.putString("alerta", String.valueOf(valores.getStringExtra("alerta")));
                bundle.putString("emergencia", String.valueOf(valores.getStringExtra("emergencia")));
                Intent telaChuvaTXT = new Intent(DetalheEstacaoActivity.this, ChuvaActivity.class);
                telaChuvaTXT.putExtras(bundle);
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
                Bundle bundle = new Bundle();
                bundle.putString("codestacao", codigoEstacao);
                Intent telaGraficoTXT = new Intent(DetalheEstacaoActivity.this, GraficoActivity.class);
                telaGraficoTXT.putExtras(bundle);
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
          favoritarEstacao();
            //Toast.makeText(getApplicationContext(), "Estação adicionada aos favoritos", Toast.LENGTH_LONG).show();

            return  true;


            // fim implementação lista de favoritos
        }

        return super.onOptionsItemSelected(item);
    }

}
