package com.example.android.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    dbhelper myDB;

    public final static String TAG = "tag";

    final int TO_DO_NOTE_REQUEST_CODE = 1;
    final String TO_DO_NOTE_TEXT_EXTRA = "to_do_extra";
    ArrayAdapter arrayAdapter;

    ArrayList<String> tasks = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listview = (ListView) findViewById(R.id.list_view);
        myDB = new dbhelper(this);
        Cursor c = myDB.getAll();
        //String[] test = {"this","that","something","again","something"};

        ArrayList<String> tasks = new ArrayList<>();
        //tasks.add("Get Laundry done");
        //tasks.add("Milk");
        //tasks.add("Collect photos");
        //tasks.add("Call Mom");

        //arrayAdapter = new ArrayAdapter(this,R.layout.list_item,tasks);

        //listview.setAdapter(arrayAdapter);

        while(c.moveToNext()){
            tasks.add(c.getString(0));
            Log.i(TAG,c.getString(0));


        }

        ListAdapter listAdapter = new ArrayAdapter<>(this,R.layout.list_item,tasks);

        listview.setAdapter(listAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchAddToDoActivityIntent =
                        new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(launchAddToDoActivityIntent, TO_DO_NOTE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TO_DO_NOTE_REQUEST_CODE && data!=null) {
            final String note = data.getStringExtra(TO_DO_NOTE_TEXT_EXTRA);
            tasks.add(note);
            arrayAdapter.notifyDataSetChanged();
            myDB.InsertData(note);
        }
        else{
            return;
        }
    }
}
