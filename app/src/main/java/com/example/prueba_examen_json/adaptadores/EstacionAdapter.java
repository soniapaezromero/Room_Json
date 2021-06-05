package com.example.prueba_examen_json.adaptadores;
/**
 * @author:Sonia Päez Creado el:25/04/2021
 * Adaptador del REcycler view que nos muestr nombre del jinete,FEcha y hora
 */
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import com.example.prueba_examen_json.R;
import com.example.prueba_examen_json.database.Estacion;


import java.util.ArrayList;
import java.util.List;

public class EstacionAdapter extends RecyclerView.Adapter<EstacionAdapter.MyViewHolder> {
    private  Context context;
    public List<Estacion> listaestaciones;

    public EstacionAdapter(){
        this.listaestaciones = new ArrayList<>();
    }

    public EstacionAdapter(Context context, List<Estacion> estacionList) {
        this.context = context;
        this.listaestaciones = estacionList;
    }

    public void setEstaciones(List<Estacion> estaciones){
        listaestaciones = estaciones;
        notifyDataSetChanged();
    }
    public Estacion getEstacionAt(int position) {
        return listaestaciones.get(position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, nombre,bicis,anclajes;



        public MyViewHolder(View itemview) {
            super(itemview);
            this.nombre = itemview.findViewById(R.id.nombre);
            this.id =itemview.findViewById(R.id.id);
            this.bicis=itemview.findViewById(R.id.bicis);
            this.anclajes=itemview.findViewById(R.id.anclajes);




        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(listaestaciones !=null) {
            Estacion estacion = listaestaciones.get(position);
            holder.nombre.setText(estacion.getNombre().toString());
            holder.id.setText(estacion.getId().toString());
            holder.bicis.setText(estacion.getBicisDisponibles().toString());
            holder.anclajes.setText(estacion.getAnclajesDisponibles().toString());





        }else{
            holder.nombre.setText("No hay ninguna mascota");
        }

    }

    @Override
    public int getItemCount() {
        if(listaestaciones != null) {
            return listaestaciones.size();
        }else{
            return 0;
        }
    }


}
