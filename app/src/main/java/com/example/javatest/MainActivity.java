package com.example.javatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.javatest.Addapter.TaskAdapter;
import com.example.javatest.Moduls.TodoModuls;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tasks;

    private ArrayList<TodoModuls> tasklist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.tasks);
        setUpTasks();

        TaskAdapter ta = new TaskAdapter(this, tasklist);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(this));
        tasks = findViewById(R.id.tasks);
        tasks.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpTasks(){
        String [] task_name = getResources().getStringArray(R.array.task_name);
        String [] task_author = getResources().getStringArray(R.array.task_author);

        for (int i = 0; i < task_name.length; i++){
            tasklist.add(new TodoModuls(task_name[i], task_author[i], "nothing to say"));
        }
    }
}