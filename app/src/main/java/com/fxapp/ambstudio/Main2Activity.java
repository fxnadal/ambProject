package com.fxapp.ambstudio;

/**
 * Created by fx on 10/06/2016.
 */
import android.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends Activity implements CrearInformeFragment.OnFragmentInteractionListener,ListaFragment.ListFragmentListener{

    private int idFragment;
    private ArrayList<String> arrayList;
    private MyDBInformesAdapter dbAdapter;
    private Bundle bundle;

    String informeTrabajador;
    String informeNumObra;
    String informeFecha;
    String informeDuracion;
    String informeLocalizacion;
    String informeHoraInicio;
    String informeHoraFin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Recuperar datos del intent
        Bundle extras=getIntent().getExtras();
        bundle=extras;
        idFragment=extras.getInt("id");//Poner el fragment segun el botónn
        arrayList=extras.getStringArrayList("arrayList");//Poner el arrayList para mostrar la lista
        //EJECUTAMOS CODIGO SEGUN EL FRAGMENT
        seleccionarFragment();

    }

    public void seleccionarFragment(){
        if(idFragment==1){
            ListaFragment listaFragment=new ListaFragment();
            //Pasamos el arrayList al fragment
            Bundle args=new Bundle();
            System.out.println("Esta vacia?...................................................." + arrayList.isEmpty());
            args.putStringArrayList(listaFragment.ARRAYLIST,arrayList);
            listaFragment.setArguments(args);
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main2, listaFragment);
            transaction.addToBackStack(null);
            //Realizamos el reemplazo
            transaction.commit();

        }
        if(idFragment==2){
            //Cargamos el fragment del texto
            CrearInformeFragment fragment = new CrearInformeFragment();
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main2, fragment);
            transaction.addToBackStack(null);
            //Realizamos el reemplazo
            transaction.commit();
        }
        if(idFragment==3){

        }
        if(idFragment==4){
            //Cargamos el fragment del texto
            InformeFragment informeFragment = new InformeFragment();
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main2, informeFragment);
            transaction.addToBackStack(null);
            //Pasamos los datos al fragment
            Bundle args=new Bundle();
            args.putString(informeFragment.ARG_TRABAJADOR, informeTrabajador);
            args.putString(informeFragment.ARG_NUM_OBRA, informeNumObra);
            args.putString(informeFragment.ARG_FECHA_OBRA, informeFecha);
            args.putString(informeFragment.ARG_DURACION, informeDuracion);
            args.putString(informeFragment.ARG_LOCALIZACION, informeLocalizacion);
            args.putString(informeFragment.ARG_HORA_INICIO, informeHoraInicio);
            args.putString(informeFragment.ARG_HORA_FIN, informeHoraFin);
            informeFragment.setArguments(args);

            //Realizamos el reemplazo
            transaction.commit();
        }

    }


    @Override
    public void onListSelected(int position) {
        System.out.println("..." + position);

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
        transaction.replace(R.id.fragment_container_main2, informeFragment);
        transaction.addToBackStack(null);
        //Realizamos el reemplazo
        transaction.commit();
    }
    @Override
    public void onFragmentInteraction(String trabajador, String fecha, String duracion, String localizacion, String horaInicio, String horaFin) {
        dbAdapter=new MyDBInformesAdapter(this);
        dbAdapter.open();
        dbAdapter.insertarInforme(trabajador, fecha, duracion, localizacion, horaInicio, horaFin);
        System.out.println("...............................INFORMACIÓN INTRODUCIDA!!!!!!!!!!!!");
    }
}
