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

import java.util.Calendar;


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

        public String parseStr(String str){
            String str1[] = str.split("[\\p{Punct}\\s]");
            String str2;
            str2 = str1[4]+"/"+str1[3]+"/"+str1[2]+" "+str1[0]+":"+str1[1];
            return str2;
        }
        provider db;
        todoAdapter todoadapter;
        Cursor cursor;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            db = new provider(getActivity());
            db.open();

            cursor = db.getAllDatatodo();
            todoadapter = new todoAdapter(getActivity(), cursor, 0);
            View rootView = inflater.inflate(R.layout.fragment_todo, container, false);


            ListView listView = (ListView) rootView.findViewById(R.id.listViewtodo);
            listView.setAdapter(todoadapter);
            listView.setOnItemLongClickListener(this);
            final EditText todonm = (EditText) rootView.findViewById(R.id.edittodo);
            final EditText todotm = (EditText) rootView.findViewById(R.id.edittodotime);

            Calendar c = Calendar.getInstance() ;
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minutes = c.get(Calendar.MINUTE);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH)+1;
            int year = c.get(Calendar.YEAR);
            todotm.setText(Integer.toString(hours)+":"+Integer.toString(minutes)+" "+Integer.toString(day )+ "/"+Integer.toString(month)+"/"+Integer.toString(year));

            Button button3 = (Button) rootView.findViewById(R.id.buttonaddtodo);
            button3.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String todotime1 = todotm.getText().toString();
                            String todo1 = todonm.getText().toString();
                            String p = parseStr(todotime1);
                            db.addRectodo(todo1,p,false);
                            todoadapter.notifyDataSetChanged();

                            todonm.setText("");
                            Calendar c1 = Calendar.getInstance() ;
                            int hours1 = c1.get(Calendar.HOUR_OF_DAY);
                            int minutes1 = c1.get(Calendar.MINUTE);
                            int day1 = c1.get(Calendar.DAY_OF_MONTH);
                            int month1 = c1.get(Calendar.MONTH)+1;
                            int year1 = c1.get(Calendar.YEAR);
                            todotm.setText(Integer.toString(hours1)+":"+Integer.toString(minutes1)+" "+Integer.toString(day1 )+ "/"+Integer.toString(month1)+"/"+Integer.toString(year1));

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
