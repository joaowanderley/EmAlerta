package br.com.emalerta.emalerta.Model;

/**
 * Created by Claudio Caique on 04/09/2017.
 */

public class Orgao {

    private String nomeOrgao;
    private String telefone;
    private String site;
    private int imagem;

    public Orgao(String nomeOrgao, String telefone, String site, int imagem){
        this.nomeOrgao = nomeOrgao;
        this.telefone = telefone;
        this.site = site;
        this.imagem = imagem;
    }
    public Orgao(){

    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
