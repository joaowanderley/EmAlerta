package br.com.emalerta.emalerta.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;

import br.com.emalerta.emalerta.R;

/**
 * Created by Wanderley on 03/09/2017.
 */

public class Tab_Map extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    private GeoJsonLayer alLayer;
    private GeoJsonLayer escolaAL;
    private SupportMapFragment mapFragment;
    private static final String TAG = "Tab Map";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       // mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        //mapFragment.getMapAsync(this);

        View view = inflater.inflate(R.layout.tab_map,container,false);

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng alagoas = new LatLng(-9.731095, -36.560825);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alagoas, 8));
        mMap.getUiSettings().setRotateGesturesEnabled(false);
    }
    public void alterarStyleMap(GeoJsonLayer alLayer){

        alLayer.addLayerToMap();
        GeoJsonPolygonStyle estiloLinha = alLayer.getDefaultPolygonStyle();
        estiloLinha.setStrokeWidth(2);

        estiloLinha.setFillColor(getResources().getColor(R.color.myAzul));


    }
}
