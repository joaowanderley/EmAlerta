package br.com.emalerta.emalerta.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.emalerta.emalerta.Model.EmergenciaAdapter;
import br.com.emalerta.emalerta.Model.Orgao;
import br.com.emalerta.emalerta.R;

public class EmergenciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // ativando o botão
        getSupportActionBar().setTitle("Lista de Emergência"); // titulo para ser exibido
        // fim implementação botão voltar


        //Se tirar o comentário dos comandos a seguir, vai ver que o findViewById não funciona
        //Porque? Não sei como essa desgraça de TAB funciona. Google não ajudou

        ListView lista = (ListView) findViewById(R.id.lvEmergencia);
        ArrayList<Orgao> emergencias = adicionarOrgaos();
        ArrayAdapter adapter = new EmergenciaAdapter(this, emergencias);
        lista.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    private ArrayList<Orgao> adicionarOrgaos(){
        ArrayList<Orgao> orgaoLista = new ArrayList<Orgao>();

        for(int i = 0; i<emergencia.length; i++){
            Orgao orgaoNovo = new Orgao();

            orgaoNovo.setNomeOrgao(emergencia[i][0]);
            orgaoNovo.setTelefone(emergencia[i][1]);
            orgaoNovo.setSite(emergencia[i][2]);
            orgaoNovo.setImagem(imagens[i]);

            orgaoLista.add(orgaoNovo);
        }

        return orgaoLista;
    }


    private String [][] emergencia = new String[][]{
            {"Defesa Civil","3315-2843 | 3315-2822","defesacivil.al.gov.br"},
            {"Corpo de Bombeiro Militar","193","cbm.al.gov.br"},
            {"Semarh","3315-2680","semarh.al.gov.br"},
            {"Polícia Militar","190","pm.al.gov.br"},
            {"SAMU","192","saude.al.gov.br/samu"},
    };

    private int[] imagens = new int[]{
            R.drawable.defesacivil,
            R.drawable.cbm,
            R.drawable.semarh,
            R.drawable.pmal,
            R.drawable.samu,
    };

}
