
package com.example.prueba_examen_json.ModelsJson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstacionesBici {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;


    public EstacionesBici() {
    }

    /**
     * 
     * @param result
     */
    public EstacionesBici(List<Result> result) {
        super();
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public EstacionesBici withResult(List<Result> result) {
        this.result = result;
        return this;
    }

}
