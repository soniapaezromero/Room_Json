package com.example.prueba_examen_json.networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sonia Paez 15/04/2021
 *Clase que se descarga la información de la Api a traves de Retrofit
 */

public class ApiAdapter {
    private static ApiService API_SERVICE;


    public static synchronized ApiService getInstance() {

        if (API_SERVICE == null) {
            //HtppLoggingInterceptor nos muestra en terminal los fallos que se puedadn producir en el OKHTTp y el documento descargado
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            // CRreamos documento
            Gson gson = new GsonBuilder().create();
            // Creamos el retrofit y nos descargamos el docuemento
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.zaragoza.es/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
//Creamos el Servico de Api
            API_SERVICE = retrofit.create(ApiService.class);
        }
        return API_SERVICE;

    }
}
