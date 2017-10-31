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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.R;

public class GraficoActivity extends AppCompatActivity {

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
                    //c.execute();
                    cGrafico.join();
                    cGrafico.start();
                    while(rslt == "START") {
                        try {
                            Thread.sleep(10);

                        }catch(Exception ex) {
                        }
                    }
                    GraphView graph = (GraphView) findViewById(R.id.graph);

                    BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                            new DataPoint(Double.parseDouble(rsltDados[0].dataHora), Double.parseDouble(rsltDados[0].nivel)),
                            new DataPoint(2017, Double.parseDouble(rsltDados[1].nivel)),
                            new DataPoint(2017, Double.parseDouble(rsltDados[2].nivel)),
                            new DataPoint(2017, Double.parseDouble(rsltDados[3].nivel)),
                    });

                    graph.addSeries(series);

                }catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        // Implementação botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); // Ativando o botão
        getSupportActionBar().setTitle("Gráfico"); // Titulo para ser exibido
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

        edtData = (EditText) findViewById(R.id.editDeAGrafico);


        dpResult = (DatePicker) findViewById(R.id.dpGrafico);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    public void setCurrentDateOnView2() {

        edtData2 = (EditText) findViewById(R.id.editAteaChuva);


        dpResult = (DatePicker) findViewById(R.id.dpGrafico);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        dpResult.init(year, month, day, null);

    }
    public void addListenerOnEditText() {

        edtData = (EditText) findViewById(R.id.editDeAGrafico);

        edtData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }
    public void addListenerOnEditTex2() {

        edtData2 = (EditText) findViewById(R.id.editAteAGrafico);

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

}



/*
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

import br.com.emalerta.emalerta.Controll.CallSoapDados2;
import br.com.emalerta.emalerta.Controll.CallerDados;
import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.R;

public class GraficoActivity extends AppCompatActivity {

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
                    //c.execute();
                    cGrafico.join();
                    cGrafico.start();
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
                        System.out.println("Chuva: " + rsltDados[i].chuva);
                        System.out.println("Data e Hora: " + rsltDados[i].dataHora);
                    }

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

        setCurrentDateOnView();
        addListenerOnEditText();

        setCurrentDateOnView2();
        addListenerOnEditTex2();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
            new DataPoint(0,1),
             new DataPoint(1,5),
             new DataPoint(2,3)
        });
        graph.addSeries(series);
    }
    /*
       Inicio implementação do Calendario no EditText
    */
/*
// display current date
public void setCurrentDateOnView() {

    edtData = (EditText) findViewById(R.id.editDeAGrafico);


    dpResult = (DatePicker) findViewById(R.id.dpGrafico);

    final Calendar c = Calendar.getInstance();
    day = c.get(Calendar.DAY_OF_MONTH);
    month = c.get(Calendar.MONTH);
    year = c.get(Calendar.YEAR);

    // set current date into datepicker
    dpResult.init(year, month, day, null);

}

    public void setCurrentDateOnView2() {

        edtData2 = (EditText) findViewById(R.id.editAteaChuva);


        dpResult = (DatePicker) findViewById(R.id.dpGrafico);

        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        dpResult.init(year, month, day, null);

    }
    public void addListenerOnEditText() {

        edtData = (EditText) findViewById(R.id.editDeAGrafico);

        edtData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }
    public void addListenerOnEditTex2() {

        edtData2 = (EditText) findViewById(R.id.editAteAGrafico);

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

} */