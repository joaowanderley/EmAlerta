package br.com.emalerta.emalerta.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import br.com.emalerta.emalerta.DAO.EstacaoDAO;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.Model.EstacaoAdapter;
import br.com.emalerta.emalerta.R;

import static br.com.emalerta.emalerta.R.id.parent;

public class EstacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Lista de Estações"); // Titulo para ser exibido
        // Fim implementação botão voltar



        EstacaoDAO listarEstacoes = new EstacaoDAO();

        final ListView lista = (ListView) findViewById(R.id.lvEstacao);
        ArrayList<Estacao> estacoes = listarEstacoes.adicionarEstacoes();
        ArrayAdapter adapter = new EstacaoAdapter(this, estacoes);
        lista.setAdapter(adapter);

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

                Intent intent = new Intent(EstacaoActivity.this, DetalheEstacaoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando. como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
}
