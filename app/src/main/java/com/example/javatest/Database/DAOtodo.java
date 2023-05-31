package com.example.javatest.Database;

import com.example.javatest.Moduls.TodoModuls;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

/**
 * Die DAOtodo-Klasse stellt Methoden zum Hinzufügen, Aktualisieren, Entfernen und Abrufen von To-Do-Daten aus der Datenbank bereit.
 * @author Raphael Härle
 * @version 1.0
 */
public class DAOtodo{

    private DatabaseReference dr;

    /**
     * Konstruktor der DAOtodo-Klasse.
     * Initialisiert die DatabaseReference, um auf die Datenbank zu zugreifen.
     */
    public DAOtodo(){
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        dr = fd.getReference(TodoModuls.class.getSimpleName());
    }

    /**
     * Fügt ein neues To-Do zur Datenbank hinzu.
     *
     * @param tdm Das TodoModuls-Objekt, das hinzugefügt werden soll.
     * @return Ein Task-Objekt, das den Erfolg des Vorgangs darstellt.
     */
    public Task<Void> add(TodoModuls tdm){
        return dr.push().setValue(tdm);
    }

    /**
     * Aktualisiert ein vorhandenes To-Do in der Datenbank.
     *
     * @param key      Der Schlüssel des To-Dos, das aktualisiert werden soll.
     * @param hashMap  Eine HashMap mit den zu aktualisierenden Feldern und Werten.
     * @return Ein Task-Objekt, das den Erfolg des Vorgangs darstellt.
     */
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return dr.child(key).updateChildren(hashMap);

    }

    /**
     * Entfernt ein vorhandenes To-Do aus der Datenbank.
     *
     * @param key Der Schlüssel des To-Dos, das entfernt werden soll.
     * @return Ein Task-Objekt, das den Erfolg des Vorgangs darstellt.
     */
    public Task<Void> remove(String key){
       return dr.child(key).removeValue();
    }

    /**
     * Ruft eine Query ab, um auf To-Do-Daten in der Datenbank zuzugreifen.
     *
     * @return Eine Query, um auf To-Do-Daten zuzugreifen.
     */
    public Query get(){
        return dr.orderByKey();
    }

}