package com.fxapp.ambstudio;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements ListaFragment.ListFragmentListener,CrearInformeFragment.OnFragmentInteractionListener {

    private MyDBInformesAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container_main) !=null){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        final Button boton1 = (Button) findViewById(R.id.bMenu1);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id=1;
                ListaFragment listaFragment=new ListaFragment();
                //Obtener el arrayList
                ArrayList arrayList=rellenarArrayListListView();
                //Abrir fragment o activity segun dispositivo
                if (findViewById(R.id.fragment_container_main) != null) {
                    //Pasamos el arrayList al fragment
                    Bundle args=new Bundle();
                    args.putStringArrayList(listaFragment.ARRAYLIST,arrayList);
                    listaFragment.setArguments(args);
                    //Capturamos el cargador dinamico
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    //Reemplazamos la noticia
                    transaction.replace(R.id.fragment_container_main, listaFragment);
                    transaction.addToBackStack(null);
                    //Realizamos el reemplazo
                    transaction.commit();
                } else {
                    Intent llamada = new Intent(MainActivity.this, Main2Activity.class);
                    llamada.putExtra("id", id);
                    llamada.putStringArrayListExtra("arrayList", arrayList);//Pasar la lista al activity para que lo pase al fragment
                    startActivity(llamada);
                }
            //}
            }
        });

        final Button boton2 = (Button) findViewById(R.id.bMenu2);
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id=2;
                CrearInformeFragment informe=new CrearInformeFragment();
                if (findViewById(R.id.fragment_container_main) != null) {
                    //Capturamos el cargador dinamico
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    //Reemplazamos la noticia
                    transaction.replace(R.id.fragment_container_main, informe);
                    transaction.addToBackStack(null);
                    //Realizamos el reemplazo
                    transaction.commit();
                } else {
                    Intent llamada = new Intent(MainActivity.this, Main2Activity.class);
                    llamada.putExtra("id", id);
                    startActivity(llamada);
                }
            }
        });

        final Button boton3 = (Button) findViewById(R.id.bMenu3);
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id=3;

            }
        });

    }

    //
    //METODOS BASE DE DATOS
    //

    //Metodo para sacar el arraylist de nombres fecha e id para el listView
    public ArrayList rellenarArrayListListView(){
        dbAdapter=new MyDBInformesAdapter(this);
        dbAdapter.open();
        ArrayList<String> arrayList = dbAdapter.recuperarListaListViewInformes();
        System.err.println("la lista va vacia desde la base de datos........?..................." + arrayList.isEmpty());
        return arrayList;
    }

    @Override
    public void onListSelected(int position) {
        System.out.println("..." + position);
        int idF=4;
        //Se saca la consulta del informe de la base de datos
        dbAdapter=new MyDBInformesAdapter(this);
        dbAdapter.open();
        String informeTrabajador=dbAdapter.recuperarTrabajador(position);
        String informeNumObra=dbAdapter.recuperarNumObra(position);
        String informeFecha=dbAdapter.recuperarFecha(position);
        String informeDuracion=dbAdapter.recuperarDuracion(position);
        String informeLocalizacion=dbAdapter.recuperarLocalizacion(position);
        String informeHoraInicio=dbAdapter.recuperarHoraInicio(position);
        String informeHoraFin=dbAdapter.recuperarHoraFin(position);

        if (findViewById(R.id.fragment_container_main) != null) {
            InformeFragment informeFragment=new InformeFragment();
            Bundle args=new Bundle();
            args.putString(informeFragment.ARG_TRABAJADOR, informeTrabajador);
            args.putString(informeFragment.ARG_NUM_OBRA, informeNumObra);
            args.putString(informeFragment.ARG_FECHA_OBRA, informeFecha);
            args.putString(informeFragment.ARG_DURACION, informeDuracion);
            args.putString(informeFragment.ARG_LOCALIZACION, informeLocalizacion);
            args.putString(informeFragment.ARG_HORA_INICIO, informeHoraInicio);
            args.putString(informeFragment.ARG_HORA_FIN, informeHoraFin);
            informeFragment.setArguments(args);
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main, informeFragment);
            transaction.addToBackStack(null);
            //Realizamos el reemplazo
            transaction.commit();
        } else {
            //Pasar los datos al activity para que lo pase al fragment
            Intent llamada = new Intent(MainActivity.this, Main2Activity.class);
            llamada.putExtra("id", idF);
            llamada.putExtra("informeTrabajador", informeTrabajador);
            llamada.putExtra("informeNumObra", informeNumObra);
            llamada.putExtra("informeFecha", informeFecha);
            llamada.putExtra("informeDuracion", informeDuracion);
            llamada.putExtra("informeLocalizacion", informeLocalizacion);
            llamada.putExtra("informeHoraInicio", informeHoraInicio);
            llamada.putExtra("informeHoraFin", informeHoraFin);
            //llamada.putStringArrayListExtra("arrayList", arrayList);//Pasar la lista al activity para que lo pase al fragment
            startActivity(llamada);
        }
    }


    @Override
    public void onFragmentInteraction(String trabajador, String fecha, String duracion, String localizacion, String horaInicio, String horaFin) {
        dbAdapter=new MyDBInformesAdapter(this);
        dbAdapter.open();
        //INSERT PARA PROBAR EN OTRO DISPOSITIVO
        dbAdapter.insertarInforme(trabajador, fecha, duracion, localizacion, horaInicio, horaFin);
    }
}
