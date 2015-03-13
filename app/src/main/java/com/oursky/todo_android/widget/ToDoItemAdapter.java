package com.oursky.todo_android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.model.Item;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class ToDoItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private LayoutInflater inflater;
    private ToDoListFinishedListener listener;

    public ToDoItemAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        try {
            listener = (ToDoListFinishedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement ToDoListFinishedListener.");
        }
    }

    public interface ToDoListFinishedListener {
        public void setTaskFinished(int position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.partial_to_do_item, parent, false);
            convertView.setTag(new ToDoItemViewHolder(convertView));
        }
        ToDoItemViewHolder holder = (ToDoItemViewHolder) convertView.getTag();

        final Item item = getItem(position);
        holder.task.setText(item.getTask());
        holder.checkBox.setChecked(item.isTaskFinished());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setisFinished(isChecked);
                if (isChecked) {
                    listener.setTaskFinished(position);
                }
            }
        });
        return convertView;
    }

    public class ToDoItemViewHolder {
        public TextView task;
        public CheckBox checkBox;

        public ToDoItemViewHolder(View view) {
            task = (TextView) view.findViewById(R.id.partial_to_do_task);
            checkBox = (CheckBox) view.findViewById(R.id.partial_to_do_checkbox);
        }
    }
}
