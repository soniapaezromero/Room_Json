package com.example.prueba_examen_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.prueba_examen_json.ModelsJson.EstacionesBici;
import com.example.prueba_examen_json.ModelsJson.Result;
import com.example.prueba_examen_json.adaptadores.EstacionAdapter;
import com.example.prueba_examen_json.adaptadores.RecyclerTouchListener;
import com.example.prueba_examen_json.database.Estacion;
import com.example.prueba_examen_json.database.EstacionRepositorio;
import com.example.prueba_examen_json.database.add.AddEstacion;
import com.example.prueba_examen_json.database.edit.EditEstacion;
import com.example.prueba_examen_json.databinding.ActivityMainBinding;
import com.example.prueba_examen_json.networks.ApiAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public ArrayList<Result> resultados;
    public String id;
    public static List<Estacion> estacionesExistentes;
    private ActivityMainBinding binding;
    private EstacionRepositorio repositorio;
    private Estacion estacionActualizada;
    private EstacionAdapter adapter;
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    public  List<Estacion> estacionList;
    public static int ADDESTACIONREQUEST =1;
    public static int EDITESTACIONREQUEST =2;
    private boolean existe=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        obtenerEstaciones();
        //Cargo el RecyclerView
        adapter= new EstacionAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        //LLamo al View model
        mainViewModel=new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllEstaciones().observe(this, new Observer<List<Estacion>>() {
            @Override
            public void onChanged(List<Estacion> estaciones) {
                adapter.setEstaciones(estaciones);
                setEstacionList(estaciones);
            }

        });

        binding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, binding.recyclerView, new RecyclerTouchListener.ClickListener() {
            //Click Corto modifica
            @Override
            public void onClick(View view, int position) {
                Estacion estacionEditada= estacionList.get(position);
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).setPositiveButton("Sí, Cambiar", new DialogInterface.OnClickListener() {
                    /**
                     * This method will be invoked when a button in the dialog is clicked.
                     *
                     * @param dialog the dialog that received the click
                     * @param which  the button that was clicked (ex.
                     *               {@link DialogInterface#BUTTON_POSITIVE}) or the position
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, EditEstacion.class);
                        intent.putExtra(EditEstacion.EXTRA_EDITID, estacionEditada.getId());
                        Log.e("EDITMAIN",estacionEditada.getId().toString());
                        intent.putExtra(EditEstacion.EXTRA_EDITNOMBRE, estacionEditada.getNombre());
                        Log.e("EDITMAIN",estacionEditada.getNombre());
                        intent.putExtra(EditEstacion.EXTRA_EDITESTADO, estacionEditada.getEstado());
                        Log.e("EDITMAIN",estacionEditada.getEstado());
                        intent.putExtra(EditEstacion.EXTRA_EDITADRESS, estacionEditada.getAddress());
                        Log.e("EDITMAIN",estacionEditada.getAddress().toString());
                        intent.putExtra(EditEstacion.EXTRA_EDITBICIS,String.valueOf(estacionEditada.getBicisDisponibles()) );
                        Log.e("EDITMAIN",estacionEditada.getBicisDisponibles().toString());
                        intent.putExtra(EditEstacion.EXTRA_EDITANCLAJES,String.valueOf(estacionEditada.getAnclajesDisponibles()));
                        Log.e("EDITMAIN",estacionEditada.getAnclajesDisponibles().toString());
                        intent.putExtra(EditEstacion.EXTRA_EDITACTUALIZACION, estacionEditada.getLastUpdated());
                        Log.e("EDITMAIN",estacionEditada.getLastUpdated());
                        startActivityForResult(intent, EDITESTACIONREQUEST);
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .setTitle("Confirmar")
                        .setMessage("¿Modificar a la  estacion " + estacionEditada.getNombre() + "?")
                        .create();
                alertDialog.show();
            }
            //Click largo elimina
            @Override
            public void onLongClick(View view, int position) {
                final Estacion estacionEliminar= estacionList.get(position);;

                AlertDialog dialog1 = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainViewModel.Delete(estacionEliminar);
                                refrescarEstacion();
                                Toast.makeText(MainActivity.this, "Estacion eliminada", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la estacion" + estacionEliminar.getNombre() + "?")
                        .create();
                dialog1.show();

            }


        }));

        //Actualizamos el recyclerView
        refrescarEstacion();

        binding.fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v== binding.fab){
            Intent intento = new Intent(MainActivity.this, AddEstacion.class);
            startActivityForResult(intento, ADDESTACIONREQUEST);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                //petición al servidor para descargar de nuevo los sitios
                obtenerEstaciones();
                break;
            case R.id.info:
                Intent intentoinfo= new Intent(MainActivity.this, MainActivityInfo.class);
                startActivity(intentoinfo);
                break;
        }
        return true;
    }


   //En este método hacemos la llamada de la api y lo utilizamos tanto para  obtenerla como pra actualizar
    public void obtenerEstaciones(){
        Call<EstacionesBici> estacionesList = ApiAdapter.getInstance().getAllEstaciones();
        estacionesList.enqueue(new Callback<EstacionesBici>() {
            @Override
            public void onResponse(Call<EstacionesBici> call, Response<EstacionesBici> response) {
                if (response.isSuccessful()) {
                    List<EstacionesBici> estaciones = Collections.singletonList(response.body());   // pasamos  la infromacion rec
                    resultados = new ArrayList<>();
                    for (EstacionesBici e : estaciones) {
                        resultados = (ArrayList<Result>) e.getResult(); //Pasamos los datos de estaciones a una array de estaciones
                        for (Result r : resultados) {
                            Estacion estacionInsertada = new Estacion();
                            estacionInsertada.setNombre(r.getTitle());
                            estacionInsertada.setEstado(r.getEstado());
                            estacionInsertada.setAddress(r.getAddress());
                            estacionInsertada.setBicisDisponibles(r.getBicisDisponibles());
                            estacionInsertada.setAnclajesDisponibles(r.getAnclajesDisponibles());
                            estacionInsertada.setLastUpdated(r.getLastUpdated());
                            String coordenadas= r.getGeometry().getCoordinates().toString();
                            Pattern p = Pattern.compile("\\[(.*?)\\]");
                            Matcher m = p.matcher(coordenadas);
                            m.find();
                            String coordenasLimpias= m.group(1);
                            String[]partes=coordenasLimpias.split(",");
                            String longitud= partes[0];
                            String  latitud= partes[1];
                            estacionInsertada.setLongitud(longitud);
                            estacionInsertada.setLatitud(latitud);
                            List<Estacion>estacionesGuardadas= getEstacionList();
                            if(estacionesGuardadas.size() <=1 ){
                                mainViewModel.Insert(estacionInsertada);
                            }else {
                                Log.e("COMPARAR ESTACION","entra");
                                if (!Existe(estacionInsertada)) {
                                    mainViewModel.Insert(estacionInsertada);
                                }

                            }
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<EstacionesBici> call, Throwable t) {     //si falllara el OKHTTp te inforam de fallo

                mostrarError("Fallos: "+ t.getLocalizedMessage());
            }
        });

    }
    private void refrescarEstacion() {
        if (adapter == null) return;
        List<Estacion> estaciones= getEstacionList();
        adapter.setEstaciones(estaciones);
        adapter.notifyDataSetChanged();
    }


    public List<Estacion> getEstacionList() {
        return estacionList;
    }

    public void setEstacionList(List<Estacion> estacionList) {
        this.estacionList = estacionList;
    }


    private void mostrarError(String s) {
        Looper.prepare();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
    private void mostrarMensaje(String s) {
        Looper.prepare();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resibimos datos de la Clase ADD
        if (requestCode == ADDESTACIONREQUEST && resultCode == RESULT_OK) {// Si sale bien el intent recogemos los datos
            String nombre = data.getStringExtra(AddEstacion.EXTRA_ADDNOMBRE);
            String estado = data.getStringExtra(AddEstacion.EXTRA_ADDESTADO);
            String adress = data.getStringExtra(AddEstacion.EXTRA_ADDADRESS);
            String bicis = data.getStringExtra(AddEstacion.EXTRA_ADDBICIS);
            String anclajes = data.getStringExtra(AddEstacion.EXTRA_ADDANCLAJES);
            String actualizacion = data.getStringExtra(AddEstacion.EXTRA_ADDACTUALIZACION);

               Estacion estacionAdd = new Estacion();
            estacionAdd.setNombre(nombre);
            estacionAdd.setEstado(estado);
            estacionAdd.setAddress(adress);
            estacionAdd.setBicisDisponibles(Integer.valueOf(bicis));
            estacionAdd.setAnclajesDisponibles(Integer.valueOf(anclajes));
            estacionAdd.setLastUpdated(actualizacion);
            if (!Existe( estacionAdd)) {
                mainViewModel.Insert( estacionAdd);
            }




        } else if (requestCode == EDITESTACIONREQUEST && resultCode == RESULT_OK) {// confirmamos los datos de lamodificacion y si lo cumple modificamoe el registro
            Integer id = data.getIntExtra(EditEstacion.EXTRA_EDITID, -1);
            if (id == -1) {
                Toast.makeText(this, "Paseo no se puede modificar",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String nombreEdit = data.getStringExtra(EditEstacion.EXTRA_EDITNOMBRE);
            String estadoEdit = data.getStringExtra(EditEstacion.EXTRA_EDITNOMBRE);
            String adressEdit = data.getStringExtra(EditEstacion.EXTRA_EDITADRESS);
            String bicisEdit = data.getStringExtra(EditEstacion.EXTRA_EDITBICIS);
            String anclajesEdit = data.getStringExtra(EditEstacion.EXTRA_EDITANCLAJES);
            String actualizacionEdit = data.getStringExtra(EditEstacion.EXTRA_EDITACTUALIZACION);
            boolean existe=false;
            int estacionexiste=0;
            List<Estacion> estacionesexistentes= getEstacionList();
            for(Estacion e:estacionesexistentes){
                if((e.getNombre().equals(nombreEdit))&&(e.getEstado().equals(estadoEdit))&&(String.valueOf(e.getAddress()).equals(adressEdit))) {
                    existe=true;
                    estacionexiste++;
                }
            }

            Estacion estacionActualizada = new Estacion();
                estacionActualizada.setNombre(nombreEdit);
                estacionActualizada.setEstado(estadoEdit);
                estacionActualizada.setAddress(adressEdit);
                estacionActualizada.setBicisDisponibles(Integer.valueOf(bicisEdit));
                estacionActualizada.setAnclajesDisponibles(Integer.valueOf(anclajesEdit));
                estacionActualizada.setLastUpdated(actualizacionEdit);
            if (!Existe(estacionActualizada)) {
                mainViewModel.Update(estacionActualizada);

            }

        }

        }

    /**
     * Metodo para comparar si esxiste ya el registro
     * @param estacion
     * @return
     */

    public boolean Existe(Estacion estacion){
        List <Estacion> estacionLista= new ArrayList<>();
        estacionLista= getEstacionList();

        for(Estacion e:estacionLista){
            Log.e("ESTACIONESGUARDADAS",e.toString());
            if((e.getNombre().equals(estacion.getNombre()))&&(e.getEstado().equals(estacion.getEstado()))&&(e.getAddress().equals(estacion.getAddress()))){
                existe=true;
                Log.e( "BOOLEAN","true");
            }
        }
        return existe;
    }


}


