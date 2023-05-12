package com.example.javatest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;


public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //if (auth.getCurrentUser() != null) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    //i.putExtra("auth", auth.getCurrentUser());
                    startActivity(i);
               // }else {
                 //   startActivity(new Intent(SplashActivity.this, Login.class));
               // }
                finish();

            }
        }, 3000);
    }

}