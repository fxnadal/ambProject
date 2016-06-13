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

public class MainActivity extends Activity  {

    private ListaFragment listaFragment=new ListaFragment();
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
                //if(){
                //Obtener el arrayList
                ArrayList arrayList=rellenarArrayListListView();
                //Abrir fragment o activity segun dispositivo
                if (findViewById(R.id.fragment_container_main) != null) {
                    //Pasamos el arrayList al fragment
                    Bundle args=new Bundle();
                    System.out.println("Esta vacia?...................................................."+arrayList.isEmpty());
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
        //INSERT PARA PROBAR EN OTRO DISPOSITIVO
        /*dbAdapter.insertarInforme("trabajador", "fecha", "duracion", "localizacion", "hora inicio", "hora final");
        dbAdapter.insertarInforme("trabajador","fecha","duracion","localizacion","hora inicio","hora final");
        dbAdapter.insertarInforme("trabajador","fecha","duracion","localizacion","hora inicio","hora final");
        dbAdapter.insertarInforme("trabajador","fecha","duracion","localizacion","hora inicio","hora final");
        dbAdapter.insertarInforme("a2","b2","c2","d2","e2","f2");
        dbAdapter.insertarInforme("a3","b3","c3","d3","e3","f3");*/
        ArrayList<String> arrayList = dbAdapter.recuperarListaListViewInformes();
        System.err.println("la lista va vacia desde la base de datos........?..................." + arrayList.isEmpty());
        return arrayList;

    }

    public void mostrarInforme(){
        int idF=4;
        if (findViewById(R.id.fragment_container_main) != null) {
            InformeFragment informeFragment=new InformeFragment();
            /*/Pasamos el arrayList al fragment
            Bundle args=new Bundle();
            System.out.println("Esta vacia?...................................................."+arrayList.isEmpty());
            args.putStringArrayList(listaFragment.ARRAYLIST,arrayList);
            listaFragment.setArguments(args);*/
            //Capturamos el cargador dinamico
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //Reemplazamos la noticia
            transaction.replace(R.id.fragment_container_main, informeFragment);
            transaction.addToBackStack(null);
            //Realizamos el reemplazo
            transaction.commit();
        } else {

            Intent llamada = new Intent(MainActivity.this, Main2Activity.class);
            llamada.putExtra("id", idF);
            //llamada.putStringArrayListExtra("arrayList", arrayList);//Pasar la lista al activity para que lo pase al fragment
            startActivity(llamada);
        }
    }





}
