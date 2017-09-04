package br.com.emalerta.emalerta.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.emalerta.emalerta.R;

/**
 * Created by Wanderley on 03/09/2017.
 */

public class tab_emergencia  extends Fragment {
    private static final String TAG = "Tab Emergencia";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_emergencia,container,false);

        return view;
    }


}
