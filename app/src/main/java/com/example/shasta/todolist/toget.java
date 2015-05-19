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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toget, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
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
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRectoget(cursor.getLong(0));
            getadapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }

    }
}
