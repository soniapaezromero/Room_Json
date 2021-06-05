package com.example.prueba_examen_json;
/**
 * @author:Sonia Päez Creado el:25/04/2021
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

    public void Insert(Estacion estacion){// Inserta
        repositorio.addEstacion(estacion);
    }
    public void Update(Estacion estacion){// Modifica la vbase de datos
        repositorio.updateEstacion(estacion);
    }
    public void Delete(Estacion estacion){// Borra registro
        repositorio.deleteEstacion(estacion);
    }
    public  LiveData<List<Estacion>> getAllEstaciones(){// Muestra todos los registros

        return todasEstaciones;
    }

}
