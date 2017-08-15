package com.android.example.habitfinal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.example.habitfinal.data.HabitDbHelper;
import com.android.example.habitfinal.data.HabitContract.HabitEntray;

public class EditorActivity extends AppCompatActivity {
    private EditText habitText;
    private EditText caloriesText;
    private String habitTextString=null;
    private String caloriesTextString;
    private HabitDbHelper habitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        habitText = (EditText) findViewById(R.id.nameofactivity);
        caloriesText = (EditText) findViewById(R.id.calories);
        Button button = (Button) findViewById(R.id.addineditor);
        habitDbHelper = new HabitDbHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHabit();
                finish();
            }
        });
    }

    private void insertHabit() {
        SQLiteDatabase db = habitDbHelper.getWritableDatabase();
        Log.i("Value of habit is",""+habitTextString);
        habitTextString = habitText.getText().toString().trim();
        caloriesTextString = caloriesText.getText().toString().trim();
        Log.i("the activity name:","ye hai"+habitTextString.length());
        int calories = Integer.parseInt(caloriesTextString);
        ContentValues values = new ContentValues();
        if(habitTextString.length()==0){
            throw new IllegalArgumentException("Pet requires a name");
        }
        values.put(HabitEntray.HABIT_ACTIVITY_COLUMN, habitTextString);
        values.put(HabitEntray.HABIT_CALORIES_BURNT, calories);
        long newRowId = db.insert(HabitEntray.TABLE_NAME, null, values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
