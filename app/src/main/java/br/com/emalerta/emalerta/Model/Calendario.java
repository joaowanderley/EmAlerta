package br.com.emalerta.emalerta.Model;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import br.com.emalerta.emalerta.R;

/**
 * Created by Wanderley on 21/10/2017.
 */

public class Calendario extends Activity {

    private DatePicker dpResult;
    private EditText edtData;
    private EditText edtData2;

    private int day;
    private int month;
    private int year;

    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID2 = 998;


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
}
