package com.example.prueba_examen_json;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.prueba_examen_json.databinding.ActivityMostrarEstacionBinding;


/**
 * @author :Sonia Páez Romero
 * Activity que nos muestra los datos
 */
public class MostrarEstacion extends AppCompatActivity {
  private ActivityMostrarEstacionBinding bindingMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_estacion);
        bindingMostrar = ActivityMostrarEstacionBinding.inflate(getLayoutInflater());
        View view = bindingMostrar.getRoot();
        setContentView(view);
        Bundle extras = getIntent().getExtras();
        String estado=extras.getString("estado");
        int anclas=extras.getInt("anclajes");
        String anclajes= String.valueOf(anclas);
        int disponibles=extras.getInt("bicisDisponibles");
        String bicisDisponibles=String.valueOf(disponibles);
        String actualizacion=extras.getString("actualizacion");
        bindingMostrar.estadobizis.setText(estado);
        bindingMostrar.bizisAnclajes.setText(anclajes);
        bindingMostrar.bizisdisponibles.setText(bicisDisponibles);
        bindingMostrar.actualizacionBizis.setText(actualizacion);
        bindingMostrar.btoVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}