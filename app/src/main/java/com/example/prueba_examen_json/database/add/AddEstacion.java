package com.example.prueba_examen_json.database.add;
/**
 * @author:Sonia Päez
 * Actividad para añadir Registros recoge los datos los pasa a la ctividad princiapl paa incluirlos en la base de datos
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.prueba_examen_json.MainActivity;
import com.example.prueba_examen_json.R;
import com.example.prueba_examen_json.database.Estacion;
import com.example.prueba_examen_json.database.EstacionRepositorio;
import com.example.prueba_examen_json.databinding.ActivityAddEstacionBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddEstacion extends AppCompatActivity implements View.OnClickListener  {
    private ActivityAddEstacionBinding addbinding;
    private EstacionRepositorio repositorio;
    private Estacion estacion;
    private boolean existe=false;
    private Date ahora;

    public static final String EXTRA_ADDNOMBRE ="com.example.prueba_examen_json.database.add.EXTRA_ADDNOMBRE";
    public static final String EXTRA_ADDESTADO ="com.example.prueba_examen_json.database.add.EXTRA_ADDESTADO";
    public static final String EXTRA_ADDADRESS ="com.example.prueba_examen_json.database.add.EXTRA_ADDADRESS";
    public static final String EXTRA_ADDBICIS ="com.example.prueba_examen_json.database.add.EXTRA_ADDBICIS";
    public static final String EXTRA_ADDANCLAJES ="com.example.prueba_examen_json.database.add.EXTRA_ADDANCLAJES";
    public static final String EXTRA_ADDACTUALIZACION ="com.example.prueba_examen_json.database.add.EXTRA_ADDACTUALIZACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estacion);
        addbinding = ActivityAddEstacionBinding.inflate(getLayoutInflater());
        View view = addbinding.getRoot();
        setContentView(view);
        setTitle("Añadir Estacion");


        addbinding.botonAdd.setOnClickListener(this);
        addbinding.botonBorrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v== addbinding.botonAdd){
            crearEstacion();
        }
        if(v== addbinding.botonBorrar){
            finish();
        }
    }

    private void crearEstacion() {
        addbinding.addnombre.setError(null);
        addbinding.addEstado.setError(null);
        addbinding.addAdress.setError(null);
        addbinding.addBicis.setError(null);
        addbinding.addAnclajes.setError(null);
        addbinding.addActualizacion.setError(null);
        ahora=new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String nombre = addbinding.addnombre.getText().toString();
        String estado = addbinding.addEstado.getText().toString();
        String adress = addbinding.addAdress.getText().toString();
        String bicis = addbinding.addBicis.getText().toString();
        String anclajes = addbinding.addAnclajes.getText().toString();
        String actualizacion = hourdateFormat.format(ahora);
        addbinding.addActualizacion.setText(actualizacion);



        if ("".equals(nombre)) {
            addbinding.addnombre.setText("Tienes que introducir el dato");
            addbinding.addnombre.requestFocus();
            return;
        }
        if ("".equals(estado)) {
            addbinding.addEstado.setText("Tienes que introducir el dato");
            addbinding.addEstado.requestFocus();
            return;
        }
        if ("".equals(adress)) {
            addbinding.addAdress.setText("Tienes que introducir el dato");
            addbinding.addAdress.requestFocus();
            return;
        }
        if ("".equals(bicis)) {
            addbinding.addBicis.setText("Tienes que introducir el dato");
            addbinding.addBicis.requestFocus();
            return;
        }
        if ("".equals(anclajes)) {
            addbinding.addAnclajes.setText("Tienes que introducir el dato");
            addbinding.addAnclajes.requestFocus();
            return;
        }
        if ("".equals(actualizacion)) {
            addbinding.addActualizacion.setText(hourdateFormat.format(ahora));
            addbinding.addActualizacion.requestFocus();
            return;
        }

        estacion = new Estacion();
        estacion.setNombre(nombre);
        estacion.setEstado(estado);
        estacion.setAddress(adress);
        estacion.setBicisDisponibles(Integer.valueOf(bicis));
        estacion.setAnclajesDisponibles(Integer.valueOf(anclajes));
        estacion.setLastUpdated(actualizacion);

        List<Estacion> estacionesLista = new ArrayList<>();
        estacionesLista = MainActivity.estacionesExistentes;
        for (Estacion e : estacionesLista) {
            Log.e("ESTACIONESgUARDADASAdd", e.toString());
            if ((e.getNombre().equals(nombre)) && (e.getEstado().equals(estado)) && (e.getAddress().equals(adress))) {
                Toast.makeText(this, "Extacion no se puede añadida,estacion existe",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Estacion añadida correctamente",
                        Toast.LENGTH_SHORT).show();
                Intent datoAdd = new Intent();
                datoAdd.putExtra(EXTRA_ADDNOMBRE, nombre);
                datoAdd.putExtra(EXTRA_ADDESTADO, estado);
                datoAdd.putExtra(EXTRA_ADDADRESS, adress);
                datoAdd.putExtra(EXTRA_ADDBICIS, bicis);
                datoAdd.putExtra(EXTRA_ADDANCLAJES, anclajes);
                datoAdd.putExtra(EXTRA_ADDACTUALIZACION, actualizacion);
                setResult(RESULT_OK, datoAdd);
                finish();

            }
        }
    }


}