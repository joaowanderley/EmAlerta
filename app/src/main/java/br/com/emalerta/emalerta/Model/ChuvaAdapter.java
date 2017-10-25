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
 * Created by Claudio Caique on 04/09/2017.
 */

public class ChuvaAdapter extends ArrayAdapter<DadoHistorico>{

    private final Context context;
    private final ArrayList<DadoHistorico> elementos;

    public ChuvaAdapter(Context context, ArrayList<DadoHistorico> niveis){
        super(context, R.layout.linhachuva, niveis);
        this.context = context;
        this.elementos = niveis;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhachuva, parent, false);

        ImageView imagem = (ImageView) rowView.findViewById(R.id.imgSituacaoListaChuva);
        TextView chuva = (TextView) rowView.findViewById(R.id.txtChuvaListaChuva);
        TextView dataehora = (TextView) rowView.findViewById(R.id.txtDatahoraListaChuva);
        TextView situacao = (TextView) rowView.findViewById(R.id.txtSituacaoListaChuva);

        imagem.setImageResource(elementos.get(position).getImagem());
        chuva.setText(elementos.get(position).getChuva());
        dataehora.setText(elementos.get(position).getDataHora());
        situacao.setText(elementos.get(position).getSituacao());

        return rowView;
    }
}