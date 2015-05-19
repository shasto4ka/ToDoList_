package com.example.shasta.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Shasta on 19.05.2015.
 */
public class todoAdapter  extends CursorAdapter {

    public static class ViewHolder {
        public final TextView todo_id;
        public final TextView todo_name;
        public final TextView tododatetime;
        public final TextView todoyes;


        public ViewHolder(View view) {
            todo_id  = (TextView) view.findViewById(R.id.texttodoid );
            todo_name = (TextView) view.findViewById(R.id.texttodoname);
            tododatetime = (TextView) view.findViewById(R.id.texttododate);
            todoyes = (TextView) view.findViewById(R.id.texttodoyes);
        }
    }


    public todoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemtodo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String item1 = cursor.getString(1);
        viewHolder.todo_name.setText(item1);
        String item2 = cursor.getString(2);
        String item22 = unparseStr(item2);
        viewHolder.tododatetime.setText(item22);
        String item3 = cursor.getString(0);
        viewHolder.todo_id.setText(item3);
        String item4 = cursor.getString(3);
        viewHolder.todoyes.setText(item4);
    }

    public String unparseStr(String str){
        String str1[] = str.split("[\\p{Punct}\\s]");
        String str2;
        str2 = str1[3]+":"+str1[4]+" "+str1[2]+"/"+str1[1]+"/"+str1[0];
        return str2;
    }
}