package br.com.emalerta.emalerta.DAO;

import java.util.ArrayList;

import br.com.emalerta.emalerta.Model.Orgao;
import br.com.emalerta.emalerta.R;

/**
 * Created by caiqu on 10/09/2017.
 */

public class EmergenciaDAO {
    public ArrayList<Orgao> adicionarOrgaos(){
        ArrayList<Orgao> orgaoLista = new ArrayList<Orgao>();

        for(int i = 0; i<emergencia.length; i++){
            Orgao orgaoNovo = new Orgao();

            orgaoNovo.setNomeOrgao(emergencia[i][0]);
            orgaoNovo.setTelefone(emergencia[i][1]);
            orgaoNovo.setSite(emergencia[i][2]);
            orgaoNovo.setImagem(imagem_emergencia[i]);

            orgaoLista.add(orgaoNovo);
        }

        return orgaoLista;
    }

    private String [][] emergencia = new String[][]{
            {"Defesa Civil","3315-2843 | 3315-2822","defesacivil.al.gov.br"},
            {"Corpo de Bombeiro Militar","193","cbm.al.gov.br"},
            {"Semarh","3315-2680","semarh.al.gov.br"},
            {"Polícia Militar","190","pm.al.gov.br"},
            {"SAMU","192","saude.al.gov.br/samu"},
    };

    private int[] imagem_emergencia = new int[]{
            R.drawable.defesacivil,
            R.drawable.cbm,
            R.drawable.semarh,
            R.drawable.pmal,
            R.drawable.samu,
    };
}
