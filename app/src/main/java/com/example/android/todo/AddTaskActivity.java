package com.example.android.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    public final int TO_DO_NOTE_REQUEST_CODE = 1;
    public final String TO_DO_NOTE_TEXT_EXTRA = "to_do_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



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
                EditText task = (EditText) findViewById(R.id.editext_task);
                String note = task.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(TO_DO_NOTE_TEXT_EXTRA, note);
                setResult(TO_DO_NOTE_REQUEST_CODE, intent);
                finish();
                break;

            case R.id.menu_delete_task:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
