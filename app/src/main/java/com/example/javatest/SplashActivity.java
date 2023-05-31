package com.example.javatest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


/**
 * Die SplashActivity ist die erste Aktivität, die beim Start der Anwendung aufgerufen wird.
 * Sie zeigt ein Splash-Screen mit einem Logo an und führt dann
 * eine Weiterleitung zur Login-Aktivität durch.
 * @author Raphael Härle
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    /**
     * Diese Methode wird aufgerufen, wenn die Aktivität erstellt wird.
     * Sie initialisiert die Benutzeroberfläche und die Firebase-Authentifizierung.
     * Ein Handler wird verwendet, um einen Verzögerungseffekt für das Anzeigen des Splash-Screens zu erzeugen.
     * Nach Ablauf der Verzögerung wird die Login-Aktivität gestartet und die SplashActivity wird beendet.
     * @param savedInstanceState Der gespeicherte Zustand der Aktivität, falls vorhanden.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        // Verzögerter Start der Login-Aktivität
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start der Login-Aktivität
                startActivity(new Intent(SplashActivity.this, Login.class));
                // Beenden der SplashActivity
                finish();

            }
        }, 3000);  // Verzögerung von 3000 Millisekunden (3 Sekunden)
    }

}