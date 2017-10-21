package br.com.emalerta.emalerta.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by nxt-fabio on 27/08/2017.
 */

public class DadoHistorico implements KvmSerializable {


    public String codEstacao;
    public String dataHora;
    public String vazao;
    public String nivel;
    public String chuva;
    public String situacao;
    public int imagem;

    public DadoHistorico(){

    }

    public DadoHistorico(String codEstacao, String dataHora, String vazao, String nivel, String chuva) {
        this.codEstacao = codEstacao;
        this.dataHora = dataHora;
        this.vazao = vazao;
        this.nivel = nivel;
        this.chuva = chuva;
        //this.situacao = situacao;
        //this.imagem = imagem;
    }

    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return codEstacao;
            case 1:
                return dataHora;
            case 2:
                return vazao;
            case 3:
                return nivel;
            case 4:
                return chuva;

        }

        return null;
    }


    public int getPropertyCount() {
        return 5;
    }


    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "codEstacao";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "dataHora";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "vazao";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "nivel";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "chuva";
                break;
            default:break;
        }
    }

    public void setProperty(int index, Object value) {
        switch(index)
        {
            case 0:
                codEstacao = value.toString();
                break;
            case 1:
                dataHora = value.toString();
                break;
            case 2:
                vazao = value.toString();
                break;
            case 3:
                nivel = value.toString();
                break;
            case 4:
                chuva = value.toString();
                break;
             default:
                break;
        }
    }


    public String getCodEstacao() {
        return codEstacao;
    }

    public void setCodEstacao(String codEstacao) {
        this.codEstacao = codEstacao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getVazao() {
        return vazao;
    }

    public void setVazao(String vazao) {
        this.vazao = vazao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getChuva() {
        return chuva;
    }

    public void setChuva(String chuva) {
        this.chuva = chuva;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
