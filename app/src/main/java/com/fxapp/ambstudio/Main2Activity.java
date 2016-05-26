package com.fxapp.ambstudio;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Cargamos el fragment del texto
        ListaFragment listaFragment = new ListaFragment();

        //Capturamos el cargador dinamico
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //Reemplazamos la noticia
        transaction.replace(R.id.fragment_container_main2, listaFragment);
        transaction.addToBackStack(null);

        //Realizamos el reemplazo
        transaction.commit();


    }
}
