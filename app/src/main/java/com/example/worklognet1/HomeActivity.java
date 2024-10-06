package com.example.worklognet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
    // DEF
    private TextView tvWelcome;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    // [End] DEF

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Full screen and hide action bar
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        // [End] Full screen and hide action bar

        // Get Instance
        tvWelcome = findViewById(R.id.tvWelcome);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        // [End] Get Instance

        // Check User
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            fetchUserInfo(currentUser.getUid());
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show();
        }
        // [End] Check User

        // Logout button
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> showLogoutConfirmationDialog());
    }

    private void fetchUserInfo(String uid) {
        db.collection("users").document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String nickname = documentSnapshot.getString("nickname");
                        String email = documentSnapshot.getString("email");
                        tvWelcome.setText("Welcome, " + nickname);
                    } else {
                        tvWelcome.setText("User info not found.");
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(HomeActivity.this, "Failed to fetch user info.", Toast.LENGTH_SHORT).show();
                });
    }

    // Show logout popup
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            mAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, com.project.worklognet.LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}