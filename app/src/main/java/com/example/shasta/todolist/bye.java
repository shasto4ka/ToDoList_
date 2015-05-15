package com.example.shasta.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public static  class byeFragment extends Fragment{



        public byeFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bye, container, false);


            provider db = new provider(getActivity()) ;
            db.open() ;
            int col=1;
            ContentValues cv = new ContentValues() ;
            Cursor cursor = db.getTable(contract.ByeEntry.TABLE_NAME);
            String[] myInts = new String[cursor.getCount()]; // объявляем массив

            if (cursor.moveToFirst()) // если курсор не пустой
            {
                for (int i = 0; i < cursor.getCount(); i++)
                {
                    myInts[i] = cursor.getString(col); // заполняем
                    cursor.moveToNext();
                }
            }
            cursor.close();
            db.close();

            ListView listView = (ListView) rootView.findViewById(R.id.ListViewAdd);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1 , myInts);
            listView.setAdapter(adapter) ;

    return rootView;
        }
    }
}
