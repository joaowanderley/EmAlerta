package br.com.emalerta.emalerta.Controll;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.emalerta.emalerta.Model.DadoHistorico;

/**
 * Created by nxt-fabio on 26/08/2017.
 */

public class CallSoapDados2 {
    public  final String SOAP_ACTION = "http://MRCS/DadosHidrometeorologicos";
    public  final String OPERATION_NAME = "DadosHidrometeorologicos";
    public  final String WSDL_TARGET_NAMESPACE = "http://MRCS/";
    public  final String SOAP_ADDRESS = "http://telemetriaws1.ana.gov.br/ServiceANA.asmx";


    public CallSoapDados2(){

    }


    public DadoHistorico[] CallDados(String codEstacao, String dataInicio, String dataFim) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        PropertyInfo pi = new PropertyInfo();
        pi.setName("codEstacao");  //Mesmo nome  do parãmtro
        pi.setValue(codEstacao);
        pi.setType(String.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("dataInicio");
        pi.setValue(dataInicio);
        pi.setType(String.class);
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("dataFim");
        pi.setValue(dataFim);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        DadoHistorico[] response=null;

        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = RetrieveFromSoap((SoapObject)envelope.getResponse() );
        }
        catch (Exception e)
        {
            Log.e("1111", e.getMessage());
        }
        return response;

    }

    /**
     *
     * @param soap - represents the entering Soap object
     * @return returns the list of categories extracted from the response
     */
    public  DadoHistorico[] RetrieveFromSoap(SoapObject soap)
    {
        DadoHistorico[] DadosHist = null;

        SoapObject root = (SoapObject)soap.getProperty(1);
        if (root != null){
            root = (SoapObject)root.getProperty(0);

            int totalRegistros = root.getPropertyCount();

            if (totalRegistros > 0 ){
                DadosHist = new DadoHistorico[totalRegistros];
                for (int i = 0; i < totalRegistros; i++) { // capturando os itens do xml e gravando em um vetor do tipo dado historic
                    String codestacao = ((SoapObject) root.getProperty(i)).getProperty("CodEstacao").toString();
                    String dataHora = ((SoapObject) root.getProperty(i)).getProperty("DataHora").toString();
                    String vazao = ((SoapObject) root.getProperty(i)).getProperty("Vazao").toString();
                    String nivel = ((SoapObject) root.getProperty(i)).getProperty("Nivel").toString();
                    String chuva = ((SoapObject) root.getProperty(i)).getProperty("Chuva").toString();

                    DadosHist[i] = new DadoHistorico(codestacao, dataHora, vazao, nivel, chuva);

                }
            }//if
        }

        return DadosHist;
    }

}
