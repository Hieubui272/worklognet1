package com.example.worklognet1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Find buttons on the layout by their IDs
        Button btnAddShift = findViewById(R.id.btnAddShift);
        Button btnShareJob = findViewById(R.id.btnShareJob);
        Button btnViewShifts = findViewById(R.id.btnViewShifts);  // New button for viewing shifts

        // Handle the event when "Add Shift" button is clicked
        btnAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AddShiftActivity
                Intent intent = new Intent(DashboardActivity.this, AddShiftActivity.class);
                startActivity(intent);
            }
        });

        // Handle the event when "Share Job" button is clicked
        btnShareJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ShareJobActivity
                Intent intent = new Intent(DashboardActivity.this, ShareJobActivity.class);
                startActivity(intent);
            }
        });

        // Handle the event when "View Shifts" button is clicked
        btnViewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ShiftListActivity to view the list of shifts
                Intent intent = new Intent(DashboardActivity.this, ShiftListActivity.class);
                startActivity(intent);
            }
        });
    }
}
