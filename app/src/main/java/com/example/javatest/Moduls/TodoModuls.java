package com.example.javatest.Moduls;


import android.graphics.Color;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Diese Klasse ist ein komplexer Datentyp.
 * Ein To-Do-Modul enthält Informationen wie den Namen der Aufgabe, eine optionale Beschreibung, den Ersteller, das Fälligkeitsdatum und einen eindeutigen Schlüssel zur Identifizierung.
 * Es implementiert das Interface Comparable<TodoModuls>, um Vergleichsoperationen durchzuführen.
 * @author Raphael Härle
 * @version 1.0
 */
public class TodoModuls implements Comparable<TodoModuls> {
    private String name;                //Name der Aufgabe
    private String beschreibung;        // Optionale Beschreibung der Aufgabe
    private String autor;               // Ersteller der Aufgabe
    private String maturityDate;     // Faelligkeitsdatum


    private String key;             // Der Key wird fuer die identifikation des ToDoModules benoetiget um dieses zu loeschen
    private String forUser;            // id of the user.

    private boolean color;             //Aufgabe wird hervorgehoben wenn gesetzt.


    //Konstruktor
    public TodoModuls(){}

    /**
     * Konstruktor zur Initialisierung eines To-Do-Moduls.
     *
     * @param forUser        E-Mail des Benutzers
     * @param name           Name der Aufgabe
     * @param autor          Ersteller der Aufgabe
     * @param beschreibung   Optionale Beschreibung der Aufgabe
     * @param day            Tag der Fälligkeit
     * @param month          Monat der Fälligkeit
     * @param year           Jahr der Fälligkeit
     */
    public TodoModuls(String forUser, String name, String autor, String beschreibung, String day, String month, String year) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.autor = autor;
        this.maturityDate = day+"."+month+"."+year;
        this.forUser = forUser;

    }

    // Getter- und Setter-Methoden

    /**
     * Gibt den Namen der Aufgabe zurück.
     *
     * @return Der Name der Aufgabe
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Aufgabe.
     *
     * @param name Der Name der Aufgabe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die optionale Beschreibung der Aufgabe zurück.
     *
     * @return Die Beschreibung der Aufgabe
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Setzt die optionale Beschreibung der Aufgabe.
     *
     * @param beschreibung Die Beschreibung der Aufgabe
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Gibt den Ersteller der Aufgabe zurück.
     *
     * @return Der Ersteller der Aufgabe
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Setzt den Ersteller der Aufgabe.
     *
     * @param autor Der Ersteller der Aufgabe
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Gibt das Fälligkeitsdatum der Aufgabe zurück.
     *
     * @return Das Fälligkeitsdatum der Aufgabe
     */
    public String getMaturityDate() {
        return maturityDate;
    }

    /**
     * Setzt das Fälligkeitsdatum der Aufgabe.
     *
     * @param maturityDate Das Fälligkeitsdatum der Aufgabe
     */
    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    /**
     * Gibt die ID des Benutzers zurück.
     *
     * @return Die ID des Benutzers
     */
    public String getForUser(){ return forUser;}

    /**
     * Setzt die ID des Benutzers.
     *
     * @param userId Die ID des Benutzers
     */
    public void setForUser(String userId){ forUser = userId; }

    /**
     * Gibt den Schlüssel des To-Do-Moduls zurück.
     *
     * @return Der Schlüssel des To-Do-Moduls
     */
    public String getKey() { return this.key; }

    /**
     * Setzt den Schlüssel des To-Do-Moduls.
     *
     * @param key Der Schlüssel des To-Do-Moduls
     */
    public void setKey(String key){ this.key = key; }

    public boolean isColor(){
        return color;
    }
    public void setColor(boolean color){
        this.color = color;
    }


    /**
     * Vergleicht das To-Do-Modul mit einem anderen To-Do-Modul.
     * Das Vergleichskriterium ist das Fälligkeitsdatum.
     *
     * @param tm Das andere To-Do-Modul, mit dem verglichen wird
     * @return -1, wenn das aktuelle To-Do-Modul vor dem anderen liegt
     *          0, wenn beide To-Do-Module am gleichen Tag fällig sind
     *          1, wenn das aktuelle To-Do-Modul nach dem anderen liegt
     */
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