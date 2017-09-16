package br.com.emalerta.emalerta.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.plus.model.people.Person;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPointStyle;
import com.google.maps.android.geojson.GeoJsonPolygonStyle;

import java.util.ArrayList;

import br.com.emalerta.emalerta.DAO.EstacaoDAO;
import br.com.emalerta.emalerta.Model.Estacao;
import br.com.emalerta.emalerta.R;

// extends BaseDemoAcitivity

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {


    private GoogleMap mMap;
    private GeoJsonLayer alLayer;
    private GeoJsonLayer escolaAL;
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

       new ProgressTask(this).execute();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng alagoas = new LatLng(-9.731095, -36.560825);

        LatLng jacuipe = new LatLng(-8.8411, -35.4469);
        LatLng portoCalvo = new LatLng(-9.0103, -35.435);
        LatLng santanaMundau = new LatLng(-9.1678, -36.2175);
        LatLng saoJose = new LatLng(-9.0042, -36.051);
        LatLng uniao = new LatLng(-9.1831, -36.0436);
        LatLng rioLargo = new LatLng(-9.4672, -35.8597);
        LatLng quebrangulo = new LatLng(-9.3656, -36.4194);
        LatLng pauloJacinto = new LatLng(-9.3686, -36.3747);
        LatLng vicosa = new LatLng(-9.3792,-36.2492);
        LatLng cajueiro = new LatLng(-9.3756, -36.1608);
        LatLng capela = new LatLng(-9.38888, -36.11);
        LatLng atalaia = new LatLng(-9.5067, -36.0228);
        LatLng anadia = new LatLng(-9.6858, -36.2853);
        LatLng limoeiro = new LatLng(-9.7436, -36.5039);
        LatLng maraial = new LatLng(-8.8967, -35.7678);
        LatLng canhotinho = new LatLng(-8.8825, -36.2186);
        LatLng palmeirina = new LatLng(-9.0019, -36.3264);
        LatLng correntes = new LatLng(-9.1242, -36.3392);
        LatLng brejao = new LatLng(-9.0394, -36.5983);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alagoas, 8));
        mMap.getUiSettings().setRotateGesturesEnabled(false);



        // marcadores cidades
        mMap.addMarker(new MarkerOptions().position(jacuipe).title("Jacuípe").snippet("Estação de Jacuípe"));
        mMap.addMarker(new MarkerOptions().position(portoCalvo).title("Porto Calvo").snippet("Estação de Porto Calvo"));
        mMap.addMarker(new MarkerOptions().position(santanaMundau).title("Santana do Mundaú").snippet("Estação de Santana do Mundaú-AL"));
        mMap.addMarker(new MarkerOptions().position(saoJose).title("São José da Laje").snippet("Estação de São José da Lage-AL"));
        mMap.addMarker(new MarkerOptions().position(uniao).title("Usina Laginha").snippet("Estação de União dos Palmares-AL"));
        mMap.addMarker(new MarkerOptions().position(rioLargo).title("Fazenda Boa Fortuna").snippet("Estação de Rio Largo-AL"));
        mMap.addMarker(new MarkerOptions().position(quebrangulo).title("Vila São Francisco").snippet("Estação de Quebrangulo-AL"));
        mMap.addMarker(new MarkerOptions().position(pauloJacinto).title("Paulo Jacinto").snippet("Estação de Paulo Jacinto-AL"));
        mMap.addMarker(new MarkerOptions().position(vicosa).title("Viçosa").snippet("Estação de Viçosa-AL"));
        mMap.addMarker(new MarkerOptions().position(cajueiro).title("Cajueiro").snippet("Estação de Cajueiro-AL"));
        mMap.addMarker(new MarkerOptions().position(capela).title("Capela").snippet("Estação de Capela-AL"));
        mMap.addMarker(new MarkerOptions().position(atalaia).title("Atalaia").snippet("Estação de Atalaia-AL"));
        mMap.addMarker(new MarkerOptions().position(anadia).title("Fazenda São Pedro").snippet("Estação de Anadia-AL"));
        mMap.addMarker(new MarkerOptions().position(limoeiro).title("Limoeiro de Anadia").snippet("Estação de Limoeiro de Anadia-AL"));
        mMap.addMarker(new MarkerOptions().position(maraial).title("Sítio Cachoeira").snippet("Estação de Canhotinho-PE"));
        mMap.addMarker(new MarkerOptions().position(canhotinho).title("Canhotinho").snippet("Estação de Canhotinho-PE"));
        mMap.addMarker(new MarkerOptions().position(palmeirina).title("Palmeirina").snippet("Estação de Palmeirina-PE"));
        mMap.addMarker(new MarkerOptions().position(correntes).title("Correntes II").snippet("Estação de Correntes-PE"));
        mMap.addMarker(new MarkerOptions().position(brejao).title("Brejão").snippet("Estação de Brejão-PE"));
    }
    public void alterarStyleMap(GeoJsonLayer alLayer){

        alLayer.addLayerToMap();
        GeoJsonPolygonStyle estiloLinha = alLayer.getDefaultPolygonStyle();
        estiloLinha.setStrokeWidth(2);

        estiloLinha.setFillColor(getResources().getColor(R.color.myAzul));
/*

        GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();
        // Set options for the point style
        pointStyle.setIcon(pointIcon);
        pointStyle.setTitle("Magnitude of " + magnitude);
        pointStyle.setSnippet("Earthquake occured " + feature.getProperty("place"));

        */

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
            Toast.makeText(getApplicationContext(), "Eita, esqueci de implementar. =(", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ProgressTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;

        public ProgressTask(Context context) {
            this.context = context;
    }

        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Carregando Estações...");
            dialog.show();

        }
        protected Boolean doInBackground(final Void... args) {
        try{
            alLayer = new GeoJsonLayer(mapFragment.getMap(), R.raw.alagoas, getApplicationContext());

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
     }
        protected void onPostExecute(final Boolean success) {
            alterarStyleMap(alLayer);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            alLayer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
                @Override
                public void onFeatureClick(GeoJsonFeature geoJsonFeature) {

                 //Intent it = new Intent(MainActivity.this, EstacaoActivity.class);
                    //startActivity(it);
                }
            });


        }
}
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favoritas) {
            Toast.makeText(getApplicationContext(), "Logo estará pronta. =)", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_estacoes) {
            //Chama a tela que lista as estações
            Intent i = new Intent(MainActivity.this, EstacaoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_mapa) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_emergencia) {
            //Chama a tela que lista os orgãos de emergência
            Intent i = new Intent(MainActivity.this, EmergenciaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_cartilha) {
            Toast.makeText(getApplicationContext(), "Vai ficar legal isso aqui. Espero. ;)", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_Sobre) {
            Toast.makeText(getApplicationContext(), "Em desenvolvimento! ;)", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_Compartilhe) {
            Intent compartilhar = new Intent(android.content.Intent.ACTION_SEND);
            compartilhar.setType("text/plain");
            compartilhar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            compartilhar.putExtra(Intent.EXTRA_SUBJECT,
                               "Aplicativo Em Alerta. ");
            compartilhar.putExtra(Intent.EXTRA_TEXT,
                    "Oi! Baixe o Em Alerta e saiba em tempo real as condições dos rios no Estado de Alagoas. " +
                            "Acesse emalerta.com.br");

            startActivity(Intent.createChooser(compartilhar, "Compartilhar"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
