package com.example.javatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.javatest.Actions.AddTask;
import com.example.javatest.Addapter.TaskAdapter;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.interfaces.ViewTodoBody;


import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements ViewTodoBody {


    private ArrayList<TodoModuls> tasklist = new ArrayList<>();
    private ImageButton create;
    private ImageButton profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.tasks);

        create = findViewById(R.id.create);
        profil = findViewById(R.id.profil);

        setUpTasks();

        TaskAdapter ta = new TaskAdapter(this, tasklist, this);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(this));

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileClick();
            }
        });
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
        TextView taskName = findViewById(R.id.taskName);
        tasklist.get(position).setName("Ich wurde gedrueckt"+position);
        taskName.setText(tasklist.get(position).getName());
    }

    public void onProfileClick(){
        final Intent i = new Intent(this, Profil.class);
    }






}