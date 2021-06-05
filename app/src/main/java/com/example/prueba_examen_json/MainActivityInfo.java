package com.example.prueba_examen_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.prueba_examen_json.adaptadores.EstacionAdapter;
import com.example.prueba_examen_json.adaptadores.RecyclerTouchListener;
import com.example.prueba_examen_json.database.Estacion;
import com.example.prueba_examen_json.databinding.ActivityMainInfoBinding;

import java.util.List;

public class MainActivityInfo extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainInfoBinding infoBinding;
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private EstacionAdapter adapter;
    public  List<Estacion> estacionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info);
        infoBinding = ActivityMainInfoBinding.inflate(getLayoutInflater());
        View view = infoBinding.getRoot();
        setContentView(view);
        adapter= new EstacionAdapter();
        infoBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infoBinding.recyclerView.setHasFixedSize(true);
        infoBinding.recyclerView.setAdapter(adapter);

        //LLamo al View model
        mainViewModel=new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllEstaciones().observe(this, new Observer<List<Estacion>>() {
            @Override
            public void onChanged(List<Estacion> estaciones) {
                adapter.setEstaciones(estaciones);
                setEstacionList(estaciones);
            }

        });
        infoBinding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, infoBinding.recyclerView, new RecyclerTouchListener.ClickListener() {
            //Click Corto modifica la Reserva
            @Override
            public void onClick(View view, int position) {
                Estacion estacionSeleccionada= estacionList.get(position);
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivityInfo.this).setPositiveButton("Sí, Ver", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivityInfo.this, MostrarEstacion.class);
                        intent.putExtra("estado", estacionSeleccionada.getEstado());
                        intent.putExtra("anclajes",estacionSeleccionada.getAnclajesDisponibles());
                        intent.putExtra("bicisDisponibles", estacionSeleccionada.getBicisDisponibles());
                        intent.putExtra("actualizacion",estacionSeleccionada.getLastUpdated());
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .setTitle("Ver datos de Estacion")
                        .setMessage("¿Quieres ver los datos de la estacion " + estacionSeleccionada.getNombre() + "?")
                        .create();
                alertDialog.show();
            }
            //Click largo elimina la Reserva
            @Override
            public void onLongClick(View view, int position) {
                final Estacion estacionEliminar= estacionList.get(position);;

                AlertDialog dialog1 = new AlertDialog
                        .Builder(MainActivityInfo.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainViewModel.Delete(estacionEliminar);

                                Toast.makeText(MainActivityInfo.this, "Estacion eliminada", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la estacion" + estacionEliminar.getNombre() + "?")
                        .create();
                dialog1.show();

            }


        }));
        infoBinding.btoVolver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v== infoBinding.btoVolver){
            finish();
        }

    }

    public List<Estacion> getEstacionList() {
        return estacionList;
    }

    public void setEstacionList(List<Estacion> estacionList) {
        this.estacionList = estacionList;
    }
}