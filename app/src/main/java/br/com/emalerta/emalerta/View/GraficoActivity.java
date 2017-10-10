package br.com.emalerta.emalerta.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class GraficoActivity extends AppCompatActivity {

    public static  String rslt = null;
    public static  DadoHistorico[] rsltDados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        final String codigoEstacao = valores.getStringExtra("codestacao");

        Button consultarGrafico = (Button)findViewById(R.id.btnConsultarGrafico);

        final AlertDialog ad = new AlertDialog.Builder(this).create();

        consultarGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSoapDados2 cs = new CallSoapDados2();
                try{

                    EditText data1Grafico = (EditText)findViewById(R.id.editDeAGrafico);
                    EditText data2Grafico  = (EditText)findViewById(R.id.editAteAGrafico);

                    String codEstacao = codigoEstacao;
                    String dataInicio = data1Grafico.getText().toString();
                    String dataFim    = data2Grafico.getText().toString();

                    rslt = "START";
                    CallerDados cGrafico = new CallerDados();
                    cGrafico.codEstacao = codEstacao;
                    cGrafico.dataInicio = dataInicio;
                    cGrafico.dataFim    = dataFim;

                    cGrafico.join();
                    cGrafico.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }
                    /*
                    for (int i = 0; rsltDados.; i++){
                        System.out.println(rsltDados[i].codEstacao);
                        System.out.println(rsltDados[i].nivel);
                        System.out.println(rsltDados[i].chuva);
                    }
                    */
                    ad.setTitle("Estação: " + codEstacao);

                    //Testando
                    ad.setMessage("Nivel: " + rsltDados[0].nivel + " | Chuva: " + rsltDados[0].chuva + " | Data e Hora: " + rsltDados[0].dataHora);

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
        getSupportActionBar().setTitle("Gráfico"); // Titulo para ser exibido
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
