package com.example.javatest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.net.URL;


public class Profil extends AppCompatActivity {


    private TextView userName, userMail;
    private ImageView userPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String photo = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());

        userName = findViewById(R.id.name);
        userMail = findViewById(R.id.email);
        userPhoto = findViewById(R.id.photo);

        userName.setText(name);
        userMail.setText(email);

       userPhoto.setImageURI(Uri.parse(photo));



        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Profil.this, Login.class);
                startActivity(i);
            }
        });


    }


}