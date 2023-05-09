package com.example.javatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.javatest.Moduls.TodoModuls;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tasks;

    private ArrayList<TodoModuls> tasklist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        tasks = findViewById(R.id.tasks);
        tasks.setLayoutManager(new LinearLayoutManager(this));

    }

    //private void setUpTasks(){

    //}
}