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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read, menu);
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
            db.addRecRead("book","author","zhanr",false);
            db.addRecRead("book2","author2","zhanr2",false);
            db.addRecRead("book3","author3","zhanr3",false);

            cursor = db.getTable(contract.ReadEntry.TABLE_NAME);
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
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRecRead(cursor.getLong(0));
            readadapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }
    }
}
