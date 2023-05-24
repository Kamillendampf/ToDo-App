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


public class TaskAdapterHelper extends ItemTouchHelper.SimpleCallback {

    private TaskAdapter adapter;

    public TaskAdapterHelper(TaskAdapter taskAdapter){
        super(0, ItemTouchHelper.LEFT);
        adapter = taskAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
        return false;
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction){
        final int position = viewHolder.getAdapterPosition();
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

        if (dX<0){
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            icon.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset, itemView.getTop(), itemView.getRight() ,itemView.getBottom() );
        } else {
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(can);
        icon.draw(can);
    }


}