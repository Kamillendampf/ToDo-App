package com.example.javatest;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Diese Klasse repräsentiert die Login-Aktivität, in der Benutzer sich mit ihrem Google-Konto anmelden können.
 * Die Klasse verwaltet die Authentifizierung mit Firebase und die Verwendung des Google Sign-In-Dienstes.
 * Nach erfolgreichem Login wird der Benutzer zur Hauptaktivität weitergeleitet.
 *
 * @author Raphael Härle
 * @version 1.0
 */
public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private  FirebaseUser currentUser;
    private boolean showOneTapUI = true;
    private static final String TAG = "Login";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;


    /**
     * Diese Methode wird aufgerufen, wenn die Aktivität erstellt wird.
     * Sie initialisiert die Ansicht, konfiguriert die Google-Anmeldung und den Firebase-Authentifizierungsmechanismus.
     *
     * @param savedInstanceState Der gespeicherte Zustand der Aktivität.
     */
	@SuppressLint("MissingInflatedId")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //"922368994691-ikote44cqk5mmg1ksuscje4tk214a87h.apps.googleusercontent.com"

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("922368994691-ikote44cqk5mmg1ksuscje4tk214a87h.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Init Firebase auth
        mAuth = FirebaseAuth.getInstance();



        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signIn();
            }
        });

    }

    /**
     * Diese Methode wird aufgerufen, wenn die Aktivität gestartet wird.
     * Sie überprüft, ob der Benutzer bereits angemeldet ist, und aktualisiert die Benutzeroberfläche entsprechend.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Diese Methode wird aufgerufen, wenn das Ergebnis einer Aktivität zurückgegeben wird.
     * Sie verarbeitet das Ergebnis des Google Sign-In-Vorgangs und authentifiziert den Benutzer mit Firebase.
     *
     * @param requestCode Der Anforderungscode, der bei der Aktivitätsstartanforderung angegeben wurde.
     * @param resultCode  Der Ergebniscode, der von der aufgerufenen Aktivität zurückgegeben wurde.
     * @param data        Die Daten, die von der aufgerufenen Aktivität zurückgegeben wurden.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    /**
     * Diese Methode authentifiziert den Benutzer mit Firebase mithilfe des Google-Anmeldeinformationen.
     *
     * @param idToken Das Google-ID-Token des Benutzers.
     */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    /**
     * Diese Methode startet den Google Sign-In-Vorgang.
     * Sie zeigt das Google-Anmeldefenster an und erwartet das Ergebnis.
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    /**
     * Diese Methode aktualisiert die Benutzeroberfläche entsprechend dem angemeldeten Benutzer.
     * Wenn ein Benutzer angemeldet ist, wird sein Profil aktualisiert und er wird zur Hauptaktivität weitergeleitet.
     * Wenn kein Benutzer angemeldet ist, wird die Benutzeroberfläche entsprechend angepasst.
     *
     * @param user Der angemeldete Firebase-Benutzer.
     */
    private void updateUI(FirebaseUser user) {
        TextView tv = findViewById(R.id.text_login);
        if (user != null){
             UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(user.getDisplayName())
                    .setPhotoUri(user.getPhotoUrl())
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User profile updated.");
                            }
                        }
                    });
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        tv.setText(user.getEmail());
        }else {



        }

    }


}