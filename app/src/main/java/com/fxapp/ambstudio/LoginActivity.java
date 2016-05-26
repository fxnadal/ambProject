package com.fxapp.ambstudio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by fx on 22/04/2016.
 */
public class LoginActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            final Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on clickVamos a programar nuestra llamada a la segunda ventana
                    Intent llamadaMenu = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(llamadaMenu);
                }
            });
        }


    }
