package com.oursky.todo_android.app;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.DatabaseHelper;
import com.oursky.todo_android.content.model.Task;
import com.oursky.todo_android.widget.ToDoItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class ToDoListActivity extends ListActivity implements ToDoItemAdapter.ToDoListFinishedListener {
    private ToDoItemAdapter adapter;
    private Button addButton, finishButton;
    private Context context;

    private List<Task> tasks = new ArrayList<>();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        context = this;
        finishButton = (Button) findViewById(R.id.to_do_finished_button);

        adapter = new ToDoItemAdapter(this);
        tasks = getDatabaseHelper().getAllUnfinishedtasks();
        adapter.addAll(tasks);
        View view = LayoutInflater.from(this).inflate(R.layout.partial_to_do_footer, getListView(), false);
        addButton = (Button) view.findViewById(R.id.partial_to_do_footer_add);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FinishedListActivity.class);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                Task task = new Task("Task " + count);
                tasks.add(task);
                getDatabaseHelper().createTask(task);
                adapter.clear();
                adapter.addAll(tasks);
            }
        });
        getListView().addFooterView(view);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTaskFinished(int position) {
        getDatabaseHelper().updateTask(tasks.remove(position));
        adapter.clear();
        adapter.addAll(tasks);
    }

    private DatabaseHelper getDatabaseHelper() {
        return ((BaseApplication) getApplication()).getDatabaseHelper();
    }
}
