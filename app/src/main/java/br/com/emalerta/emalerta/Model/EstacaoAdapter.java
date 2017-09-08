package br.com.emalerta.emalerta.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.emalerta.emalerta.R;

/**
 * Created by Claudio Caique on 08/09/2017.
 */

public class EstacaoAdapter extends ArrayAdapter<Estacao> {


    private final Context context;
    private final ArrayList<Estacao> elementos;

    public EstacaoAdapter(Context context, ArrayList<Estacao> estacoes){
        super(context, R.layout.linhaestacao, estacoes);
        this.context = context;
        this.elementos = estacoes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhaestacao, parent, false);

        TextView estacao = (TextView) rowView.findViewById(R.id.txtEstacao);
        TextView municipio = (TextView) rowView.findViewById(R.id.txtMunicipio);
        TextView rio = (TextView) rowView.findViewById(R.id.txtRio);

        ImageView imagem = (ImageView) rowView.findViewById(R.id.imgEstacao);

        estacao.setText(elementos.get(position).getNomeEstacao());
        municipio.setText(elementos.get(position).getMunicipio());
        rio.setText(elementos.get(position).getNomeRio());

        imagem.setImageResource(elementos.get(position).getImagem());

        return rowView;
    }
}
