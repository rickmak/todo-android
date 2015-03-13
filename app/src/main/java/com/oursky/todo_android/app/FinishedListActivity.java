package com.oursky.todo_android.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.model.Item;
import com.oursky.todo_android.widget.FinishedItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class FinishedListActivity extends ListActivity {
    private FinishedItemAdapter adapter;

    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_list);

        String task = getIntent().getStringExtra("finished");
        if (task != null) {
            Item item = new Item(task);
            items.add(item);
        }

        adapter = new FinishedItemAdapter(this);
        adapter.addAll(items);

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
}
