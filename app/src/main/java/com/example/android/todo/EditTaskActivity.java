package com.example.android.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditTaskActivity extends AppCompatActivity {

    final String EDIT_EXTRA = "extra";
    final int EDIT_TASK_CODE = 2;
    int position;
    SQLiteDatabase myDB;
    final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText editText = (EditText) findViewById(R.id.editext_task);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        position = bundle.getInt("position");
        editText.setText(message);

        editText.requestFocus();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addtaskmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_task:
                EditText task2 = (EditText) findViewById(R.id.editext_task);
                String note2 = task2.getText().toString();
                Intent intent2 = new Intent();
                intent2.putExtra(EDIT_EXTRA, note2);
                setResult(EDIT_TASK_CODE, intent2);
                finish();
                break;

            case R.id.menu_delete_task:
                EditText task = (EditText) findViewById(R.id.editext_task);
                String note = task.getText().toString();

                dbhelper db = new dbhelper(this);
                db.DeleteTask(note,position);
                Log.i(TAG, "onOptionsItemSelected: "+position+note);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
