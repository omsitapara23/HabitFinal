package com.android.example.habitfinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.example.habitfinal.data.HabitContract;
import com.android.example.habitfinal.data.HabitDbHelper;

public class CatalogActivity extends AppCompatActivity {
    private HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Button button = (Button) findViewById(R.id.add);
        habitDbHelper = new HabitDbHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayInfo();
    }

    private void displayInfo() {
        SQLiteDatabase database = habitDbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + HabitContract.HabitEntray.TABLE_NAME, null);
        try {
            TextView textView = (TextView) findViewById(R.id.textviewtoall);
            textView.setText("The habit app contains " + cursor.getCount() + " activites\n\n");
            textView.append(HabitContract.HabitEntray._ID + "-"
                    + HabitContract.HabitEntray.HABIT_ACTIVITY_COLUMN + "-"
                    + HabitContract.HabitEntray.HABIT_CALORIES_BURNT + "\n\n");

            int columnIndex = cursor.getColumnIndex(HabitContract.HabitEntray._ID);
            int activityIndex = cursor.getColumnIndex(HabitContract.HabitEntray.HABIT_ACTIVITY_COLUMN);
            int caloriesIndex = cursor.getColumnIndex(HabitContract.HabitEntray.HABIT_CALORIES_BURNT);
            while (cursor.moveToNext()) {

                int CurrentId = cursor.getInt(columnIndex);
                String activity = cursor.getString(activityIndex);
                int calories = cursor.getInt(caloriesIndex);
                textView.append(CurrentId + "-" + activity + "-" + calories + "\n\n");
            }

        } finally {
            cursor.close();
        }
    }
}
