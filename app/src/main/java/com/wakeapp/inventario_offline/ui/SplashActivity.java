package com.wakeapp.inventario_offline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wakeapp.inventario_offline.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initTime(1000);
    }

    private void initTime(final int Time) {

        //Luego de tiempo determinado se cambia de pantalla
        //se pueden implementar tareas de inicio
        Thread timerTread = new Thread(){
            public void run(){
                try{
                    try {
                        sleep(Time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } finally {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        timerTread.start();
    }
}