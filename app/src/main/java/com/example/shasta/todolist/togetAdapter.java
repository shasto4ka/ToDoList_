package com.example.shasta.todolist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Shasta on 18.05.2015.
 */
public class togetAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView goal_name;
        public final TextView goal_id;
        public final TextView goal_yes;

        public ViewHolder(View view) {
            goal_name = (TextView) view.findViewById(R.id.goal);
            goal_id = (TextView) view.findViewById(R.id.goalid);
            goal_yes = (TextView) view.findViewById(R.id.goal_yes);
        }
    }

    public togetAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.toget_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int t = cursor.getInt(2) ;
        if (t==1)
            view.setBackgroundColor(Color.parseColor("#ADFF2F"));
        else {view.setBackgroundColor(Color.parseColor("#FA8072"));
        }
        String item1 = cursor.getString(1);
        viewHolder.goal_name.setText(item1);
        String item2 = cursor.getString(2);
        viewHolder.goal_yes.setText(item2);
        String item3 = cursor.getString(0);
        viewHolder.goal_id.setText(item3);
    }
}