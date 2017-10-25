package br.com.emalerta.emalerta.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.ChuvaAdapter;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.Model.NivelAdapter;
import br.com.emalerta.emalerta.R;

public class ChuvaActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_chuva);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //cria uma nova intenção para buscar os dados enviados pela activityanterior
        final Intent valores = getIntent();

        //pega os valores enviados da activityanterior e preenche os campos
        final String codigoEstacao = valores.getStringExtra("codestacao");

        Button consultarChuva = (Button)findViewById(R.id.btnConsultarC);

        consultarChuva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSoapDados2 cs = new CallSoapDados2();
                try{

                    EditText data1Chuva = (EditText)findViewById(R.id.editDeaChuva);
                    EditText data2Chuva = (EditText)findViewById(R.id.editAteaChuva);

                    String codEstacao = codigoEstacao;
                    String dataInicio = data1Chuva.getText().toString();
                    String dataFim    = data2Chuva.getText().toString();

                    rslt = "START";
                    CallerDados cChuva = new CallerDados();
                    cChuva.codEstacao = codEstacao;
                    cChuva.dataInicio = dataInicio;
                    cChuva.dataFim    = dataFim;
                    //c.execute();
                    cChuva.join();
                    cChuva.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }

                    ListView lista = (ListView) findViewById(R.id.listViewChuva);
                    ArrayList<DadoHistorico> listadeChuva = adicionarChuva();
                    ArrayAdapter adapter = new ChuvaAdapter(getBaseContext(), listadeChuva);
                    lista.setAdapter(adapter);

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Volume de Chuva"); // Titulo para ser exibido
        // Fim implementação botão voltar

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

        edtData = (EditText) findViewById(R.id.editDeaChuva);


        dpResult = (DatePicker) findViewById(R.id.dpChuva);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    public void setCurrentDateOnView2() {

        edtData2 = (EditText) findViewById(R.id.editAteaChuva);


        dpResult = (DatePicker) findViewById(R.id.dpChuva);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        dpResult.init(year, month, day, null);

    }
    public void addListenerOnEditText() {

        edtData = (EditText) findViewById(R.id.editDeaChuva);

        edtData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }
    public void addListenerOnEditTex2() {

        edtData2 = (EditText) findViewById(R.id.editAteaChuva);

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
                        year, month, day);
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
                    .append("/").append(month + 1).append("/").append(year)
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
                    .append("/").append(month + 1).append("/").append(year)
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

    public ArrayList<DadoHistorico> adicionarChuva() {
        ArrayList<DadoHistorico> chuvaLista = new ArrayList<DadoHistorico>();
        //float nivelAnterior = 0;
        //float nivelAtual;

        for (int i = 0; i < rsltDados.length; i++) {
            DadoHistorico chuvaNova = new DadoHistorico();
            /*
            nivelAtual = Float.parseFloat(rsltDados[i].nivel);

            if (nivelAtual > nivelAnterior) {
                nivelNovo.setImagem(R.drawable.subindo);
                nivelAnterior = nivelAtual;
                nivelAtual = 0;
            } else {
                nivelNovo.setImagem(R.drawable.descendo);
            }*/
            chuvaNova.setImagem(R.drawable.com_chuva);
            chuvaNova.setChuva(rsltDados[i].chuva);
            chuvaNova.setDataHora(rsltDados[i].dataHora);
            chuvaNova.setSituacao("Com chuva");

            chuvaLista.add(chuvaNova);
        }

        return chuvaLista;
    }
}