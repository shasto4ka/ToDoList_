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
public class readAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView book_name;
        public final TextView book_zhanr;
        public final TextView id_view_book;
        public final TextView book_author;
        public final TextView book_read;

        public ViewHolder(View view) {
            book_name = (TextView) view.findViewById(R.id.read_name);
            book_author = (TextView) view.findViewById(R.id.read_author);
            book_zhanr = (TextView) view.findViewById(R.id.read_zhanr);
            id_view_book = (TextView) view.findViewById(R.id.read_id);
            book_read = (TextView) view.findViewById(R.id.read_yes);
        }
    }

    public readAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.read_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        int t = cursor.getInt(4) ;
        if (t==1)
        view.setBackgroundColor(Color.parseColor("#00FF00"));
        else {view.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        String item1 = cursor.getString(1);
        viewHolder.book_name.setText(item1);
        String item2 = cursor.getString(2);
        viewHolder.book_author.setText(item2);
        String item3 = cursor.getString(0);
        viewHolder.id_view_book.setText(item3);
        String item4 = cursor.getString(3);
        viewHolder.book_zhanr.setText(item4);
        String item5 = cursor.getString(4);
        viewHolder.book_read.setText(item5);
    }
}