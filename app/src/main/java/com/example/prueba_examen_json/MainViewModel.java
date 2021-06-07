package com.example.prueba_examen_json;
/**
 * @author:Sonia Päez
 * Clase View Model que se encarga de conectar la activida principal con le calse repositoio de la base de datos
 */

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.prueba_examen_json.database.Estacion;
import com.example.prueba_examen_json.database.EstacionRepositorio;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private EstacionRepositorio repositorio;
    private LiveData<List<Estacion>> todasEstaciones;
    public MainViewModel( Application application) {
        super(application);
        repositorio= new EstacionRepositorio(application);
        todasEstaciones = repositorio.getEstaciones();

    }
    //Insertamos registro
    public void Insert(Estacion estacion){// Inserta
        repositorio.addEstacion(estacion);
    }
    //Actualizamos registro
    public void Update(Estacion estacion){// Modifica la vbase de datos
        repositorio.updateEstacion(estacion);
    }
    //Borra un  registro
    public void Delete(Estacion estacion){// Borra registro
        repositorio.deleteEstacion(estacion);
    }
    public  LiveData<List<Estacion>> getAllEstaciones(){// Muestra todos los registros

        return todasEstaciones;
    }

}
