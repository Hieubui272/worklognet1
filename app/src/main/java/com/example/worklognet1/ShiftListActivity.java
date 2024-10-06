package com.example.worklognet1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShiftListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShiftDatabaseHelper dbHelper;
    private ShiftAdapter shiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewShifts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the database helper
        dbHelper = new ShiftDatabaseHelper(this);

        // Get the list of shifts from the database
        List<Shift> shiftList = dbHelper.getAllShifts();

        // Initialize the ShiftAdapter with shiftList and dbHelper
        shiftAdapter = new ShiftAdapter(shiftList, dbHelper);
        recyclerView.setAdapter(shiftAdapter);
    }
}
