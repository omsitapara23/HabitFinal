package com.android.example.habitfinal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 13/8/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SQL_TABLE = "CREATE TABLE " + HabitContract.HabitEntray.TABLE_NAME + " ("
                + HabitContract.HabitEntray._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntray.HABIT_ACTIVITY_COLUMN + " TEXT NOT NULL, "
                + HabitContract.HabitEntray.HABIT_CALORIES_BURNT + " INTEGER );";

        db.execSQL(CREATE_SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
