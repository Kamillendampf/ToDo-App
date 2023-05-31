package com.example.javatest.interfaces;

/**
 * Das ViewTodoBody-Interface definiert eine Methode zum Behandeln von Klicks auf ein To-Do.
 * @author Raphael Härle
 * @version 1.0
 */
public interface ViewTodoBody {
    /**
     * Wird aufgerufen, wenn auf ein To-Do geklickt wird.
     *
     * @param position Die Position des geklickten To-Dos
     */
    void onTodoClick(int position);
}
