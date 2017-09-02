package br.com.emalerta.emalerta;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

   // private SectionsPagerAdapter mSectionsPagerAdapter;

   // private ViewPager mViewPager;

   // private ImageView imgfavorite, imgestacoes, imgmaps, imgemergencia;

    private GoogleMap mMap;

    private GeoJsonLayer alLayer;

    private SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //new ProgressTask(this).execute();
    }
        // inicio manipulação do mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng alagoas = new LatLng(-9.731095, -36.560825);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alagoas, 8));
        mMap.getUiSettings().setRotateGesturesEnabled(false);

    }

    //
    public void alterarEstiloMapa(GeoJsonLayer alLayer){

            alLayer.addLayerToMap();
            GeoJsonPolygonStyle estiloLinha = alLayer.getDefaultPolygonStyle();
            estiloLinha.setStrokeWidth(2);
            estiloLinha.setFillColor(getResources().getColor(R.color.myAzul));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favoritas) {
            Toast.makeText(getApplicationContext(), "Aba estações favoritas", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_estacoes) {

        } else if (id == R.id.nav_mapa) {

        } else if (id == R.id.nav_emergencia) {

        }
        /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
       */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

public class ProgressTask extends AsyncTask<Void, Void, Boolean>{

    private ProgressDialog dialog;
    private Context context;

        public ProgressTask(Context context){
            this.context=context;
        }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Carregando Mapa");
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(final Void... args) {

        try{

            alLayer = new GeoJsonLayer(alLayer.getMap(), R.raw.alagoas, getApplicationContext());

            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }

    }

    @Override
    protected void onPostExecute(final Boolean sucess) {
       alterarEstiloMapa(alLayer);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}


}
