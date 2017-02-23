package com.example.android.todo.Data;

import android.provider.BaseColumns;

/**
 * Created by anuj on 23/2/17.
 */


public final class todoItemContract {


    private todoItemContract() {}


    public static final class todoItem implements BaseColumns {

        public final static String DB_NAME = "Main.db";

        /** Name of database table for tasks */
        public final static String TABLE_NAME = "Tasks";

        /**
         * Unique ID number(only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the Task.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TASK_NAME ="taskString";


    }

}