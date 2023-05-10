package com.example.javatest.Database;

import com.example.javatest.Moduls.TodoModuls;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOtodo{

    private DatabaseReference dr;

    public DAOtodo(){
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        dr = fd.getReference(TodoModuls.class.getSimpleName());
    }

    public Task<Void> add(TodoModuls tdm){
        return dr.push().setValue(tdm);
    }
}
