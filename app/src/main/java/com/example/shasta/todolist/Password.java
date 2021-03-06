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

public class Password extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.passcontainer, new passFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_password, menu);
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

    public static class passFragment extends Fragment implements AdapterView.OnItemLongClickListener{

        public passFragment() {
        }

        @Override
        public void onDetach(){
            super.onDetach() ;
            db.close() ;
            cursor.close() ;
        }

        provider db;
        passadapter padapter;
        Cursor cursor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            db = new provider(getActivity());
            db.open();
            cursor = db.getAllDatapass() ;
            padapter = new passadapter(getActivity(), cursor, 0) ;
            View rootView = inflater.inflate(R.layout.fragment_password, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listViewpass);
            listView.setAdapter(padapter );
            listView.setOnItemLongClickListener(this);
            final EditText addsite = (EditText) rootView.findViewById(R.id.editTextsite);
            final EditText addlogin = (EditText) rootView.findViewById(R.id.editTextlogin);
            final EditText addpassword = (EditText) rootView.findViewById(R.id.editTextpass);
            Button button4 = (Button) rootView.findViewById(R.id.buttonaddpass);
            button4.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            String site1 = addsite.getText().toString();
                            String login1 = addlogin.getText().toString();
                            String pass1 = addpassword.getText().toString();
                            db.addRecrass(site1,login1,pass1);
                            padapter.notifyDataSetChanged();
                            addsite.setText("");
                            addlogin.setText("");
                            addpassword.setText("");
                            cursor.requery();
                        }

                    });
            return rootView;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRecPass(cursor.getLong(0)) ;
            padapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }
    }
}