package com.example.javatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.javatest.Addapter.TaskAdapter;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.interfaces.ViewTodoBody;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewTodoBody {


    private ArrayList<TodoModuls> tasklist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.tasks);
        setUpTasks();

        TaskAdapter ta = new TaskAdapter(this, tasklist, this);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setUpTasks(){
        String [] task_name = getResources().getStringArray(R.array.task_name);
        String [] task_author = getResources().getStringArray(R.array.task_author);
        String [] task_endDate = getResources().getStringArray(R.array.task_endDate);

        for (int i = 0; i < task_name.length; i++){
            tasklist.add(new TodoModuls(task_name[i], task_author[i], "nothing to say", task_endDate[i]));
        }
    }

    @Override
    public void onTodoClick(int position) {

    }
}