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

    public void InsertData2(String s){
        myDb = getWritableDatabase();
        myDb.execSQL("INSERT INTO "+TABLE_NAME+" (task) VALUES('"+s+"');");

    }

    public long insertData(String item) {

        final ContentValues cv = new ContentValues(1);
        cv.put("task",item);

        myDb = getWritableDatabase();
        return myDb.insert("Tasks", null, cv);
    }

    public Cursor getAll(){
        myDb = this.getWritableDatabase();
        Cursor cursor= myDb.rawQuery("SELECT * from Tasks",null);
        return cursor;
        }

    public long Update(long id, String task){

        myDb = this.getWritableDatabase();

        final ContentValues cv = new ContentValues(1);
        cv.put("task", task);

        final String where = "_id=?";
        final String[] whereArgs = new String[] { String.valueOf(id) };

        final int numRowsUpdated = myDb.update("Tasks", cv, where, whereArgs);
        return numRowsUpdated;

    }

    public boolean DeleteTask(String item, int position){
        //Log.i(TAG, "DeleteTask: "+position);
        myDb = this.getWritableDatabase();
        final int numColsDeleted = myDb.delete("Tasks","_id=?",new String[]{position+""});
        Log.i(TAG, "DeleteTask: "+numColsDeleted);
        return (numColsDeleted > 0);
    }

}

