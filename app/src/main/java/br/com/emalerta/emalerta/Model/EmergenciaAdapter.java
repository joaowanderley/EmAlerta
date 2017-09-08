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

public class EmergenciaAdapter extends ArrayAdapter<Orgao>{

    private final Context context;
    private final ArrayList<Orgao> elementos;

    public EmergenciaAdapter(Context context, ArrayList<Orgao> orgaos){
        super(context, R.layout.linhaemergencia, orgaos);
        this.context = context;
        this.elementos = orgaos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhaemergencia, parent, false);

        TextView nomeOrgao = (TextView) rowView.findViewById(R.id.txtOrgao);
        TextView telefone = (TextView) rowView.findViewById(R.id.txtTelefone);
        TextView site = (TextView) rowView.findViewById(R.id.txtSite);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.imgOrgao);

        nomeOrgao.setText(elementos.get(position).getNomeOrgao());
        telefone.setText(elementos.get(position).getTelefone());
        site.setText(elementos.get(position).getSite());
        imagem.setImageResource(elementos.get(position).getImagem());

        return rowView;
    }
}

