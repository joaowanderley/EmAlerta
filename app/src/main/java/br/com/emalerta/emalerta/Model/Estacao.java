package br.com.emalerta.emalerta.Model;

/**
 * Created by caiqu on 08/09/2017.
 */

public class Estacao {
    private String NomeEstacao;
    private String CodEstacao;
    private String Municipio;
    private String Latitude;
    private String Longitude;
    private String Altitude;
    private String CodRio;
    private String NomeRio;
    private String Status;
    private int imagem;

    public Estacao(String NomeEstacao, String CodEstacao, String Municipio, String Latitude, String Longitude, String Altitude,
                   String CodRio, String NomeRio, String Status, int imagem){
        this.NomeEstacao = NomeEstacao;
        this.CodEstacao = CodEstacao;
        this.Municipio = Municipio;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.Altitude = Altitude;
        this.CodRio = CodRio;
        this.NomeRio = NomeRio;
        this.Status = Status;
        this.imagem = imagem;
    }

    public Estacao(){

    }

    public String getNomeEstacao() {
        return NomeEstacao;
    }

    public void setNomeEstacao(String nomeEstacao) {
        NomeEstacao = nomeEstacao;
    }

    public String getCodEstacao() {
        return CodEstacao;
    }

    public void setCodEstacao(String codEstacao) {
        CodEstacao = codEstacao;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getAltitude() {
        return Altitude;
    }

    public void setAltitude(String altitude) {
        Altitude = altitude;
    }

    public String getCodRio() {
        return CodRio;
    }

    public void setCodRio(String codRio) {
        CodRio = codRio;
    }

    public String getNomeRio() {
        return NomeRio;
    }

    public void setNomeRio(String nomeRio) {
        NomeRio = nomeRio;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
