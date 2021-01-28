package com.example.miexamen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Jugar extends AppCompatActivity {
    TextView puntos,contador,vidas,txtcorrecto,txtincorrecto;
    ImageView imagen;
    EditText txtedit;
    Button btnconfirmar;
    String[] nombre_pers={"habilidades","blandas","carnage","daredevil","deadpool","destroyer","doom","falcon","galactus","gambit","ghost rider","groot","iron man","magneto","mysterio","punisher","red hulk","she hulk","spider man","storm","thanos","thor","ultron","venom","vision","wolverine","apocalypse"};
    String[] nombre_pers2={"habilidades","blandas","carnage","daredevil","dead pool","destroyer","doom","falcon","galactus","gambit","ghostrider","groot","ironman","magneto","mysterio","punisher","red hulk","she hulk","spiderman","storm","thanos","thor","ultron","venom","vision","wolverine","apocalypse"};
    String[] imagen_pers={"img1","img2","img3","img4","img5","img6","img7","img8","img9","img10","img11","img12","img13","img14","img15","img16","img17","img18","img19","img20","img21","img22","img23","img24","img25","img26","img27"};
    int intpunto=0;
    int intvidas=3;
    int numerogenerado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        txtcorrecto=(TextView)findViewById(R.id.txtcorrecto);
        txtincorrecto=(TextView)findViewById(R.id.txtincorrecto);

        puntos=(TextView)findViewById(R.id.Puntos);
        vidas=(TextView)findViewById(R.id.vidas);
        contador=(TextView)findViewById(R.id.Cuenta);
        imagen=(ImageView)findViewById(R.id.imagen);
        txtedit=(EditText)findViewById(R.id.txtedit);
        btnconfirmar=(Button)findViewById(R.id.btnconfirmar);

        establecer_imagen(numerogenerado);

        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoconfirmar= txtedit.getText().toString().toLowerCase();
                if(textoconfirmar.equals(nombre_pers[numerogenerado])|textoconfirmar.equals(nombre_pers2[numerogenerado])){
                    txtcorrecto.setVisibility(View.VISIBLE);
                    intpunto=intpunto+1;
                    puntos.setText("Puntos: "+ intpunto);
                    esperar1();
                }
                else {
                    txtincorrecto.setVisibility(View.VISIBLE);
                    intvidas=intvidas-1;
                    vidas.setText("Vidas: "+ intvidas);
                    esperar2();
                }
                if(intvidas==0){
                    finish();
                }
            }
        });
    }
    void esperar2() {
        new CountDownTimer(2000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                txtincorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el personaje");
            }
        }.start();
    }
    void esperar1(){
        new CountDownTimer(4000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador.setText(""+(millisUntilFinished/1000 +1));
                btnconfirmar.setVisibility(View.GONE);
            }
            @Override
            public void onFinish() {
                btnconfirmar.setVisibility(View.VISIBLE);
                numerogenerado=numerogenerado+1;
                contador.setText("");
                establecer_imagen(numerogenerado);
                txtcorrecto.setVisibility(View.INVISIBLE);
                txtedit.setText("");
                txtedit.setHint("Ingrese el personaje");
            }
        }.start();
    }
    void establecer_imagen(int numero){
        int id = getResources().getIdentifier(imagen_pers[numero],"mipmap",getPackageName());
        imagen.setImageResource(id);
    }
}
