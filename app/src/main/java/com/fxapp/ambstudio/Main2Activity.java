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

public class Main2Activity extends Activity implements ListaFragment.ListFragmentListener{

    private int idFragment;
    private ArrayList<String> arrayList;
    private MyDBInformesAdapter dbAdapter;
    Bundle savedInstanceState2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState2=savedInstanceState;
        setContentView(R.layout.activity_main2);
        //Recuperar datos del intent
        Bundle extras=getIntent().getExtras();
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
            if(findViewById(R.id.fragment_container_main2)!=null) {
                if(savedInstanceState2 != null){
                    return;
                }
                //Cargamos el fragment del texto
                InformeFragment fragment = new InformeFragment();
                //Capturamos el cargador dinamico
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //Reemplazamos la noticia
                transaction.replace(R.id.fragment_container_main2, fragment);
                transaction.addToBackStack(null);
                //Realizamos el reemplazo
                transaction.commit();
            }
        }

    }
    public void onListSelected(int position){
        if(findViewById(R.id.fragment_container_main2)!=null) {
            if(savedInstanceState2 != null){
                return;
            }
            //Recuperamos el indorme
            dbAdapter=new MyDBInformesAdapter(this);
            dbAdapter.open();


            ArrayList aL=dbAdapter.recuperarInforme(position);
            System.out.println("........................................."+aL.size());

            //Guardamos cada columna en su variable
            String textTrabajador = dbAdapter.recuperarTrabajador(position);
            String textFecha =dbAdapter.recuperarFecha(position);
            String textDuracion =dbAdapter.recuperarDuracion(position);
            String textNumObra =dbAdapter.recuperarNumObra(position);
            String textLocalizacion =dbAdapter.recuperarLocalizacion(position);
            String textHoraInicio =dbAdapter.recuperarHoraInicio(position);
            String textHoraFin =dbAdapter.recuperarHoraFin(position);


            //Cargamos el fragment del informe
            InformeFragment fragment = new InformeFragment();
            //Pasamos los datos del informe
            Bundle args=new Bundle();
            args.putString(fragment.ARG_TRABAJADOR, textTrabajador);
            args.putString(fragment.ARG_FECHA_OBRA, textFecha);
            args.putString(fragment.ARG_DURACION, textDuracion);
            args.putString(fragment.ARG_NUM_OBRA, textNumObra);
            args.putString(fragment.ARG_LOCALIZACION, textLocalizacion);
            args.putString(fragment.ARG_HORA_INICIO, textHoraInicio);
            args.putString(fragment.ARG_HORA_FIN, textHoraFin);
            fragment.setArguments(args);
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main2, fragment);
            transaction.addToBackStack(null);
            //Realizamos el reemplazo
            transaction.commit();
        }
    }


}
