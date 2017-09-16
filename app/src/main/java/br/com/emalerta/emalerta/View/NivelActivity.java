package br.com.emalerta.emalerta.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.R;

public class NivelActivity extends AppCompatActivity {

    public static  String rslt = null;
    public static  DadoHistorico[] rsltDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        final String codigoEstacao = valores.getStringExtra("codestacao");

        Button hoje = (Button)findViewById(R.id.btnHoje);
        final AlertDialog ad = new AlertDialog.Builder(this).create();

        hoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSoapDados2 cs = new CallSoapDados2();
                try{

                    EditText dt1 = (EditText)findViewById(R.id.dateDe);
                    EditText dt2 = (EditText)findViewById(R.id.dateA);

                    String codEstacao = codigoEstacao;
                    String dataInicio = dt1.getText().toString();
                    String dataFim    = dt2.getText().toString();

                    rslt = "START";
                    CallerDados c = new CallerDados();
                    c.codEstacao = codEstacao;
                    c.dataInicio = dataInicio;
                    c.dataFim    = dataFim;

                    c.join();
                    c.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }

                    ad.setTitle("RESULTADO -> Estação: " + codEstacao + " | Período: " + dataInicio + " à " + dataFim);

                    //Testando
                    ad.setMessage("Estação: " + rsltDados[18].codEstacao
                            + " | Nível: " + rsltDados[18].nivel
                            + " | Chuva: " + rsltDados[18].chuva
                            + " | Data e Hora: " + rsltDados[18].dataHora);

                }catch(Exception ex){
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }

                ad.show();
            }
        });





        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Nível da Estação"); // Titulo para ser exibido
        // Fim implementação botão voltar

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
