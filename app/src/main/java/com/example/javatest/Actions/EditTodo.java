package com.example.javatest.Actions;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EditTodo  extends BottomSheetDialogFragment {

    public static final String TAG = "add_task_layout";
    public String key;
    private EditText aufgabenName, beschreibung, text_endDate;
    private TextView task_name,  task_beschreibung, task_endDate;
    private TextView pageName;
    private Button save;

    DAOtodo daoTodo =  new DAOtodo();

    public static EditTodo newInstance(){ return new EditTodo();}

    @Override
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance){
        View view = inflater.inflate(R.layout.add_task_layout, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstance){
        super.onViewCreated(view, saveInstance);
        aufgabenName = getView().findViewById(R.id.aufgabenName);
        beschreibung = getView().findViewById(R.id.beschreibung);
        text_endDate= getView().findViewById(R.id.endDate);
        pageName = getView().findViewById(R.id.pageName);
        pageName.setText("Aufgabe Bearbeiten");
        save = getView().findViewById(R.id.save);

        task_name = getActivity().findViewById(R.id.aufgabenName);
        task_beschreibung = getActivity().findViewById(R.id.aufgabenBeschreibung);
        task_endDate = getActivity().findViewById(R.id.aufgabenEndDate);
        aufgabenName.setText(task_name.getText());
        beschreibung.setText(task_beschreibung.getText());
        text_endDate.setText(task_endDate.getText());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task_name = aufgabenName.getText().toString();
                String task_beschreibung = beschreibung.getText().toString();
                String task_endDate = text_endDate.getText().toString();
                String autor = "Name";
                TodoModuls tdm = new TodoModuls( 0, task_name,  autor,  task_beschreibung,  task_endDate);

            }
        });




    }
}
