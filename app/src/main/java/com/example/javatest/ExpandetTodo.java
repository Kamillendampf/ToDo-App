package com.example.javatest;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.javatest.Actions.Share;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.javatest.databinding.ActivityExpandetTodoBinding;

public class ExpandetTodo extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityExpandetTodoBinding binding;

    private TextView taskName, taskBeschreibung, taskDate, taskAutor;
    private Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandet_todo);

        taskName = findViewById(R.id.aufgabenName);
        taskBeschreibung = findViewById(R.id.aufgabenBeschreibung);
        taskDate = findViewById(R.id.aufgabenEndDate);
        taskAutor = findViewById(R.id.aufgabenAutor);

        share = findViewById(R.id.share);

        taskName.setText(getIntent().getStringExtra("NAME"));
        taskBeschreibung.setText(getIntent().getStringExtra("BESCHREIBUNG"));
        taskDate.setText(getIntent().getStringExtra("DATE"));
        taskAutor.setText(getIntent().getStringExtra("AUTOR"));


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Share.newInstance().show(getSupportFragmentManager(), Share.TAG);

            }
        });




    }



}