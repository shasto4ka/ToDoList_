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
import android.widget.RatingBar;

import com.example.shasta.todolist.data.provider;

public class bye extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bye);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new byeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bye, menu);
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
    public static  class byeFragment extends Fragment  implements AdapterView.OnItemLongClickListener{

        public byeFragment() {
        }

        provider db;
        byeAdapter byeadapter;
        Cursor cursor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            db = new provider(getActivity()) ;
            db.open() ;
            cursor = db.getAllDataBye() ;//!!!!
            byeadapter = new byeAdapter(getActivity(),cursor, 0);
            final View rootView = inflater.inflate(R.layout.fragment_bye, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.ListViewBye);
            listView.setAdapter(byeadapter);
            listView.setOnItemLongClickListener(this) ;
            final RatingBar rb = (RatingBar) rootView.findViewById(R.id.ratingBar);
            final EditText tv = (EditText) rootView.findViewById(R.id.editBye) ;
            Button button = (Button) rootView.findViewById(R.id.addbye);
            button.setOnClickListener(
                    new View.OnClickListener() {
                public void onClick(View v) {
                        String product = tv.getText().toString();
                        int priority ;
                    float rating = rb.getRating();
                    priority = Math.round(rating);
                    db.addRecBye(product,priority);
                    byeadapter.notifyDataSetChanged();
                    rb.setRating(0);
                    tv.setText("") ;
                    cursor.requery();
                    }

            });
               return rootView;
       }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            cursor = (Cursor) parent.getItemAtPosition(position);
            db.delRecBye(cursor.getLong(0));
            byeadapter.notifyDataSetChanged();
            cursor.requery();
            return true;
        }
    }
}

