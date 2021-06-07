package com.example.prueba_examen_json.database;
/**
 * @author:Sonia Päez
 * Crea la entidad
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
    @ColumnInfo(name = "longitud")
    private String longitud;
    @ColumnInfo(name = "latitud")
    private String latitud;

    public Estacion(Integer id) {
        this.id = id;
    }

    public Estacion() {

    }

    public Estacion(String nombre, String estado, String address, Integer bicisDisponibles, Integer anclajesDisponibles, String lastUpdated, String longitud, String latitud) {
        this.nombre = nombre;
        this.estado = estado;
        this.address = address;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
        this.lastUpdated = lastUpdated;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
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
                ", longitud='" + longitud + '\'' +
                ", latitud='" + latitud + '\'' +
                '}';
    }
}
