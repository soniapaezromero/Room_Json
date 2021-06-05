package com.example.prueba_examen_json.database.edit;
/**
 * @author:Sonia Päez
 * Actividad para Editar Registros recoge los datos los pasa a la actividad princiapl paa incluirlos en la base de datos
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
import com.example.prueba_examen_json.databinding.ActivityEditEstacionBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EditEstacion extends AppCompatActivity   implements View.OnClickListener{
    private ActivityEditEstacionBinding editEstacionBinding;
    private EstacionRepositorio repositorio;
    private boolean existe=false;
    private Date ahora;
    private Estacion estacionModificada;

    public static final String  EXTRA_EDITID="com.example.prueba_examen_json.database.edit.EDITID";
    public static final String EXTRA_EDITNOMBRE ="com.example.prueba_examen_json.database.edit.EXTRA_EDITNOMBRE";
    public static final String EXTRA_EDITESTADO ="com.example.prueba_examen_json.database.edit.EXTRA_EDITESTADO";
    public static final String EXTRA_EDITADRESS ="com.example.prueba_examen_json.database.edit.EXTRA_EDITADRESS";
    public static final String EXTRA_EDITBICIS ="com.example.prueba_examen_json.database.edit.EXTRA_EDITBICIS";
    public static final String EXTRA_EDITANCLAJES ="com.example.prueba_examen_json.database.edit.EXTRA_EDITANCLAJES";
    public static final String EXTRA_EDITACTUALIZACION ="com.example.prueba_examen_json.database.edit.EXTRA_EDITACTUALIZACION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_estacion);
        editEstacionBinding = ActivityEditEstacionBinding.inflate(getLayoutInflater());
        View view = editEstacionBinding.getRoot();
        setContentView(view);
        Intent intent= getIntent();
        if(intent.hasExtra(EXTRA_EDITID)) {
            setTitle("Modificar Estación");
            Log.e("editar", String.valueOf(intent.getIntExtra(EXTRA_EDITID,-1)));
            editEstacionBinding.editnombre.setText(intent.getStringExtra(EXTRA_EDITNOMBRE));
            Log.e("editar", intent.getStringExtra(EXTRA_EDITNOMBRE));
            editEstacionBinding.editEstado.setText(intent.getStringExtra(EXTRA_EDITESTADO));
            editEstacionBinding.editAdress.setText(intent.getStringExtra(EXTRA_EDITADRESS));
            editEstacionBinding.editBicis.setText(intent.getStringExtra(EXTRA_EDITBICIS));
            editEstacionBinding.editAnclajes.setText(intent.getStringExtra(EXTRA_EDITANCLAJES));
            editEstacionBinding.editActualizacion.setText(intent.getStringExtra(EXTRA_EDITACTUALIZACION));


        }

        //configuramos los clicks

        editEstacionBinding.botonAdd.setOnClickListener(this);
        editEstacionBinding.botonBorrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if (v== editEstacionBinding.botonAdd){
           modificarEstacion();

        }
        if(v== editEstacionBinding.botonBorrar){
           finish();
        }


    }

    private void modificarEstacion() {
        editEstacionBinding.editnombre.setError(null);
        editEstacionBinding.editEstado.setError(null);
        editEstacionBinding.editAdress.setError(null);
        editEstacionBinding.editBicis.setError(null);
        editEstacionBinding.editAnclajes.setError(null);
        editEstacionBinding.editActualizacion.setError(null);
        ahora=new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String nombre = editEstacionBinding.editnombre.getText().toString();
        String estado = editEstacionBinding.editEstado.getText().toString();
        String adress = editEstacionBinding.editAdress.getText().toString();
        String bicis = editEstacionBinding.editBicis.getText().toString();
        String anclajes = editEstacionBinding.editBicis.getText().toString();
        String actualizacion=hourdateFormat.format(ahora);



        if ("".equals(nombre)) {
            editEstacionBinding.editnombre.setText("Tienes que introducir el dato");
            editEstacionBinding.editnombre.requestFocus();
            return;
        }
        if ("".equals(estado)) {
            editEstacionBinding.editEstado.setText("Tienes que introducir el dato");
            editEstacionBinding.editEstado.requestFocus();
            return;
        }
        if ("".equals(adress)) {
            editEstacionBinding.editAdress.setText("Tienes que introducir el dato");
            editEstacionBinding.editAdress.requestFocus();
            return;
        }
        if ("".equals(bicis)) {
            editEstacionBinding.editBicis.setText("Tienes que introducir el dato");
            editEstacionBinding.editBicis.requestFocus();
            return;
        }
        if ("".equals(anclajes)) {
            editEstacionBinding.editAnclajes.setText("Tienes que introducir el dato");
            editEstacionBinding.editAnclajes.requestFocus();
            return;
        }
        if ("".equals(actualizacion)) {
            editEstacionBinding.editActualizacion.setText(hourdateFormat.format(ahora));
            editEstacionBinding.editActualizacion.requestFocus();
            return;
        }
        estacionModificada = new Estacion();
        estacionModificada.setNombre(nombre);
        estacionModificada.setEstado(estado);
        estacionModificada.setAddress(adress);
        estacionModificada.setBicisDisponibles(Integer.valueOf(bicis));
        estacionModificada.setBicisDisponibles(Integer.valueOf(bicis));
        estacionModificada.setAnclajesDisponibles(Integer.valueOf(anclajes));
        estacionModificada.setLastUpdated(actualizacion);

        List<Estacion> estacionlista = new ArrayList<>();
        estacionlista = MainActivity.estacionesExistentes;
        for (Estacion e : estacionlista) {
            Log.e("ESTACIONESGUARDADASEdit", e.toString());
            if ((e.getNombre().equals(nombre)) && (e.getEstado().equals(estado)) && (e.getAddress().equals(adress))) {
                Toast.makeText(this, "Estacion no se puede modificar, estacion existe",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Estacion modificada correctamente",
                        Toast.LENGTH_SHORT).show();
                Intent datoEdit = new Intent();
                datoEdit.putExtra(EXTRA_EDITNOMBRE, nombre);
                datoEdit.putExtra(EXTRA_EDITESTADO, estado);
                datoEdit.putExtra(EXTRA_EDITADRESS, adress);
                datoEdit.putExtra(EXTRA_EDITBICIS, bicis);
                datoEdit.putExtra(EXTRA_EDITANCLAJES, anclajes);
                datoEdit.putExtra(EXTRA_EDITACTUALIZACION, actualizacion);
                Integer id = getIntent().getIntExtra(EXTRA_EDITID, -1);
                Log.e("DAtos Enviados ID", String.valueOf(id));
                if (id != -1) {
                    datoEdit.putExtra(EXTRA_EDITID, id);
                }
                setResult(RESULT_OK, datoEdit);
                Log.e("DAtos Enviados", "Editar encia datos");
                finish();
            }

        }
    }


}