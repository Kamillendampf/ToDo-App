package com.example.javatest.Addapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.Database.DAOtodo;
import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.example.javatest.interfaces.ViewTodoBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final ViewTodoBody vtb;
    private DAOtodo daOtodo = new DAOtodo();
    Context context;
    ArrayList<TodoModuls> todoModuls;

    /**
     * Konstruktor des TaskAdapters.
     *
     * @param context      Der Kontext der Anwendung.
     * @param todoModuls   Eine ArrayList mit TodoModuls-Objekten.
     * @param vtb          Das ViewTodoBody-Objekt für die Klickereignisse.
     */
    public TaskAdapter(Context context, ArrayList<TodoModuls> todoModuls, ViewTodoBody vtb){
        this.context = context;
        this.todoModuls = todoModuls;
        this.vtb = vtb;
    }

    /**
     * Gibt den Kontext zurück.
     *
     * @return Der Kontext.
     */
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Wird aufgerufen, wenn RecyclerView einen neuen {@link ViewHolder} des angegebenen Typs benötigt, um
     * ein Element zu repräsentieren.
     * <p>
     * Dieser neue ViewHolder sollte mit einer neuen View konstruiert werden, die die Elemente repräsentieren kann.
     * des gegebenen Typs repräsentieren kann. Sie können entweder eine neue View manuell erstellen oder sie aus einer XML
     * Layout-Datei.
     * <p>
     * Der neue ViewHolder wird verwendet um Elemente des Adapters anzuzeigen.
     * {link onBindViewHolder(ViewHolder, int, List)}. Da er wiederverwendet wird um
     * verschiedenen Elementen im Datensatz wiederverwendet wird, ist es eine gute Idee Referenzen auf Unteransichten der
     * der View zu cachen, um unnötige {@link View#findViewById(int)} Aufrufe zu vermeiden.
     *
     * @param parent Die ViewGroup in welche die neue View hinzugefügt wird, nachdem sie an
     * eine Adapterposition gebunden ist.
     * @param viewType Der View-Typ des neuen Views.
     * @return Ein neuer ViewHolder, der eine View des angegebenen Viewtyps enthält.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater i = LayoutInflater.from(context);
        View view =i.inflate(R.layout.overview_task, parent, false);

        return new TaskAdapter.ViewHolder(view, vtb);
    }

    /**
     * Wird von RecyclerView aufgerufen, um die Daten an der angegebenen Position anzuzeigen. Diese Methode sollte
     * den Inhalt der {@link ViewHolder#itemView} aktualisieren, um das Element an der angegebenen
     * Position widerzuspiegeln.
     * <p>
     * Beachte, dass im Gegensatz zur {@link ListView} RecyclerView diese Methode nicht erneut aufrufen wird,
     * wenn sich die Position des Elements im Datensatz ändert, es sei denn, das Element selbst ist
     * ungültig oder die neue Position kann nicht bestimmt werden. Aus diesem Grund solltest du nur
     * den <code>position</code>-Parameter verwenden, um das zugehörige Datenobjekt innerhalb
     * dieser Methode abzurufen und keine Kopie davon aufbewahren. Wenn du die Position eines Elements später benötigst
     * (z.B. in einem Klick-Listener), verwende {@link ViewHolder#getAdapterPosition()}, die
     * die aktualisierte Adapterposition enthält.
     * <p>
     * Überschreibe stattdessen {link #onBindViewHolder(ViewHolder, int, List)}, wenn der Adapter
     * eine effiziente teilweise Bindung unterstützen kann.
     *
     * @param holder   Der ViewHolder, der aktualisiert werden soll, um den Inhalt des
     *                 Elements an der angegebenen Position im Datensatz darzustellen.
     * @param position Die Position des Elements im Datensatz des Adapters.
     */
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.taskName.setText(todoModuls.get(position).getName());
        holder.taskAuthor.setText(todoModuls.get(position).getAutor());
        holder.taskEnd.setText(todoModuls.get(position).getMaturityDate());
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        String currentDate = format.format(Calendar.getInstance().getTime());
        Date today = null;
        Date myDate = null;
        try {
            today = format.parse(currentDate);
            myDate = format.parse(todoModuls.get(position).getMaturityDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long myDistance =(( myDate.getTime() - today.getTime())  / (24 * 60 * 60 * 1000));

        if (myDistance < 3) {

            holder.taskColor.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.red));

        } else if (myDistance < 5) {
            holder.taskColor.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.yellow));
        } else {
            holder.taskColor.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.gruen) );
        }
        if (todoModuls.get(position).isColor()){
            holder.taskCarde.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.lightgray));
        }
 }



    /**
     * Gibt die Gesamtanzahl der Elemente im Datensatz des Adapters zurück.
     *
     * @return Die Gesamtanzahl der Elemente in diesem Adapter.
     */
    @Override
    public int getItemCount() {
        return todoModuls.size();
    }

    public void removeItem(int position){
        daOtodo.remove(todoModuls.get(position).getKey());
    }

    public void setItemColor(int position){
        if (todoModuls.get(position).isColor()){
            todoModuls.get(position).setColor(false);
            HashMap<String, Object> tdmChange = new HashMap<>();
            tdmChange.put("color", todoModuls.get(position).isColor() );
            daOtodo.update(todoModuls.get(position).getKey(), tdmChange);
        } else {
            todoModuls.get(position).setColor(true);
            HashMap<String, Object> tdmChange = new HashMap<>();
            tdmChange.put("color", todoModuls.get(position).isColor() );
            daOtodo.update(todoModuls.get(position).getKey(), tdmChange);
        }

    }

    /**
     * Eine statische Klasse, die den ViewHolder für die RecyclerView darstellt.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        View taskCarde;

        TextView taskName, taskAuthor, taskEnd;
        Button taskColor;

        /**
         * Konstruktor des ViewHolders.
         *
         * @param itemView Die zugehörige View des ViewHolders.
         * @param vtb      Das ViewTodoBody-Objekt für die Klickereignisse.
         */
        public ViewHolder(@NonNull View itemView, ViewTodoBody vtb) {
            super(itemView);

            this.taskName = itemView.findViewById(R.id.taskName);
            this.taskAuthor = itemView.findViewById(R.id.taskAuthor);
            this.taskEnd = itemView.findViewById(R.id.taskEnd);
            this.taskColor = itemView.findViewById(R.id.setColore);
            this.taskCarde = itemView.findViewById(R.id.cardColor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vtb != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            vtb.onTodoClick(position);
                        }
                    }
                }
            });
        }


    }
}