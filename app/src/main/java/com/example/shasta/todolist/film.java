package com.example.shasta.todolist;

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

import com.example.shasta.todolist.data.contract;
import com.example.shasta.todolist.data.provider;


public class film extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new filmFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_film, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

            cursor = db.getTable(contract.FilmEntry.TABLE_NAME);
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
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRecFilm(cursor.getLong(0));
            filmadapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }
    }
}
