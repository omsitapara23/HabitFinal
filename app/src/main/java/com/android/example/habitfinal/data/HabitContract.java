package com.android.example.habitfinal.data;

import android.provider.BaseColumns;

/**
 * Created by root on 13/8/17.
 */

public class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntray implements BaseColumns {
        public static final String TABLE_NAME = "Habit";
        public static final String _ID = BaseColumns._ID;
        public static final String HABIT_ACTIVITY_COLUMN = "Activity";
        public static final String HABIT_CALORIES_BURNT = "Calories";
    }
}
