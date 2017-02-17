package com.example.android.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by anuj on 16/2/17.
 */

public class dbhelper extends SQLiteOpenHelper {

    SQLiteDatabase myDb;


    final static private String DB_NAME = "Todo";
    final String TAG = "TAG";

    final static private String TABLE_NAME = "Tasks";

    final static private int DB_VER = 1;

    dbhelper(Context context){
        super(context,DB_NAME,null,DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,task TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }

    public void InsertData(String s){
        myDb = getWritableDatabase();
        myDb.execSQL("INSERT INTO "+TABLE_NAME+" (task) VALUES('"+s+"');");

    }

    public Cursor getAll(){
        myDb = this.getWritableDatabase();
        Cursor cursor= myDb.rawQuery("SELECT task from "+TABLE_NAME,null);
        return cursor;
        }

    public boolean Update(String item, int position){
        myDb = this.getWritableDatabase();
        String strSQL = "UPDATE Tasks SET task =\""+item+"\" WHERE _id = "+ position;
        myDb.execSQL(strSQL);
        return true;

    }

    public boolean DeleteTask(String item, int position){
        //Log.i(TAG, "DeleteTask: "+position);
        String s = position+"";
        myDb = this.getWritableDatabase();
        final int numColsDeleted = myDb.delete("Tasks","task=?",new String[]{item});
        Log.i(TAG, "DeleteTask: "+numColsDeleted);
        return (numColsDeleted > 0);
    }

}

