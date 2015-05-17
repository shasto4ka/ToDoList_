package com.example.shasta.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Shasta on 17.05.2015.
 */
public class byeAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView prod_view;
        public final TextView id_view;

        public ViewHolder(View view) {
            prod_view = (TextView) view.findViewById(R.id.bye_item);
            id_view = (TextView) view.findViewById(R.id.bye_item_hidden);
        }
    }


    public byeAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.bye_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String item1 = cursor.getString(1);
        viewHolder.prod_view.setText(item1);
        String item2 = cursor.getString(0);
        viewHolder.id_view.setText(item2);
    }
}