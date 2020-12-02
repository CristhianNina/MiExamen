package com.example.miexamen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    private TextView appName;
    private ImageView appImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// para que la SplasScreen se vea en pantalla completa sin la barra de arriba
        setContentView(R.layout.activity_splash);

        appName = findViewById(R.id.appName);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);
        appName.setAnimation(anim);

        appImageView = findViewById(R.id.appimageView);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        appImageView.setAnimation(anim2);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }).start();

    }
}