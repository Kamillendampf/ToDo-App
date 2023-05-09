package com.example.javatest.Moduls;

import java.time.LocalDate;

public class TodoModuls {
    private String name;                //Name der Aufgabe
    private String beschreibung;        // Optionale Beschreibung der Aufgabe
    private String autor;               // Ersteller der Aufgabe
    private LocalDate maturityDate;     // Faelligkeitsdatum



    //Konstruktor

    public TodoModuls(String name, String beschreibung, String autor, LocalDate maturityDate) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.autor = autor;
        this.maturityDate = maturityDate;
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

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

}
