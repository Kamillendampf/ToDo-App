package com.example.javatest.Addapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.Moduls.TodoModuls;
import com.example.javatest.R;
import com.example.javatest.interfaces.ViewTodoBody;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final ViewTodoBody vtb;
    Context context;
    ArrayList<TodoModuls> todoModuls;
    public TaskAdapter(Context context, ArrayList<TodoModuls> todoModuls, ViewTodoBody vtb){
        this.context = context;
        this.todoModuls = todoModuls;
        this.vtb = vtb;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {link onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
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
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.taskName.setText(todoModuls.get(position).getName());
        holder.taskAuthor.setText(todoModuls.get(position).getAutor());
        holder.taskEnd.setText(todoModuls.get(position).getMaturityDate());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return todoModuls.size();
    }

    /*
        grabbing the views from our Recycling_view_row layout file
        Kinda like on the onCreate method
         */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView taskName, taskAuthor, taskEnd;



        public ViewHolder(@NonNull View itemView, ViewTodoBody vtb) {
            super(itemView);

            this.taskName = itemView.findViewById(R.id.taskName);
            this.taskAuthor = itemView.findViewById(R.id.taskAuthor);
            this.taskEnd = itemView.findViewById(R.id.taskEnd);

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