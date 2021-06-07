package com.example.prueba_examen_json.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
/**
 * @author:Sonia Päez Creado el:25/04/2021
 * Nos hace las operaciones que queremos hacer con la base de datos
 */


@Dao
public abstract interface EstacionDao {
    @Transaction// Ordena las estaciones oredenadas por id
    @Query(value = "SELECT * FROM estaciones ORDER BY id ")
    LiveData<List<Estacion>> getEstacionnes();
    @Transaction
    @Query("SELECT * FROM estaciones WHERE id LIKE :idestacion")
    LiveData <Estacion> getEstacion(Integer idestacion);

    @Insert(onConflict = OnConflictStrategy.IGNORE)//Inerta datos de la tabla
    void addEstacion(Estacion estacion);

    @Delete
    void deleteEstacion(Estacion estacion);// Borra un registro de la tabla

    @Update
    void updateEstacion(Estacion estacion);// Actualiza el registro

   }
