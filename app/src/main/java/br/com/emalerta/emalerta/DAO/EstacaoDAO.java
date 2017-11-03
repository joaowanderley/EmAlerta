package br.com.emalerta.emalerta.DAO;

import java.util.ArrayList;

import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.Model.SituacaoNivel;
import br.com.emalerta.emalerta.R;

/**
 * Created by caiqu on 10/09/2017.
 */

public class EstacaoDAO {

    public ArrayList<Estacao> adicionarEstacoes(){
        ArrayList<Estacao> estacaoLista = new ArrayList<Estacao>();

        for(int i = 0; i<estacao.length; i++){
            Estacao estacaoNovo = new Estacao();

            estacaoNovo.setNomeEstacao(estacao[i][0]);
            estacaoNovo.setCodEstacao(estacao[i][1]);
            estacaoNovo.setMunicipio(estacao[i][2]);
            estacaoNovo.setLatitude(estacao[i][3]);
            estacaoNovo.setLongitude(estacao[i][4]);
            estacaoNovo.setAltitude(estacao[i][5]);
            estacaoNovo.setCodRio(estacao[i][6]);
            estacaoNovo.setNomeRio(estacao[i][7]);
            estacaoNovo.setStatus(estacao[i][8]);
            estacaoNovo.setImagem(imagem_estacao[i]);

            SituacaoNivel situacaoNova = new SituacaoNivel();

            situacaoNova.setAbaixo_cota(situacao[i][0]);
            situacaoNova.setNormal(situacao[i][1]);
            situacaoNova.setAlerta(situacao[i][2]);
            situacaoNova.setEmergencia(situacao[i][3]);

            estacaoNovo.setSituacao(situacaoNova);

            estacaoLista.add(estacaoNovo);
        }

        return estacaoLista;
    }

    private int [][] situacao  = new int[][]{
            {143, 168, 277, 520},
            {0, 0, 500, 520},
            {108, 0, 400, 460},
            {109, 132, 194, 520},
            {156, 170, 216, 520},
            {127, 161, 304, 520},
            {434, 460, 500, 520},
            {41, 47, 80, 520},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {122, 158, 251, 500},
            {0, 0, 0, 0},
            {144, 182, 260, 520},
            {96, 139, 210, 520},
            {115, 143, 238, 520},
            {155, 162, 204, 520},
            {0, 0, 0, 0},
            {114, 135, 177, 520},
            {60, 85, 136, 520},
    };

    private String [][] estacao = new String[][]{
            {"Jacuípe","39580000","Jacuípe-AL","-8,8411","-35,4469","61,2","39742000","Rio Jacuípe","Ativo"},
            {"Porto Calvo","39622000","Porto Calvo-AL","-9,0103","-35,435","0","39753500","Rio Manguaba","Ativo"},
            {"Santana do Mundaú","39700000","Santana do Mundaú-AL","-9,1678","-36,2175","0","39762000","Rio Mundaú","Ativo"},
            {"São José da Laje","39720000","São José da Laje-AL","-9,0042","-36,0511","256,54","39762500","Rio Caruru","Ativo"},
            {"Usina Laginha","39745000","União dos Palmares-AL","-9,1831","-36,0436","137","39762000","Rio Mundaú","Ativo"},
            {"Fazenda Boa Fortuna","39770000","Rio Largo-AL","-9,4672","-35,8597","42","39762000","Rio Mundaú","Ativo"},
            {"Vila São Francisco","39852000","Quebrangulo-AL","-9,3656","-36,4194","353,67","39810000","Rio Paraíba","Ativo"},
            {"Paulo Jacinto","39855000","Paulo Jacinto-AL","-9,3686","-36,3747","0","39810000","Rio Paraíba","Ativo"},
            {"Viçosa","39860000","Viçosa-AL","-9,3792","-36,2492","212","39810000","Rio Paraíba","Ativo"},
            {"Cajueiro","39863000","Cajueiro-AL","-9,3756","-36,1608","0","39810000","Rio Paraíba","Ativo"},
            {"Capela","39866000","Capela-AL","-9,38888","-36,11","0","39810000","Rio Paraibinha","Ativo"},
            {"Atalaia","39870000","Atalaia-AL","-9,5067","-36,0228","54,14","39810000","Rio Paraíba","Ativo"},
            {"Fazenda São Pedro","39950000","Anadia-AL","-9,6858","-36,2853","95","39820000","Rio São Miguel","Ativo"},
            {"Limoeiro de Anadia","39970000","Limoeiro de Anadia-AL","-9,7436","-36,5039","0","39850000","Rio Coruripe","Ativo"},
            {"Sítio Cachoeira","39571000","Maraial-PE","-8,8967","-35,7678","150,22","39742000","Rio Jacuipe","Ativo"},
            {"Canhotinho","39575000","Canhotinho-PE","-8,8825","-36,2186","511,11","39762300","Rio Canhoto","Ativo"},
            {"Palmeirina","39715000","Palmeirina-PE","-9,0019","-36,3264","527,03","39419000","Rio Inhumas","Ativo"},
            {"Correntes II","39689000","Correntes-PE","-9,1242","-36,3392","384,08","39762000","Rio Mundaú","Ativo"},
            {"Brejão","39800000","Brejão-PE","-9,0394","-36,5983","0","39762010","Riacho Seco","Ativo"},
    };

    private int[] imagem_estacao = new int[]{
            R.drawable.jacuipe,
            R.drawable.porto_calvo,
            R.drawable.santana_do_mundau,
            R.drawable.sao_jose_da_laje,
            R.drawable.uniao_dos_palmares,
            R.drawable.rio_largo,
            R.drawable.quebrangulo,
            R.drawable.paulo_jacinto,
            R.drawable.vicosa,
            R.drawable.cajueiro,
            R.drawable.capela,
            R.drawable.atalaia,
            R.drawable.anadia,
            R.drawable.limoeiro_de_anadia,
            R.drawable.maraial,
            R.drawable.canhotinho,
            R.drawable.palmeirina,
            R.drawable.correntes,
            R.drawable.brejao,
    };

}
