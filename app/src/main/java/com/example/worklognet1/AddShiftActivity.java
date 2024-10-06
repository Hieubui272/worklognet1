package com.example.worklognet1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class AddShiftActivity extends AppCompatActivity {

    private EditText etStartTime, etEndTime;
    private ShiftDatabaseHelper dbHelper;  // Khởi tạo đối tượng quản lý cơ sở dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        // Khởi tạo database helper
        dbHelper = new ShiftDatabaseHelper(this);

        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        Button btnSaveShift = findViewById(R.id.btnSaveShift);

        // Xử lý sự kiện khi bấm nút "Lưu ca làm"
        btnSaveShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTime = etStartTime.getText().toString();
                String endTime = etEndTime.getText().toString();

                // Kiểm tra nếu người dùng đã nhập đủ thông tin
                if (!startTime.isEmpty() && !endTime.isEmpty()) {
                    // Thêm ca làm vào cơ sở dữ liệu
                    dbHelper.addShift(startTime, endTime);
                    Toast.makeText(AddShiftActivity.this, "Đã lưu ca làm", Toast.LENGTH_SHORT).show();

                    // Xóa nội dung sau khi lưu
                    etStartTime.setText("");
                    etEndTime.setText("");
                } else {
                    Toast.makeText(AddShiftActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
