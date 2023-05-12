package com.example.javatest.Actions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Share extends BottomSheetDialogFragment{
    DAOtodo daoTodo = new DAOtodo();

    public static final String TAG = "share_todo";

    private EditText forUser;
    private Button share;
    TextView task_name,  task_beschreibung,  autor,  task_endDate;
    public static Share newInstance (){return new Share();}

    @Override
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance){
        View view = inflater.inflate(R.layout.share_todo, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }
    public void onViewCreated(View view, Bundle saveInstance){
        super.onViewCreated(view, saveInstance);


        share = getView().findViewById(R.id.send);
        forUser = getView().findViewById(R.id.forUser);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task_name = getActivity().findViewById(R.id.aufgabenName);
                task_beschreibung = getActivity().findViewById(R.id.aufgabenBeschreibung);
                task_endDate = getActivity().findViewById(R.id.aufgabenEndDate);
                autor = getActivity().findViewById(R.id.aufgabenAutor);



                TodoModuls tdm = new TodoModuls( Integer.parseInt(String.valueOf(forUser.getText())), task_name.getText().toString(),  autor.getText().toString(),  task_beschreibung.getText().toString(),  task_endDate.getText().toString());
                daoTodo.add(tdm).addOnSuccessListener(suc -> {

                    getDialog().dismiss();

                }).addOnFailureListener(er -> {

                });
            }
        });


    }
}
