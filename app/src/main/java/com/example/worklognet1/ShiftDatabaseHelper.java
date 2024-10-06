package com.example.worklognet1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ShiftDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shiftDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SHIFTS = "shifts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";

    // Constructor
    public ShiftDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // save time
        String CREATE_SHIFT_TABLE = "CREATE TABLE " + TABLE_SHIFTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_START_TIME + " TEXT,"
                + COLUMN_END_TIME + " TEXT" + ")";
        db.execSQL(CREATE_SHIFT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIFTS);
        onCreate(db);
    }

    // add time
    public void addShift(String startTime, String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_START_TIME, startTime);
        values.put(COLUMN_END_TIME, endTime);
        db.insert(TABLE_SHIFTS, null, values);
        db.close();
    }

    // get all shifts
    public List<Shift> getAllShifts() {
        List<Shift> shiftList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SHIFTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Shift shift = new Shift();
                shift.setStartTime(cursor.getString(cursor.getColumnIndex(COLUMN_START_TIME)));
                shift.setEndTime(cursor.getString(cursor.getColumnIndex(COLUMN_END_TIME)));
                shiftList.add(shift);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return shiftList;
    }

    // delete shift
    public void deleteShift(Shift shift) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHIFTS, COLUMN_START_TIME + " = ? AND " + COLUMN_END_TIME + " = ?",
                new String[]{shift.getStartTime(), shift.getEndTime()});
        db.close();
    }
}
