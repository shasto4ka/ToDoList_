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

public class film extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.filmcontainer, new  filmFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_film, menu);
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

    public static class filmFragment extends Fragment implements AdapterView.OnItemLongClickListener {

        public filmFragment() {
        }

        provider db;
        filmAdapter filmadapter;
        Cursor cursor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            db = new provider(getActivity());
            db.open();
            cursor = db.getAllDataFilm() ;
            filmadapter = new filmAdapter(getActivity(), cursor, 0);
            View rootView = inflater.inflate(R.layout.fragment_film, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listViewFilm);
            listView.setAdapter(filmadapter);
            listView.setOnItemLongClickListener(this);
            final EditText etfilm = (EditText) rootView.findViewById(R.id.editfilmtext);
            final EditText etfilmzhanr = (EditText) rootView.findViewById(R.id.editfilmzhanr);
            Button button1 = (Button) rootView.findViewById(R.id.film_add_butt);
            button1.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String film = etfilm.getText().toString();
                            String zhanr = etfilmzhanr.getText().toString();
                            db.addRecFilm(film, zhanr, false);
                            filmadapter.notifyDataSetChanged();
                            etfilm.setText("");
                            etfilmzhanr.setText("");
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
                    db.delRecFilm(cursor.getLong(0));
                    filmadapter.notifyDataSetChanged();
                    cursor.requery();

                } });
            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    boolean c=false;
                    if (cursor.getInt(3)==0)
                        c=false;
                    if (cursor.getInt(3)==1)
                        c=true;
                    db.changeFilm(c,cursor.getInt(0));
                    filmadapter.notifyDataSetChanged();
                    cursor.requery();
                } });
            alertDialog.show();
            return true;
        }
    }
}
