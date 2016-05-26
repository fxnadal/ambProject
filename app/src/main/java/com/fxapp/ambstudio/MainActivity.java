package com.fxapp.ambstudio;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

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
                ListaFragment listaF=new ListaFragment();

                if (findViewById(R.id.fragment_container_main) != null) {

                    //Capturamos el cargador dinamico
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    //Reemplazamos la noticia
                    transaction.replace(R.id.fragment_container_main, listaF);
                    transaction.addToBackStack(null);

                    //Realizamos el reemplazo
                    transaction.commit();
                } else {

                    Intent llamada = new Intent(MainActivity.this, Main2Activity.class);

                    startActivity(llamada);

                }
            }
        });

        final Button boton2 = (Button) findViewById(R.id.bMenu2);
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        final Button boton3 = (Button) findViewById(R.id.bMenu3);
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }
}
