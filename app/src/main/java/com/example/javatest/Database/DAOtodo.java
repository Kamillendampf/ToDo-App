package com.example.javatest.Database;

import com.example.javatest.Moduls.TodoModuls;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOtodo{

    private DatabaseReference dr;

    public DAOtodo(){
        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        dr = fd.getReference(TodoModuls.class.getSimpleName());
    }

    public Task<Void> add(TodoModuls tdm){
        return dr.push().setValue(tdm);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return dr.child(key).updateChildren(hashMap);

    }

    public Task<Void> remove(String key){
       return dr.child(key).removeValue();
    }

    public Query get(){
        return dr.orderByKey();
    }

}