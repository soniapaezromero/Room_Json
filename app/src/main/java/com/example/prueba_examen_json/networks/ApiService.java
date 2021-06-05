package com.example.prueba_examen_json.networks;



import com.example.prueba_examen_json.ModelsJson.EstacionesBici;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @Headers({"Accept: application/json"})// Al indicarle este encabezado nos aseguramos que el documento descrgado sea Json
    // Le solicitamos la informacion a la api de  todas las estaciones actualiza cada 24 horas utilizo el json de estacipone bicis que es ñla que actualiza cada  cada 2 minutos

    @GET("sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json?rf=html&start=0&rows=130")
    Call<EstacionesBici> getAllEstaciones();
}
