package com.example.javatest.Actions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.MainActivity;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;
/**
 * Die EditTodo-Klasse ist ein BottomSheetDialogFragment zum Bearbeiten einer To-Do-Aufgabe.
 * @author Raphael Härle
 * @version 1.0
 */
public class EditTodo  extends BottomSheetDialogFragment {

    public static final String TAG = "add_task_layout";
    private  String taskKey;
    private EditText aufgabenName, beschreibung, day, month, year;
    private TextView task_name,  task_beschreibung, task_endDate, key;
    private TextView pageName;
    private Button save;

    DAOtodo daoTodo =  new DAOtodo();

    /**
     * Erstellt eine neue Instanz der EditTodo-Klasse.
     *
     * @return Eine neue Instanz der EditTodo-Klasse.
     */
    public static EditTodo newInstance(){ return new EditTodo();}

    /**
     * Wird aufgerufen, wenn die EditTodo-Klasse erstellt wird.
     *
     * @param saveInstance Der gespeicherte Zustand der Instanz.
     */
    @Override
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);

        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    /**
     * Wird aufgerufen, um die Benutzeroberfläche des Dialogfragments zu erstellen.
     *
     * @param inflater           Der Inflater zum Aufblasen des Layouts.
     * @param container          Der Container, der das Dialogfragment enthält.
     * @param saveInstance Der gespeicherte Zustand der Instanz.
     * @return Die erstellte View für das Dialogfragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance){
        View view = inflater.inflate(R.layout.add_task_layout, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    /**
     * Wird aufgerufen, wenn die View erstellt wurde.
     *
     * @param view                  Die erstellte View des Dialogfragments.
     * @param saveInstance Der gespeicherte Zustand der Instanz.
     */
    @Override
    public void onViewCreated(View view, Bundle saveInstance){
        super.onViewCreated(view, saveInstance);
        aufgabenName = getView().findViewById(R.id.aufgabenName);
        beschreibung = getView().findViewById(R.id.beschreibung);
        day= getView().findViewById(R.id.day);
        month = getView().findViewById(R.id.month);
        year = getView().findViewById(R.id.year);

        pageName = getView().findViewById(R.id.pageName);
        pageName.setText("Aufgabe Bearbeiten");
        save = getView().findViewById(R.id.save);

        task_name = getActivity().findViewById(R.id.aufgabenName);
        task_beschreibung = getActivity().findViewById(R.id.aufgabenBeschreibung);
        task_endDate = getActivity().findViewById(R.id.aufgabenEndDate);
        key = getActivity().findViewById(R.id.key);
        taskKey = key.getText().toString();
        aufgabenName.setText(task_name.getText());
        beschreibung.setText(task_beschreibung.getText());
        save.setText("Bearbeiten");
        save.setWidth(250);

        if (task_endDate.getText().toString().contains(".")){
            String[] dateParts = task_endDate.getText().toString().split("\\.");
            String len = " " +dateParts.length;
            day.setText(dateParts[0].toString());
            month.setText(dateParts[1].toString());
            year.setText(dateParts[2].toString());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task_name = aufgabenName.getText().toString();
                String task_beschreibung = beschreibung.getText().toString();
                String task_day = day.getText().toString();
                String task_month = month.getText().toString();
                String task_year = year.getText().toString();
                String autor = "Name";

                TodoModuls tdm = new TodoModuls( "0", task_name,  autor,  task_beschreibung,  task_day, task_month, task_year);
                HashMap <String, Object> tdmChange = new HashMap<>();
                tdmChange.put("beschreibung", tdm.getBeschreibung());
                tdmChange.put("maturityDate", tdm.getMaturityDate());
                tdmChange.put("name", tdm.getName());
                daoTodo.update(taskKey, tdmChange);
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });




    }
}