package com.example.prueba_examen_json.database;
/**
 * @author:Sonia Päez Creado el:25/04/2021
 * Crea la tabla reservas que es la encargada de registrar los paseos
 */

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estaciones")
public class Estacion {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "estado")
    private String estado;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "bicisDisponibles")
    private Integer bicisDisponibles;
    @ColumnInfo(name = "anclajesDisponibles")
    private Integer anclajesDisponibles;
    @ColumnInfo(name = "lastUpdated")
    private String lastUpdated;

    public Estacion(Integer id) {
        this.id = id;
    }
    public Estacion(){

    }

    public Estacion(String nombre, String estado, String address, Integer bicisDisponibles,Integer anclajesDisponibles,String lastUpdated) {
        this.nombre = nombre;
        this.estado = estado;
        this.address = address;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles=anclajesDisponibles;
        this.lastUpdated=lastUpdated;
    }

    @NonNull
    public Integer  getId() {
        return id;
    }

    public void setId(@NonNull Integer  id) {
        this.id = id;
    }

    @NonNull

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public void setBicisDisponibles(Integer bicisDisponibles) {
        this.bicisDisponibles = bicisDisponibles;
    }

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

    public void setAnclajesDisponibles(Integer anclajesDisponibles) {
        this.anclajesDisponibles = anclajesDisponibles;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Estacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", address='" + address + '\'' +
                ", bicisDisponibles=" + bicisDisponibles +
                ", anclajesDisponibles=" + anclajesDisponibles +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
