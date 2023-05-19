package com.example.javatest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.net.URL;


public class Profil extends AppCompatActivity {


    private TextView userName, userMail;
    private ImageView userPhoto;
    private final int YOUR_RADIUS_VALUE = 165;
    private static final String TAG = "Profil";
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

        userPhoto.setClipToOutline(true);
        userPhoto.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), YOUR_RADIUS_VALUE);
            }
        });

        downloadProfileImage(this,photo, userPhoto );

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Profil.this, Login.class);
                startActivity(i);
            }
        });


    }


    public static void downloadProfileImage(Context c, String imageUrl, final ImageView imageView ) {
        RequestQueue rq = Volley.newRequestQueue(c);
        ImageRequest ir = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
                Log.d(TAG, "Set Image is done");
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.w("Set Image is faild", e);
            }
        });

        rq.add(ir);
    }
}