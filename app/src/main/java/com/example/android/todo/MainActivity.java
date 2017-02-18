package com.example.android.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    dbhelper myDB;
    String s;
    int position;

    public final static String TAG = "tag";

    final int TO_DO_NOTE_REQUEST_CODE = 1;
    final String TO_DO_NOTE_TEXT_EXTRA = "to_do_extra";
    final String EDIT_EXTRA = "extra";
    final int EDIT_TASK_CODE = 2;

    ArrayAdapter<TodoItem> arrayAdapter;

    ArrayList<TodoItem> tasks = new ArrayList<TodoItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listview = (ListView) findViewById(R.id.list_view);
        myDB = new dbhelper(this);


        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, tasks);
        listview.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchAddToDoActivityIntent =
                        new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(launchAddToDoActivityIntent, TO_DO_NOTE_REQUEST_CODE);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view.findViewById(R.id.listview_item);
                s = tv.getText().toString();
                position = i;
                Log.i(TAG, "onItemClick: "+position);
                Intent launchActivityToEdit = new Intent(MainActivity.this,EditTaskActivity.class);
                launchActivityToEdit.putExtra("message", s);
                TodoItem ti = tasks.get(position);
                long row = ti.getId();
                launchActivityToEdit.putExtra("id",row);

                startActivityForResult(launchActivityToEdit,EDIT_TASK_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TO_DO_NOTE_REQUEST_CODE && data!=null) {
            final String note = data.getStringExtra(TO_DO_NOTE_TEXT_EXTRA);
            if(note != null && !note.trim().equals("")){
                long row = myDB.insertData(note);
                tasks.add(new TodoItem(row,note));
                arrayAdapter.notifyDataSetChanged();

            }
        }
        else if (requestCode == EDIT_TASK_CODE && data!=null){
            String response = data.getStringExtra(EDIT_EXTRA);
            if(!response.equals(s)) {
                TodoItem ti = tasks.get(position);
                long row = ti.getId();
                myDB.Update(row,response);
                //Log.i(TAG, "onActivityResult: "+response + "" +position);
                tasks.set(position,new TodoItem(row,response));
                arrayAdapter.notifyDataSetChanged();

            }
            else{
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();
        Cursor c = myDB.getAll();
        while(c.moveToNext()){
            long id = c.getInt(c.getColumnIndex("_id"));
            String tempTask = c.getString(c.getColumnIndex("task"));

            tasks.add(new TodoItem(id,tempTask));
            //Log.i(TAG,c.getString(0));
        }
        arrayAdapter.notifyDataSetChanged();
    }


}
