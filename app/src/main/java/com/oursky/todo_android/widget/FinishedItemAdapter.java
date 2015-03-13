package com.oursky.todo_android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.oursky.todo_android.R;
import com.oursky.todo_android.content.model.Item;

/**
 * Created by yuyauchun on 13/3/15.
 */
public class FinishedItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private LayoutInflater inflater;

    public FinishedItemAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.partial_finished_item, parent, false);
            convertView.setTag(new FinishedItemViewHolder(convertView));
        }
        FinishedItemViewHolder holder = (FinishedItemViewHolder) convertView.getTag();

        Item item = getItem(position);
        holder.task.setText(item.getTask());
        return convertView;
    }

    public class FinishedItemViewHolder {
        public TextView task;

        public FinishedItemViewHolder(View view) {
            task = (TextView) view.findViewById(R.id.partial_finished_task);
        }
    }
}
