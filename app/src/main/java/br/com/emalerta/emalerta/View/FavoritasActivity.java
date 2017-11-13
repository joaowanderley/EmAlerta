package br.com.emalerta.emalerta.View;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.emalerta.emalerta.DAO.EstacaoDAO;
import br.com.emalerta.emalerta.DAO.EstacaoFavorita;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.Model.EstacaoAdapter;
import br.com.emalerta.emalerta.R;

public class FavoritasActivity extends AppCompatActivity {

    SQLiteDatabase db;
    public void listarEstacao(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tb_estacao");

        Cursor dadosestacao = db.rawQuery(sql.toString(),null);
        //Array com os IDs dos campos do layout dos dados
        int[] to = {R.id.tvCodEstacao, R.id.tvNome, R.id.tvMunicipio, R.id.tvRio, R.id.imgEstacaoFavorita};
        String[] from = {"cod_estacao", "nome", "municipio", "rio", "img"};

        try{
            SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dadosestacao, dadosestacao, from, to, 0);

            ListView listar;
            listar = (ListView) findViewById(R.id.lvEstacaoFavoritas);

            listar.setAdapter(ad);
        }catch (Exception ex){
            Toast.makeText(getBaseContext(), sql.toString() + "Erro = " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Estações Favoritas"); // Titulo para ser exibido
        // Fim implementação botão voltar

        db = openOrCreateDatabase("estacao_banco.db", Context.MODE_PRIVATE, null);

        listarEstacao();


        final ListView lista = (ListView) findViewById(R.id.lvEstacaoFavoritas);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*View v = lista.getChildAt(i);

                String codEstacao, nome, municipio, rio,imgEstacao;

                TextView tvCod = (TextView) findViewById(R.id.tvCodEstacao);
                codEstacao =  tvCod.getText().toString();
                TextView tvNome = (TextView) findViewById(R.id.tvNome);
                nome = tvNome.getText().toString();
                TextView tvMunicipio = (TextView) findViewById(R.id.tvMunicipio);
                municipio = tvMunicipio.getText().toString();
                TextView tvRio = (TextView) findViewById(R.id.tvRio);
                rio = tvRio.getText().toString();
                ImageView img = (ImageView) findViewById(R.id.imgEstacaoFavorita);
                imgEstacao = String.valueOf(img);


                Bundle bundle = new Bundle();
*/
                Intent intent = new Intent(FavoritasActivity.this, EstacaoActivity.class);
                /*
                bundle.putString("codestacao", codEstacao);
                bundle.putString("nomeestacao", nome);
                bundle.putString("municipio", municipio);
                bundle.putString("rio", rio);
                bundle.putString("img", String.valueOf(imgEstacao));
                intent.putExtras(bundle);*/
                startActivity(intent);
                finish();
            }
        });




        /*
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Estacao estacaoSelecionada = (Estacao)lista.getItemAtPosition(i);

                Bundle bundle = new Bundle();
                bundle.putString("nomeestacao", estacaoSelecionada.getNomeEstacao());
                bundle.putString("codestacao", estacaoSelecionada.getCodEstacao());
                bundle.putString("municipio", estacaoSelecionada.getMunicipio());
                bundle.putString("rio", estacaoSelecionada.getNomeRio());
                bundle.putString("img", String.valueOf(estacaoSelecionada.getImagem()));
                bundle.putString("abaixoCota", String.valueOf(estacaoSelecionada.getSituacao().getAbaixo_cota()));
                bundle.putString("normal", String.valueOf(estacaoSelecionada.getSituacao().getNormal()));
                bundle.putString("alerta", String.valueOf(estacaoSelecionada.getSituacao().getAlerta()));
                bundle.putString("emergencia", String.valueOf(estacaoSelecionada.getSituacao().getEmergencia()));

                Intent intent = new Intent(FavoritasActivity.this, DetalheEstacaoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                Intent enviarParaFavoritos = new Intent(FavoritasActivity.this, EstacaoFavorita.class);
                enviarParaFavoritos.putExtras(bundle);
            }
        });
*/

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando. como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:
                break;
        }
        return true;
    }
}
