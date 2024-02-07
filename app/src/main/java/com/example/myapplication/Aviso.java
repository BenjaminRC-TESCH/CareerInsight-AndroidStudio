package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Aviso extends AppCompatActivity {

    TextView aviso;
    Switch acepto;
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);

        aviso = findViewById(R.id.aviso);
        aviso.setMovementMethod(new ScrollingMovementMethod());

        acepto = findViewById(R.id.acepto);
        iniciar = findViewById(R.id.iniciar);


        //comienza el estado del boton en desactivado
        iniciar.setEnabled(false);

        // Agrega un listener al Switch
        acepto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Habilita o deshabilita el botón según el estado del Switch
                iniciar.setEnabled(isChecked);
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Aviso.this, Datos.class);
                startActivity(i);
            }
        });
    }
}
