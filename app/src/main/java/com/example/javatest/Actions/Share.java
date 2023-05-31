package com.example.javatest.Actions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;

/**
 * Die Share-Klasse ist eine BottomSheetDialogFragment, die für das Teilen von To-Do-Aufgaben verwendet wird.
 * @author Raphael Härle
 * @version 1.0
 */
public class Share extends BottomSheetDialogFragment{
    DAOtodo daoTodo = new DAOtodo();

    public static final String TAG = "share_todo";

    private EditText forUser;
    private Button share;
    TextView task_name,  task_beschreibung,  autor, task_endDate, key;
    String day, month, year;

    /**
     * Erstellt eine neue Instanz der Share-Klasse.
     *
     * @return Eine neue Instanz der Share-Klasse.
     */
    public static Share newInstance (){return new Share();}

    /**
     * Wird aufgerufen, wenn die Share-Klasse erstellt wird.
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
     * @param savedInstanceState Der gespeicherte Zustand der Instanz.
     * @return Die erstellte View für das Dialogfragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance){
        View view = inflater.inflate(R.layout.share_todo, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    /**
     * Wird aufgerufen, wenn die View erstellt wurde.
     *
     * @param view                  Die erstellte View des Dialogfragments.
     * @param savedInstanceState Der gespeicherte Zustand der Instanz.
     */
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
                key = getActivity().findViewById(R.id.key);
                if (task_endDate.getText().toString().contains(".")){
                    String[] dateParts = task_endDate.getText().toString().split("\\.");

                     day = dateParts[0].toString();
                     month = dateParts[1].toString();
                     year = dateParts[2].toString();
                } else{
                    day = "404";
                }


                TodoModuls tdm = new TodoModuls( forUser.getText().toString(), task_name.getText().toString(),  autor.getText().toString(),  task_beschreibung.getText().toString(),  day, month, year);
                HashMap<String, Object> tdmChange = new HashMap<>();
                tdmChange.put("forUser", tdm.getForUser());

                daoTodo.update(key.getText().toString(), tdmChange);

                getDialog().dismiss();
            }
        });


    }
}