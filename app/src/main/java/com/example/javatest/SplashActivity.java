package com.example.javatest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {




    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Intent i = new Intent(this, MainActivity.class);
        System.out.print("Ich habe entlich etwas zu tun, aber nachher hab ich gleich keinen Bock mehr weil ich bin ein DUmmer spasst");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.print("Diese Aufgabe wurde ausgefuehrt");
                startActivity(i);
                finish();

            }
        }, 3000);
    }


}