package com.example.shasta.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shasta on 12.05.2015.
 */
public class Fragment1 extends Fragment {

    ArrayAdapter<String> adapter;
    private final String LOG_TAG = MainActivity.class.getSimpleName();


    public Fragment1() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment1, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
   /*     if (id == R.id.action_refresh) {
            FetchWeatherTask weatherTask = new FetchWeatherTask();
            weatherTask.execute("94043");
            return true;}*/

        return super.onOptionsItemSelected(item);
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            String[] lists = getResources().getStringArray(R.array.lists);
            List<String > list=new ArrayList<String>(Arrays.asList(lists));

            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list) ;

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView listView = (ListView)rootView.findViewById(R.id.listView);
            listView.setAdapter(adapter) ;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String forecast = adapter.getItem(position);
                    Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();
                    Intent intent;
                   switch (position) {
                       case 0: {
                           intent = new Intent(getActivity(), bye.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }
                       case 2: {
                           intent = new Intent(getActivity(), film.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }
                       case 3: {
                           intent = new Intent(getActivity(), todo.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }
                       case 4: {
                           intent = new Intent(getActivity(), toget.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }
                       case 5: {
                           intent = new Intent(getActivity(), Password.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }
                       case 1: {
                           intent = new Intent(getActivity(), read.class)
                                   .putExtra(Intent.EXTRA_TEXT, forecast);
                           startActivity(intent) ;
                           break;
                       }

                   }

                }
            });

            return rootView;
        }
    }



