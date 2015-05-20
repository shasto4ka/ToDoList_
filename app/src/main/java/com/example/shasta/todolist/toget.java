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

public class toget extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toget);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new togetFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toget, menu);
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

    public static class togetFragment extends Fragment implements AdapterView.OnItemLongClickListener{

        public togetFragment() {
        }

        provider db;
        togetAdapter  getadapter;
        Cursor cursor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            db = new provider(getActivity());
            db.open();
            cursor = db.getAllDatatoget();
            getadapter = new togetAdapter(getActivity(), cursor, 0);
            View rootView = inflater.inflate(R.layout.fragment_toget, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listgoal);
        listView.setAdapter(getadapter);
        listView.setOnItemLongClickListener(this);
        final EditText goaladd = (EditText) rootView.findViewById(R.id.editgoal);
        Button button8 = (Button) rootView.findViewById(R.id.buttongoal);
        button8.setOnClickListener(
                new View.OnClickListener() {
            public void onClick(View v) {
                String goal1 = goaladd.getText().toString();
                db.addRectoget(goal1, false);
                getadapter.notifyDataSetChanged();
                goaladd.setText("");
                cursor.requery();
            }

        });
            return rootView;
        }

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialog;
            alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Edit?");
            alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    db.delRectoget(cursor.getLong(0));
                    getadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    boolean c = false;
                    if (cursor.getInt(2) == 0)
                        c = false;
                    if (cursor.getInt(2) == 1)
                        c = true;
                    db.changetoget(c, cursor.getInt(0));
                    getadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.show();
            return true;
        }
    }
}