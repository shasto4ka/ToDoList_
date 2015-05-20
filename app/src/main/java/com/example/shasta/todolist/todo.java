package com.example.shasta.todolist;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Date;

public class todo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.todocontainer, new todoFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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

    public static class todoFragment extends Fragment implements AdapterView.OnItemLongClickListener {

        public todoFragment() {
        }

        @Override
        public void onDetach(){
            super.onDetach() ;
            db.close() ;
            cursor.close() ;
        }

        public String parseStr(String str) {
            String str1[] = str.split("[\\p{Punct}\\s]");
            String str2;
            str2 = str1[4] + "/" + str1[3] + "/" + str1[2] + " " + str1[0] + ":" + str1[1];
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

            Calendar c = Calendar.getInstance();
            final int hours = c.get(Calendar.HOUR_OF_DAY);
            final int minutes = c.get(Calendar.MINUTE);
            final int day = c.get(Calendar.DAY_OF_MONTH);
            final int month = c.get(Calendar.MONTH) + 1;
            final int year = c.get(Calendar.YEAR);



            todotm.setText(Integer.toString(hours) + ":" + Integer.toString(minutes) + " " + Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
            Button button3 = (Button) rootView.findViewById(R.id.buttonaddtodo);
            button3.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String todotime1 = todotm.getText().toString();
                            String todo1 = todonm.getText().toString();
                            String p = parseStr(todotime1);
                            db.addRectodo(todo1, p, false);

                            String str1[] = todotime1.split("[\\p{Punct}\\s]");
                            int int0= Integer.parseInt(str1[4]);
                            int int1= Integer.parseInt(str1[3]);
                            int int2= Integer.parseInt(str1[2]);
                            int int3= Integer.parseInt(str1[0]);
                            int int4= Integer.parseInt(str1[1]);
                            if((int0==year)&&(int1==(month+1))&&(int2==day)&&((60*int3+int4)>(hours*60+minutes)))
                            {
                                Date d = new Date();
                                d.setYear(int0);
                                d.setMonth(int1);
                                d.setDate(int2);
                                d.setHours(int3);
                                d.setMinutes(int4);
                                d.setSeconds(0);
                                todo t12 = new todo();
                                t12.restartNotify(d);
                            }
                            todoadapter.notifyDataSetChanged();
                            todonm.setText("");
                            Calendar c1 = Calendar.getInstance();
                            int hours1 = c1.get(Calendar.HOUR_OF_DAY);
                            int minutes1 = c1.get(Calendar.MINUTE);
                            int day1 = c1.get(Calendar.DAY_OF_MONTH);
                            int month1 = c1.get(Calendar.MONTH) + 1;
                            int year1 = c1.get(Calendar.YEAR);
                            todotm.setText(Integer.toString(hours1) + ":" + Integer.toString(minutes1) + " " + Integer.toString(day1) + "/" + Integer.toString(month1) + "/" + Integer.toString(year1));
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
                    db.delRecTodo(cursor.getLong(0));
                    todoadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cursor = (Cursor) parent.getItemAtPosition(position);
                    boolean c = false;
                    if (cursor.getInt(3) == 0)
                        c = false;
                    if (cursor.getInt(3) == 1)
                        c = true;
                    db.changetodo(c, cursor.getInt(0));
                    todoadapter.notifyDataSetChanged();
                    cursor.requery();
                }
            });
            alertDialog.show();
            return true;
        }
    }
    public void restartNotify(Date stamp) {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);;
        Intent intent = new Intent(this, TimeNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT );
        am.cancel(pendingIntent);
        am.set(AlarmManager.RTC_WAKEUP, stamp.getTime(), pendingIntent);
    }
}