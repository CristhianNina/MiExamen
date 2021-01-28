package com.example.miexamen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    Button btnjugar,btnrespuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_inicio);

        btnjugar=(Button)findViewById(R.id.jugar);
        btnrespuestas=(Button)findViewById(R.id.respuestas);


        btnjugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pagina2= new Intent(Inicio.this,Jugar.class);
                startActivity(pagina2);
            }
        });
        btnrespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagina3= new Intent(Inicio.this,Respuestas.class);
                startActivity(pagina3);
            }
        });

    }
}
