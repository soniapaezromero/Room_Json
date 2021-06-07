package com.example.prueba_examen_json.database;
/**
 * @author:Sonia Päez Creado el:25/04/2021
 * Mi Clase repositorio crea las distintas operacione sde insertar, actualizar, eliminar registros
 */

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EstacionRepositorio {
    @SuppressLint("StaticFieldLeak")
    public EstacionDao estacionDao;
    private LiveData<List<Estacion>> allEstaciones;

    public EstacionRepositorio(Application aplication) {
        Estaciondatabase estaciondatabase = Estaciondatabase.getInstance(aplication);
        estacionDao = estaciondatabase.estacionDao();
        allEstaciones = estacionDao.getEstacionnes();


    }
    // Obtiene todos los registros
    public  LiveData<List<Estacion>> getEstaciones() {
         return allEstaciones;
    }
    // Obtiene un registro determinado
    public LiveData<Estacion> getEstacion(Integer id) {
        return estacionDao.getEstacion(id);
    }
    // Añade Registro
    public void addEstacion(Estacion estacion) {
        new InsertAsynctask(estacionDao).execute(estacion);
    }
    //actualiza registro
    public void updateEstacion(Estacion estacion) {
        new UpdateAsynctask(estacionDao).execute(estacion);
    }
    //borra registro
    public void deleteEstacion(Estacion estacion) {
        new DeleteAsynctask(estacionDao).execute(estacion);
        ;
    }
// Asyntak para crear registro
    private static class InsertAsynctask extends AsyncTask<Estacion, Void, Void> {
        EstacionDao estacionDao;

        public InsertAsynctask(EstacionDao estacionDao) {
            this.estacionDao = estacionDao;
        }

        @Override
        protected Void doInBackground(Estacion... estacions) {
            estacionDao.addEstacion(estacions[0]);
            return null;
        }
    }
    // Asyntak para actualizar registro
    private static class UpdateAsynctask extends AsyncTask<Estacion, Void, Void> {
        EstacionDao estacionDao;

        public UpdateAsynctask(EstacionDao estacionDao) {
            this.estacionDao = estacionDao;
        }

        @Override
        protected Void doInBackground(Estacion... estacions) {
            estacionDao.updateEstacion(estacions[0]);
            return null;
        }
    }
    // Asyntak para borrar registro
    private static class DeleteAsynctask extends AsyncTask<Estacion, Void, Void> {
        EstacionDao estacionDao;

        public DeleteAsynctask(EstacionDao estacionDao) {
            this.estacionDao = estacionDao;
        }

        @Override
        protected Void doInBackground(Estacion... estacions) {
            estacionDao.deleteEstacion(estacions[0]);
            return null;
        }
    }
}



