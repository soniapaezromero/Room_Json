package com.example.prueba_examen_json;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List coordenadas;


    //Iniciamos el activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    // Creamos el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_maps, menu);
        return true;
    }
    // El Agrupamos las distintas opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.finish://LLamamos al metodo que calcula los metros cuadrados
            finish();
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //pasamos la longitud y latitud  del objeto
        Bundle extras = getIntent().getExtras();
        String direccion= extras.getString("direccion");

        String latitudPasada=extras.getString("latitud");
        String longitudPasada= extras.getString("longitud");
        double latitud = Double.parseDouble(latitudPasada);
        double longitud = Double.parseDouble(longitudPasada);
        LatLng estacion = new LatLng(latitud, longitud);

        mMap.addMarker(new MarkerOptions().position(estacion).title("Marker in Estacion: "+direccion+" (Spain)"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(estacion, 15), 7000, null);
        Toast.makeText(this,"Estacion Situada en "+ direccion,Toast.LENGTH_SHORT).show();

    }

    public List getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List coordenadas) {
        this.coordenadas = coordenadas;
    }
}