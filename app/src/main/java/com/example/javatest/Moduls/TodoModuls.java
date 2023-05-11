package com.example.javatest.Moduls;

import java.time.LocalDate;

public class TodoModuls {
    private String name;                //Name der Aufgabe
    private String beschreibung;        // Optionale Beschreibung der Aufgabe
    private String autor;               // Ersteller der Aufgabe
    private String maturityDate;     // Faelligkeitsdatum

    private int forUser;            // id of the user.



    //Konstruktor
    public TodoModuls(){}
    public TodoModuls(int forUser, String name, String autor, String beschreibung, String endDate) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.autor = autor;
       this.maturityDate = endDate;
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

    public int getForUser(){ return forUser;}

    public void setForUser(int userId){ forUser = userId; }

}
