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
import android.widget.ListView;

import com.example.shasta.todolist.data.contract;
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bye, menu);
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
            /*db.addRecBye("milk",5) ;
            db.addRecBye("coffe",4) ;
            db.addRecBye("soda",5) ;
            db.addRecBye("farch",5) ;
            db.addRecBye("vhiskie",4) ;
*/
            cursor = db.getTable(contract.ByeEntry.TABLE_NAME);

            byeadapter = new byeAdapter(getActivity(),cursor, 0);
            final View rootView = inflater.inflate(R.layout.fragment_bye, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.ListViewBye);
            listView.setAdapter(byeadapter);
            listView.setOnItemLongClickListener(this) ;


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

