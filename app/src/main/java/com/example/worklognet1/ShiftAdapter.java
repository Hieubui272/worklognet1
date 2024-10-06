package com.example.worklognet1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder> {

    private List<Shift> shiftList;
    private ShiftDatabaseHelper dbHelper;

    public ShiftAdapter(List<Shift> shiftList, ShiftDatabaseHelper dbHelper) {
        this.shiftList = shiftList;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ShiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shift, parent, false);
        return new ShiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, int position) {
        Shift shift = shiftList.get(position);
        holder.startTimeTextView.setText(shift.getStartTime());
        holder.endTimeTextView.setText(shift.getEndTime());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.deleteShift(shift);

                shiftList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, shiftList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shiftList.size();
    }

    public static class ShiftViewHolder extends RecyclerView.ViewHolder {

        TextView startTimeTextView, endTimeTextView;
        Button deleteButton;

        public ShiftViewHolder(@NonNull View itemView) {
            super(itemView);
            startTimeTextView = itemView.findViewById(R.id.textViewStartTime);
            endTimeTextView = itemView.findViewById(R.id.textViewEndTime);
            deleteButton = itemView.findViewById(R.id.btnDeleteShift);
        }
    }
}
