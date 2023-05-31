package com.example.javatest.Addapter.Helper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javatest.Addapter.TaskAdapter;
import com.example.javatest.R;

/**
 * Der TaskAdapterHelper ist eine Hilfsklasse für das Swipe-to-Delete-Verhalten in der TaskAdapter RecyclerView.
 */
public class TaskAdapterHelper extends ItemTouchHelper.SimpleCallback {

    private TaskAdapter adapter;

    /**
     * Konstruktor des TaskAdapterHelpers.
     *
     * @param taskAdapter Der TaskAdapter, der das Swipe-to-Delete-Verhalten verwendet.
     */
    public TaskAdapterHelper(TaskAdapter taskAdapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        adapter = taskAdapter;
    }

    /**
     * Wird aufgerufen, wenn ein Element in dem RecyclerView verschoben wird und uebrprueft ob es gerade in bewegung ist.
     *
     * @param recyclerView Die RecyclerView.
     * @param viewHolder   Der ViewHolder des verschobenen Elements.
     * @param target       Der ViewHolder des Zielpositionselements.
     * @return True, wenn das Element verschoben wurde, andernfalls False.
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
        return false;
    }

    /**
     * Wird aufgerufen, wenn ein Element in dem RecyclerView verschoben wurde.
     *
     * @param viewHolder Der ViewHolder des verschobenen Elements.
     * @param direction  Die Richtung der Swipe-Aktion.
     */
    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction){
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.RIGHT){
            adapter.setItemColor(position);
        }
        if(direction == ItemTouchHelper.LEFT){
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Aufgabe Loeschen");
            builder.setMessage("Sind Sie sicher?");
            builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItem(position);

                }
            });
            builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    /**
     * Wird aufgerufen, wenn die Swipe-Aktion visualisiert wird.
     *
     * @param can                Der Canvas zum Zeichnen.
     * @param recyclerView      Die RecyclerView.
     * @param viewHolder        Der ViewHolder des verschobenen Elements.
     * @param dX                Der X-Versatz des Elements.
     * @param dY                Der Y-Versatz des Elements.
     * @param action            Die Aktion.
     * @param isCurrentlyActive True, wenn die Aktion aktiv ist, andernfalls False.
     */
    @Override
    public void  onChildDraw(Canvas can, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int action, boolean isCurrantlyActive){
        super.onChildDraw(can, recyclerView, viewHolder,  dX, dY, action, isCurrantlyActive);

        Drawable icon;
        ColorDrawable background;

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;


        icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.delete);
        background = new ColorDrawable(Color.RED);


        int iconMargin = ((itemView.getHeight() - icon.getIntrinsicHeight())/2);
        int iconTop = itemView.getTop() +(itemView.getHeight() - icon.getIntrinsicHeight()) /2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();
        if (dX > 0){
            background = new ColorDrawable(Color.BLUE);
            background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + (int) dX + backgroundCornerOffset, itemView.getBottom());
        } else if (dX<0){
            icon.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset, itemView.getTop(), itemView.getRight() ,itemView.getBottom());
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset, itemView.getTop(), itemView.getRight() ,itemView.getBottom() );
        } else {
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(can);
        icon.draw(can);
    }


}