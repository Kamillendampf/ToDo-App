package com.example.javatest.Actions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AddTask extends BottomSheetDialogFragment {
    public static final String TAG = "add_task_layout";

    private EditText aufgabenName, beschreibung, day, month, year;
    private Button save;
    private Date d = new Date();
    private ZoneId zoneId;
    private LocalDate ld;
    DAOtodo daoTodo =  new DAOtodo();

    public static AddTask newInstance(){
        return new AddTask();
    }

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
        day= getView().findViewById(R.id.day);
        month= getView().findViewById(R.id.month);
        year= getView().findViewById(R.id.year);

        save = getView().findViewById(R.id.save);



        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                String task_name = aufgabenName.getText().toString();
                String task_beschreibung = beschreibung.getText().toString();
                String task_day = day.getText().toString();
                String task_month = month.getText().toString();
                String task_year = year.getText().toString();

                String autor = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();





                // Rules for input velues
                if ((task_year.equals("") || task_name.equals("")|| task_month.equals("")) || (Integer.parseInt(task_day)< 1 || Integer.parseInt(task_day) > 31  || task_day.equals("") || Integer.parseInt(task_month) < 0 || Integer.parseInt(task_month) > 12   || Integer.parseInt(task_year) < Calendar.getInstance().get(Calendar.YEAR))){
                    if ( (task_day.equals("") ) || ( Integer.parseInt(task_day) < 1 || Integer.parseInt(task_day) > 31 ) || (Integer.parseInt(task_day) < Calendar.getInstance().get(Calendar.DATE))) {
                        day.setText("");
                        day.setBackgroundColor(R.color.red);
                    } else{
                        day.setBackgroundColor(R.color.gray);
                    }
                    if ((task_month.equals("")) || (Integer.parseInt(task_month) < 1 || Integer.parseInt(task_month) > 12  ) || ((Integer.parseInt(task_month) < Calendar.getInstance().get(Calendar.MONTH)))) {
                        month.setText("");
                        month.setBackgroundColor(R.color.red);
                    }
                    else{
                        day.setBackgroundColor(R.color.gray);
                    }
                    if (  (task_year.equals("")) || (Integer.parseInt(task_year) < Calendar.getInstance().get(Calendar.YEAR))) {
                        year.setText("");
                        year.setBackgroundColor(R.color.red);
                    }
                    else{
                        day.setBackgroundColor(R.color.gray);
                    }
                    if (task_name.equals("")){
                        aufgabenName.setBackgroundColor(R.color.red);
                    }else {
                        aufgabenName.setBackgroundColor(R.color.gray);
                    }

                } else {
                    TodoModuls tdm = null;
                    //Todo chance input of for user
                        tdm = new TodoModuls( FirebaseAuth.getInstance().getCurrentUser().getEmail(), task_name,  autor,  task_beschreibung,  task_day, task_month, task_year);

                    daoTodo.add(tdm).addOnSuccessListener(suc -> {


                        getDialog().dismiss();

                    }).addOnFailureListener(er -> {

                    });
                }

                }
        });
    }
}