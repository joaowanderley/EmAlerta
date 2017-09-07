package br.com.emalerta.emalerta.Model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import br.com.emalerta.emalerta.R;

/**
 * Created by Wanderley on 06/09/2017.
 */

public class BaseDemoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startDemo();
    }
    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    /**
     * Run the demo-specific code.
     */
    protected void startDemo() {

    }

    protected GoogleMap getMap() {
        return mMap;
    }
}
