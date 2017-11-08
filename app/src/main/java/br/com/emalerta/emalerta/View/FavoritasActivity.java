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
        int[] to = {R.id.tvId, R.id.tvNome, R.id.tvMunicipio, R.id.txtRio};
        String[] from = {"_id", "cod_estacao", "nome", "municipio"};
        try{
            SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(), R.layout.dadosestacao, dadosestacao, from, to, 0);

            ListView listar;
            listar = (ListView) findViewById(R.id.lvEstacao);

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
       // favoritarEstacao();


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
