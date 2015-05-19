package com.example.shasta.todolist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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


public class todo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new todoFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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


    public static class todoFragment extends Fragment  implements AdapterView.OnItemLongClickListener{

        public todoFragment() {
        }
        provider db;
        todoAdapter todoadapter;
        Cursor cursor;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            db = new provider(getActivity());
            db.open();

            cursor = db.getTable(contract.todoEntry.TABLE_NAME);
            todoadapter = new todoAdapter(getActivity(), cursor, 0);
            View rootView = inflater.inflate(R.layout.fragment_todo, container, false);


            ListView listView = (ListView) rootView.findViewById(R.id.listViewtodo);
            listView.setAdapter(todoadapter);
            listView.setOnItemLongClickListener(this);
            final EditText todonm = (EditText) rootView.findViewById(R.id.edittodo);
            final EditText todotm = (EditText) rootView.findViewById(R.id.edittodotime);
            final EditText tododt = (EditText) rootView.findViewById(R.id.edittododate);

            Button button3 = (Button) rootView.findViewById(R.id.buttonaddtodo);
            button3.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String todo1 = todonm.getText().toString();
                            String tododate1 = tododt.getText().toString();
                            String todotime1 = todotm.getText().toString();
                            Log.v("bla",todo1+" "+tododate1 +" "+todotime1);
                            //db.addRectodo(,false);
                            todoadapter.notifyDataSetChanged();
                            todonm.setText("");
                            todotm.setText("");
                         //   tododt.setDate();
                            cursor.requery();
                        }

                    });


            return rootView;
        }
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRecTodo(cursor.getLong(0));
            todoadapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }
    }
}
