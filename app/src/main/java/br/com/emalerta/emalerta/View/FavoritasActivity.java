package br.com.emalerta.emalerta.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.emalerta.emalerta.DAO.EstacaoDAO;
import br.com.emalerta.emalerta.DAO.EstacaoFavorita;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.Model.EstacaoAdapter;
import br.com.emalerta.emalerta.R;

public class FavoritasActivity extends AppCompatActivity {

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

      /*  // inicio implementação lista de favoritos
        EstacaoFavorita listarEstacoesFavoritas = new EstacaoFavorita();

        final ListView lista = (ListView) findViewById(R.id.lvEstacaoFavoritas);
        ArrayList<Estacao> estacoes = listarEstacoesFavoritas.adicionarEstacaoFavorita();
        ArrayAdapter adapter = new EstacaoAdapter(this, estacoes);
        lista.setAdapter(adapter);

        // fim implementação lista de favoritos*/
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
