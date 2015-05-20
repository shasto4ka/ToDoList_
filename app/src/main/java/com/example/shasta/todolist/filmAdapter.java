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
public class filmAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final TextView film_name;
        public final TextView film_zhanr;
        public final TextView id_view_film;
        public final TextView film_seen;
        public ViewHolder(View view) {
            film_name = (TextView) view.findViewById(R.id.film_item_text);
            film_zhanr = (TextView) view.findViewById(R.id.film_item_zhanr);
            id_view_film = (TextView) view.findViewById(R.id.film_item_id);
            film_seen = (TextView) view.findViewById(R.id.film_yes_see);
        }
    }

    public filmAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.film_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int t = cursor.getInt(3) ;
        if (t==1)
            view.setBackgroundColor(Color.parseColor("#00FF00"));
        else {view.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        String item1 = cursor.getString(1);
        viewHolder.film_name.setText(item1);
        String item2 = cursor.getString(2);
        viewHolder.film_zhanr.setText(item2);
        String item3 = cursor.getString(0);
        viewHolder.id_view_film.setText(item3);
        String item4 = cursor.getString(3);
        viewHolder.film_seen.setText(item4);
    }
}