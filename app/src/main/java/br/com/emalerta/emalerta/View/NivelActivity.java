package br.com.emalerta.emalerta.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.Calendario;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.R;

public class NivelActivity extends AppCompatActivity {

    public static  String rslt = null;
    public static  DadoHistorico[] rsltDados = null;
    private DatePicker dpResult;
    private EditText edtData;
    private EditText edtData2;

    private int day;
    private int month;
    private int year;

    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID2 = 998;


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

        Button consultarNivel = (Button)findViewById(R.id.btnConsultar);

        final AlertDialog ad = new AlertDialog.Builder(this).create();

        //ListView lista = (ListView) findViewById(R.id.listViewNivel);
        //ArrayList<DadoHistorico> listadeNivel = adicionarNiveis();
        //ArrayAdapter adapter = new NivelAdapter(this, listadeNivel);


        consultarNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CallSoapDados2 cs = new CallSoapDados2();
                try{

                    EditText data1Nivel = (EditText)findViewById(R.id.editDeA);
                    EditText data2Nivel = (EditText)findViewById(R.id.editAteA);

                    String codEstacao = codigoEstacao;
                    String dataInicio = data1Nivel.getText().toString();
                    String dataFim    = data2Nivel.getText().toString();

                    rslt = "START";
                    CallerDados cNivel = new CallerDados();
                    cNivel.codEstacao = codEstacao;
                    cNivel.dataInicio = dataInicio;
                    cNivel.dataFim    = dataFim;
                   // c.execute();

                    cNivel.join();
                    cNivel.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }

                    for (int i = 0;  i < rsltDados.length; i++){
                        System.out.println("Posição: " + i);
                        System.out.println("Estação: " + rsltDados[i].codEstacao);
                        System.out.println("Nível: " + rsltDados[i].nivel);
                        System.out.println("Data e Hora: " + rsltDados[i].dataHora);
                    }
                    //lista.setAdapter(adapter);


                   ad.setTitle("Estação: " + codEstacao);

                    //Testando
                    ad.setMessage("Nível: " + rsltDados[0].nivel + " | Data e Hora: " + rsltDados[0].dataHora);

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

        /*
        // Chama a classe calendário.
        Calendario calendario = new Calendario();

        calendario.setCurrentDateOnView();
        calendario.addListenerOnEditText();

        calendario.setCurrentDateOnView2();
        calendario.addListenerOnEditTex2();
*/
        // Métodos do calendário
        setCurrentDateOnView();
        addListenerOnEditText();

        setCurrentDateOnView2();
        addListenerOnEditTex2();
    }

/*
Inicio implementação do Calendario no EditText
 */

    // display current date
    public void setCurrentDateOnView() {

        edtData = (EditText) findViewById(R.id.editDeA) ;


        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }
    public void setCurrentDateOnView2() {

        edtData2 = (EditText) findViewById(R.id.editAteA) ;


        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        dpResult.init(year, month, day, null);

    }

    public void addListenerOnEditText() {

        edtData = (EditText) findViewById(R.id.editDeA);

        edtData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }
    public void addListenerOnEditTex2() {

        edtData2 = (EditText) findViewById(R.id.editAteA);

        edtData2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID2);

            }

        });

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(this, datePickerListener2,
                        year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


            // set selected date into EditText
            edtData.setText(new StringBuilder().append(day)
                    .append("/").append(month +1).append("/").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(day, month, year, null);


        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListener2
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


            // set selected date into EditText
            edtData2.setText(new StringBuilder().append(day)
                    .append("/").append(month +1).append("/").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(day, month, year, null);


        }
    };
    /*
    Final da implementação do calendario no EDitText.

     */
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

    public ArrayList<DadoHistorico> adicionarNiveis(){
        ArrayList<DadoHistorico> nivelLista = new ArrayList<DadoHistorico>();

        for(int i = 0; i < rsltDados.length; i++){
            DadoHistorico nivelNovo = new DadoHistorico();

            //nivelNovo.setImagem(R.drawable.subindo);
            nivelNovo.setProperty(3,rsltDados[i].nivel);
            nivelNovo.setProperty(1,rsltDados[i].dataHora);
            //nivelNovo.setSituacao("Normal");

            nivelLista.add(nivelNovo);
        }

        return nivelLista;
    }

}




/*

package br.com.emalerta.emalerta.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.Model.EmergenciaAdapter;
import br.com.emalerta.emalerta.Model.NivelAdapter;
import br.com.emalerta.emalerta.Model.Orgao;
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

        Button consultarNivel = (Button)findViewById(R.id.btnConsultar);

        //final AlertDialog ad = new AlertDialog.Builder(this).create();

        ListView lista = (ListView) findViewById(R.id.listViewNivel);
        ArrayList<DadoHistorico> listadeNivel = adicionarNiveis();
        ArrayAdapter adapter = new NivelAdapter(this, listadeNivel);
        lista.setAdapter(adapter);

        consultarNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSoapDados2 cs = new CallSoapDados2();
                try{

                    EditText data1Nivel = (EditText)findViewById(R.id.editDeA);
                    EditText data2Nivel = (EditText)findViewById(R.id.editAteA);

                    String codEstacao = codigoEstacao;
                    String dataInicio = data1Nivel.getText().toString();
                    String dataFim    = data2Nivel.getText().toString();

                    rslt = "START";
                    CallerDados cNivel = new CallerDados();
                    cNivel.codEstacao = codEstacao;
                    cNivel.dataInicio = dataInicio;
                    cNivel.dataFim    = dataFim;
                   // c.execute();

                    cNivel.join();
                    cNivel.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }

                    for (int i = 0;  i < rsltDados.length; i++){
                        System.out.println("Posição: " + i);
                        System.out.println("Estação: " + rsltDados[i].codEstacao);
                        System.out.println("Nível: " + rsltDados[i].nivel);
                        System.out.println("Data e Hora: " + rsltDados[i].dataHora);
                    }

                   // ad.setTitle("Estação: " + codEstacao);

                    //Testando
                    //ad.setMessage("Nível: " + rsltDados[0].nivel + " | Data e Hora: " + rsltDados[0].dataHora);

                }catch(Exception ex){
                    //ad.setTitle("Error!");
                    //ad.setMessage(ex.toString());
                }

                //ad.show();

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

    public ArrayList<DadoHistorico> adicionarNiveis(){
        ArrayList<DadoHistorico> nivelLista = new ArrayList<DadoHistorico>();

        for(int i = 0; i < rsltDados.length; i++){
            DadoHistorico nivelNovo = new DadoHistorico();

            nivelNovo.setImagem(R.drawable.subindo);
            nivelNovo.setNivel(rsltDados[i].nivel);
            nivelNovo.setDataHora(rsltDados[i].dataHora);
            nivelNovo.setSituacao("Normal");

            nivelLista.add(nivelNovo);
        }

        return nivelLista;
    }

}


 */