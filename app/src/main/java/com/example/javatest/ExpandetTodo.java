package com.example.javatest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.javatest.Actions.EditTodo;
import com.example.javatest.Actions.Share;
import com.example.javatest.databinding.ActivityExpandetTodoBinding;

/**
 * Diese Aktivität zeigt eine erweiterte Ansicht für eine einzelne Aufgabe an.
 * @author Raphael Härle
 * @version 1.0
 */
public class ExpandetTodo extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityExpandetTodoBinding binding;

    private TextView taskName, taskBeschreibung, taskDate, taskAutor, taskKey;

    private Button share, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandet_todo);

        taskName = findViewById(R.id.aufgabenName);
        taskBeschreibung = findViewById(R.id.aufgabenBeschreibung);
        taskDate = findViewById(R.id.aufgabenEndDate);
        taskAutor = findViewById(R.id.aufgabenAutor);
        taskKey = findViewById(R.id.key);
        share = findViewById(R.id.share);
        edit = findViewById(R.id.edit);

        taskName.setText(getIntent().getStringExtra("NAME"));
        taskBeschreibung.setText(getIntent().getStringExtra("BESCHREIBUNG"));
        taskDate.setText(getIntent().getStringExtra("DATE"));
        taskAutor.setText(getIntent().getStringExtra("AUTOR"));
        taskKey.setText(getIntent().getStringExtra("KEY"));

        /**
         * Diese Methode wird aufgerufen, wenn der "Teilen"-Button geklickt wird.
         * Sie öffnet das Teilen-Dialogfenster.
         */
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.newInstance().show(getSupportFragmentManager(), Share.TAG);
            }
        });

        /**
         * Diese Methode wird aufgerufen, wenn der "Bearbeiten"-Button geklickt wird.
         * Sie öffnet das Dialogfenster zum Bearbeiten der Aufgabe.
         */
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTodo.newInstance().show(getSupportFragmentManager(), Share.TAG);
            }
        });
    }
}


