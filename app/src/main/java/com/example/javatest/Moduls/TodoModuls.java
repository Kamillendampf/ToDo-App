package com.example.javatest.Moduls;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoModuls implements Comparable<TodoModuls> {
    private String name;                //Name der Aufgabe
    private String beschreibung;        // Optionale Beschreibung der Aufgabe
    private String autor;               // Ersteller der Aufgabe
    private String maturityDate;     // Faelligkeitsdatum


    private String key;             // Der Key wird fuer die identifikation des ToDoModules benoetiget um dieses zu loeschen
    private String forUser;            // id of the user.
    String test;


    //Konstruktor
    public TodoModuls(){}

    public TodoModuls(String forUser, String name, String autor, String beschreibung, String day, String month, String year) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.autor = autor;
        this.maturityDate = day+"."+month+"."+year;
        this.forUser = forUser;

    }

    //Getter- and Setter-Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getForUser(){ return forUser;}

    public void setForUser(String userId){ forUser = userId; }

    public String getKey() { return this.key; }

    public void setKey(String key){ this.key = key; }


    //Vergleichen der Daten
    public int compareTo(TodoModuls tm){
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String currentDate = format.format(Calendar.getInstance().getTime());

        Date today = null;
        Date myDate = null;
        Date hisDate = null;
        try {
            today = format.parse(currentDate);
            myDate = format.parse(this.maturityDate);
            hisDate = format.parse(tm.maturityDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long myDistance = myDate.getTime() - today.getTime();
        long hisDistance = hisDate.getTime()- today.getTime();

        if (myDistance ==  hisDistance){
            return 0;
        } else if (myDistance >  hisDistance) {
            return 1;
        }else {
            return -1;
        }
    }
}