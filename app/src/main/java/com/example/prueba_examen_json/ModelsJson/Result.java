
package com.example.prueba_examen_json.ModelsJson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("bicisDisponibles")
    @Expose
    private Integer bicisDisponibles;
    @SerializedName("anclajesDisponibles")
    @Expose
    private Integer anclajesDisponibles;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param lastUpdated
     * @param estado
     * @param address
     * @param id
     * @param title
     * @param anclajesDisponibles
     * @param bicisDisponibles
     */
    public Result(String id, String title, String estado, String address, Integer bicisDisponibles, Integer anclajesDisponibles, String lastUpdated) {
        super();
        this.id = id;
        this.title = title;
        this.estado = estado;
        this.address = address;
        this.bicisDisponibles = bicisDisponibles;
        this.anclajesDisponibles = anclajesDisponibles;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Result withId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Result withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Result withEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Result withAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getBicisDisponibles() {
        return bicisDisponibles;
    }

    public void setBicisDisponibles(Integer bicisDisponibles) {
        this.bicisDisponibles = bicisDisponibles;
    }

    public Result withBicisDisponibles(Integer bicisDisponibles) {
        this.bicisDisponibles = bicisDisponibles;
        return this;
    }

    public Integer getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

    public void setAnclajesDisponibles(Integer anclajesDisponibles) {
        this.anclajesDisponibles = anclajesDisponibles;
    }

    public Result withAnclajesDisponibles(Integer anclajesDisponibles) {
        this.anclajesDisponibles = anclajesDisponibles;
        return this;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Result withLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

}
