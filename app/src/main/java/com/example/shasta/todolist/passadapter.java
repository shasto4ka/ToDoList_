package com.example.shasta.todolist;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shasta on 19.05.2015.
 */
public class passadapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView site;
        public final TextView login;
        public final TextView password;
        public final TextView id_pass;

        public ViewHolder(View view) {
            site = (TextView) view.findViewById(R.id.passtextsite);
            login = (TextView) view.findViewById(R.id.textpasslogin);
            password = (TextView) view.findViewById(R.id.textpasspass);
            id_pass = (TextView) view.findViewById(R.id.textpassid);
        }
    }

    public passadapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.pass_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String item1 = cursor.getString(1);
        viewHolder.site.setText(item1);
        String item2 = cursor.getString(0);
        viewHolder.id_pass.setText(item2);
        String item3 = cursor.getString(2);
        viewHolder.login.setText(item3);
        String item4 = cursor.getString(3);
        viewHolder.password.setText(item4);
    }
}