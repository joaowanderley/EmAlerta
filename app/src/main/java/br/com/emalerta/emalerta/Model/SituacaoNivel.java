package br.com.emalerta.emalerta.Model;

/**
 * Created by Claudio Caique on 02/11/2017.
 */

public class SituacaoNivel {
    private int Abaixo_cota;
    private int normal;
    private int alerta;
    private int emergencia;

    public int getAbaixo_cota() {
        return Abaixo_cota;
    }

    public void setAbaixo_cota(int abaixo_cota) {
        Abaixo_cota = abaixo_cota;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public int getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(int emergencia) {
        this.emergencia = emergencia;
    }
}
