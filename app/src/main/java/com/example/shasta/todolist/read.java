package com.example.shasta.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shasta.todolist.data.provider;

public class read extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new readFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_read, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class readFragment extends Fragment implements AdapterView.OnItemLongClickListener {

        public readFragment() {
        }

        provider db;
        readAdapter readadapter;
        Cursor cursor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            db = new provider(getActivity());
            db.open();
            cursor = db.getAllDataRead();
            readadapter = new readAdapter(getActivity(), cursor, 0);
            View rootView = inflater.inflate(R.layout.fragment_read, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listViewRead);
            listView.setAdapter(readadapter);
            listView.setOnItemLongClickListener(this);
            final EditText name = (EditText) rootView.findViewById(R.id.editbook);
            final EditText author = (EditText) rootView.findViewById(R.id.editauthor);
            final EditText zhanr = (EditText) rootView.findViewById(R.id.editzhanr);
            Button button2 = (Button) rootView.findViewById(R.id.button_add_read);
            button2.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String name1 = name.getText().toString();
                            String author1 = author.getText().toString();
                            String zhanr1 = zhanr.getText().toString();
                            db.addRecRead(name1, author1, zhanr1, false);
                            readadapter.notifyDataSetChanged();
                            name.setText("");
                            author.setText("");
                            zhanr.setText("");
                            cursor.requery();
                        }

                    });
            return rootView;
        }

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

            AlertDialog.Builder alertDialog;
            alertDialog = new  AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Edit?");
            alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    db.delRecRead(cursor.getLong(0));
                    readadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    boolean c = false;
                    if (cursor.getInt(4) == 0)
                        c = false;
                    if (cursor.getInt(4) == 1)
                        c = true;
                    db.changeRead(c, cursor.getInt(0));
                    readadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.show();
            return true;
        }
    }
}