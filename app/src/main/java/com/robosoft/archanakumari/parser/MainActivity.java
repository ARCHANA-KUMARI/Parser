package com.robosoft.archanakumari.parser;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.robosoft.archanakumari.parser.Modal.ByArtistComparator;
import com.robosoft.archanakumari.parser.Modal.ByTitleComparator;
import com.robosoft.archanakumari.parser.Modal.Cummunicator;
import com.robosoft.archanakumari.parser.Modal.DateComparator;
import com.robosoft.archanakumari.parser.Modal.FilterModelClass;
import com.robosoft.archanakumari.parser.Modal.MovieDetails;
import com.robosoft.archanakumari.parser.Modal.SortModelClass;
import com.robosoft.archanakumari.parser.Modal.SortingCommunicator;
import com.robosoft.archanakumari.parser.Network.Downloader;
import com.robosoft.archanakumari.parser.adapter.ListViewAdapter;
import com.robosoft.archanakumari.parser.fragments.FilterByCategoryFragment;
import com.robosoft.archanakumari.parser.fragments.SortByFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements Cummunicator,SortingCommunicator {

    Toolbar toolbar;
    boolean status;
    ArrayList<FilterModelClass> nameList = new ArrayList<>();
    private ListView mListView;
    private String mUrl = "https://itunes.apple.com/us/rss/topvideorentals/limit=40/json";
    private ArrayList<MovieDetails> categoryList = new ArrayList<>();
    ArrayList<MovieDetails> mWholeList = new ArrayList<>();
    ArrayList<MovieDetails> tempList = new ArrayList<>();
    private HashMap<String,ArrayList<MovieDetails>> hashMap;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListView = (ListView)findViewById(R.id.listview);
        Downloader downloader = new Downloader(mUrl,mListView);
        downloader.execute();
        try {
            hashMap = downloader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Set keys = hashMap.entrySet();
        Iterator<MovieDetails> iterator = keys.iterator();
        while(iterator.hasNext()){
            Map.Entry pairs = (Map.Entry) iterator.next();
            String keyName = (String) pairs.getKey();
            tempList = (ArrayList<MovieDetails>) pairs.getValue();
            for(int i= 0;i<tempList.size();i++){
                mWholeList.add(tempList.get(i));
            }
            FilterModelClass filterModelClass = new FilterModelClass();
            filterModelClass.setmCategoryname((String) pairs.getKey());
            filterModelClass.setmCount((Integer)((ArrayList<MovieDetails>) pairs.getValue()).size());
            filterModelClass.setmSelectedValue(false);
            nameList.add(filterModelClass);

        }
        categoryList = checkedListByCategory();
        //Initially adapter setting wi
        if(status == false) {
            listViewAdapter = new ListViewAdapter(this, -1, categoryList);
            mListView.setAdapter(listViewAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filterby) {

            FilterByCategoryFragment filterByCategoryFragment = new FilterByCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Name",nameList);
            filterByCategoryFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, filterByCategoryFragment);
            fragmentTransaction.commit();
            return true;
        }
        if(id == R.id.sortby){

            SortByFragment sortByFragment = new SortByFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, sortByFragment);
            fragmentTransaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void toCommunicate(ArrayList<FilterModelClass> arrayList) {

        checkedListByCategory();
        if(status)
        {
            mListView.setAdapter(listViewAdapter);
            listViewAdapter.notifyDataSetChanged();

        }
        if(status == false){
            checkedListByCategory();
            mListView.setAdapter(listViewAdapter);
            listViewAdapter.notifyDataSetChanged();
        }

    }


   //Fetching data based on checkBox checked status
    public ArrayList<MovieDetails> checkedListByCategory(){

        categoryList.clear();
        Set keys = hashMap.entrySet();
        Iterator<MovieDetails> iterator = keys.iterator();
        int count = 0;
        while(iterator.hasNext()){
            Map.Entry pairs = (Map.Entry) iterator.next();
            String keyName = (String) pairs.getKey();
            tempList = (ArrayList<MovieDetails>) pairs.getValue();
            if(nameList.get(count).ismSelectedValue()==true){
                status = true;
                for(int i= 0;i<tempList.size();i++) {
                    categoryList.add(tempList.get(i));
                }

            }
            count++;

        }
       if(status){
           return categoryList;
       }
       else {

           categoryList.clear();
           categoryList.addAll(mWholeList);
           return categoryList;

       }


    }

    //For sorting
    @Override
    public void toCommunicateSortedList(ArrayList<SortModelClass> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            SortModelClass sortModelClass = arrayList.get(i);
            //Sorting based on title
            if (sortModelClass.isSortrtitle()) {

                Collections.sort(categoryList, new ByTitleComparator());
                mListView.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
            //sorting based on artist
            if (sortModelClass.isSortArtist()) {

                Collections.sort(categoryList, new ByArtistComparator());
                mListView.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
            //sorting based on date
            if (sortModelClass.isSortDate()) {
                Collections.sort(categoryList, new DateComparator());
                mListView.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
        }
    }
}

