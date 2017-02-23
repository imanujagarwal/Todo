package com.example.android.todo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android.todo.Data.todoItemContract.todoItem;


public class dbhelper extends SQLiteOpenHelper {

    public SQLiteDatabase myDb;


    final static private String DB_NAME = "Todo.db";
    final String TAG = "TAG";
    final static private int DB_VER = 1;


    public dbhelper(Context context){
        super(context,DB_NAME,null,DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + todoItem.TABLE_NAME + "("
                + todoItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + todoItem.COLUMN_TASK_NAME + " TEXT);";


        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+todoItem.TABLE_NAME);
        onCreate(db);

    }

    public long insertData(String item) {

        final ContentValues cv = new ContentValues(1);
        cv.put(todoItem.COLUMN_TASK_NAME,item);

        myDb = getWritableDatabase();
        long t = myDb.insert(todoItem.TABLE_NAME, null, cv);
        Log.i(TAG, "insertData: "+t);
        return t;
    }

    public Cursor getAll(){
        myDb = this.getWritableDatabase();
        Cursor cursor= myDb.rawQuery("SELECT * from "+todoItem.TABLE_NAME,null);
        return cursor;
        }

    public long Update(long id, String task){

        myDb = this.getWritableDatabase();

        final ContentValues cv = new ContentValues(1);
        cv.put(todoItem.COLUMN_TASK_NAME, task);

        final String where = "_ID=?";
        final String[] whereArgs = new String[] { String.valueOf(id) };

        final int numRowsUpdated = myDb.update(todoItem.TABLE_NAME, cv, where, whereArgs);
        return numRowsUpdated;

    }

    public boolean DeleteTask(String item, Long position){
        //Log.i(TAG, "DeleteTask: "+position);
        myDb = this.getWritableDatabase();
        final int numColsDeleted = myDb.delete(todoItem.TABLE_NAME,"_ID=?",new String[]{String.valueOf(position)});
        //Log.i(TAG, "DeleteTask: "+numColsDeleted);
        return (numColsDeleted > 0);
    }

}

