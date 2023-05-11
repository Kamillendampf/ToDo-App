package com.example.javatest;

import androidx.annotation.NonNull;
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
import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.interfaces.ViewTodoBody;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ViewTodoBody {
    private final int me = 0;
    private ArrayList<TodoModuls> tasklist = new ArrayList<>();
    private ImageButton create;
    private ImageButton profil;
    private DAOtodo daoTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daoTodo = new DAOtodo();

        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.tasks);

        create = findViewById(R.id.create);
        profil = findViewById(R.id.profil);

        TaskAdapter ta = new TaskAdapter(this, tasklist, this);
        rv.setAdapter(ta);
        rv.setLayoutManager(new LinearLayoutManager(this));

        setUpTasks();



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
            }
        });

        profil.setOnClickListener(v -> {
            Intent i = new Intent(this, Profil.class);
            startActivity(i);
        });
    }
    private void setUpTasks(){

        daoTodo.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasklist.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                   TodoModuls todoModul = data.getValue(TodoModuls.class);
                   if(todoModul.getForUser() == me) {
                       tasklist.add(todoModul);
                   }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onTodoClick(int position) {
        TextView taskName = findViewById(R.id.taskName);
        taskName.setText(tasklist.get(position).getName());
    }








}