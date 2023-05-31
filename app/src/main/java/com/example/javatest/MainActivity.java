package com.example.javatest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.Actions.AddTask;
import com.example.javatest.Addapter.Helper.TaskAdapterHelper;
import com.example.javatest.Addapter.TaskAdapter;
import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.interfaces.ViewTodoBody;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Die Klasse MainActivity ist die Hauptaktivität der Anwendung. Sie verwaltet die Benutzeroberfläche,
 * die Interaktionen mit dem Benutzer und die Anzeige der Aufgabenliste.
 * @author Raphael Härle
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements ViewTodoBody {


    private TextView pageName;
    private ArrayList<TodoModuls> tasklist = new ArrayList<>();
    private ImageButton create;
    private ImageButton profil;
    private DAOtodo daoTodo;
    private TaskAdapter ta;


    /**
     * Diese Methode wird beim Erstellen der Aktivität aufgerufen.
     * Sie initialisiert die Benutzeroberfläche, den TaskAdapter und die TaskListe.
     * @param savedInstanceState Der gespeicherte Zustand der Aktivität, falls vorhanden.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialisierung der DAOtodo Instanz
        daoTodo = new DAOtodo();


        // Festlegen des Layouts für die Aktivität
        setContentView(R.layout.activity_main);

        // Initialisierung der Ansichtselemente
        RecyclerView rv = findViewById(R.id.tasks);
        create = findViewById(R.id.create);
        profil = findViewById(R.id.profil);
        pageName = findViewById(R.id.pageName);


        // Konfiguration der Aufgabenliste
        setUpTasks();

        // Initialisierung des TaskAdapters und Setzen des Adapters auf den RecyclerView
        ta = new TaskAdapter(this, tasklist, this);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Konfiguration des Swipe-to-Delete Mechanismus
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TaskAdapterHelper(ta));
        touchHelper.attachToRecyclerView(rv);

        // Konfiguration des OnClickListener für den "Create" Button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
            }
        });

        // Konfiguration des OnClickListener für den "Profil" Button
        profil.setOnClickListener(v -> {
            Intent i = new Intent(this, Profil.class);
            startActivity(i);
        });
    }

    /**
     * Diese Methode initialisiert die Aufgabenliste aus der Datenbank und aktualisiert sie bei Änderungen.
     */
    private void setUpTasks(){

        daoTodo.get().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<TodoModuls> toSort = new ArrayList<>();
                tasklist.clear();

                // Durchlaufen der Aufgaben in der Datenbank und Hinzufügen
                for(DataSnapshot data : snapshot.getChildren()){
                   TodoModuls todoModul = data.getValue(TodoModuls.class);
                   //Elemente auswaehlen welche dem Benutzer zu gewiesen wurden oder von diesem erstellt wurden
                   if(todoModul.getForUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()) || todoModul.getAutor().equals(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()) ) {
                       todoModul.setKey(data.getKey());

                       // Elemente der Taskliste hinzufügen
                       tasklist.add(todoModul);
                   }
                }
                //Sortieren der liste, nach distanz zum Faelligkeitsdatum
                Collections.sort(tasklist);

                //Benachtichtigung des Adapters ueber eine aenderung
                ta.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Fehlerbehandlung wenn lesen von Datenbank fehlschlägt.
                Log.d("MainActivity", "onCancelled: " + error);
            }
        });
    }

   /**
   * Der Aufruf dieser Methode geschieht dann wenn ein Benutzer auf eine Aufgabe in der Ansicht Klickt.
   * Es wird eine neue Altivität geöffnet, welche eine deteilierte ansicht der Aufgabe wieder gibt.
   * Ebenso übergibt sie die werte an den Intent i aus der ArrayListe taskList, welche aus dem komlexen Datentyp TodoModuls besteht-
   *
   * @param position Ist die Position der geklickten Aufgabe
    */
    @Override
    public void onTodoClick(int position) {
        Intent i = new Intent(MainActivity.this, ExpandetTodo.class);
        i.putExtra("NAME", tasklist.get(position).getName());
        i.putExtra("AUTOR", tasklist.get(position).getAutor());
        i.putExtra("DATE", tasklist.get(position).getMaturityDate());
        i.putExtra("BESCHREIBUNG", tasklist.get(position).getBeschreibung());
        i.putExtra("KEY", tasklist.get(position).getKey() );

        startActivity(i);

    }
}