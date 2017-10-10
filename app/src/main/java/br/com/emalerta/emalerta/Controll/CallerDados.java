package br.com.emalerta.emalerta.Controll;

import br.com.emalerta.emalerta.Model.DadoHistorico;
import br.com.emalerta.emalerta.View.ChuvaActivity;
import br.com.emalerta.emalerta.View.GraficoActivity;
import br.com.emalerta.emalerta.View.NivelActivity;

/**
 * Created by nxt-fabio on 26/08/2017.
 *
Criado com thread pra se melhorado pois não podem acontecer na thread principal
 *
 */

public class CallerDados extends Thread {
    public CallSoapDados2 cs;

    public String codEstacao;
    public String dataInicio;
    public String dataFim;

    public void run(){
        try{
            cs = new CallSoapDados2();
            DadoHistorico[] resp = cs.CallDados(codEstacao, dataInicio, dataFim);
            NivelActivity.rslt = resp.toString();
            NivelActivity.rsltDados = resp;

            ChuvaActivity.rslt = resp.toString();
            ChuvaActivity.rsltDados = resp;

            GraficoActivity.rslt = resp.toString();
            GraficoActivity.rsltDados = resp;

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
