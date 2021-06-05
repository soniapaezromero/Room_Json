package com.example.prueba_examen_json.database;
/**
 * @author:Sonia Päez Creado el:25/04/2021
 * Crea la abase de datos y ejecuta los disitinto hilos a traves de llamadas
 */

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Estacion.class}, version = 1)
public abstract class Estaciondatabase extends RoomDatabase {
    private static Estaciondatabase INSTANCE;
    public abstract EstacionDao estacionDao();


    public static Estaciondatabase getInstance(Context context){
          if(INSTANCE== null) {
              synchronized (Estaciondatabase.class) {
                  if (INSTANCE == null) {
                      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Estaciondatabase.class, "estaciones")
                              .fallbackToDestructiveMigration()
                              .addCallback(sRoomDatabaseCallback)
                              .build();
                  }
              }
                    }
        return INSTANCE;
    }
    private static Callback sRoomDatabaseCallback = new Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final EstacionDao estacionDao;
        PopulateDbAsync(Estaciondatabase db){
            estacionDao = db.estacionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }



}


