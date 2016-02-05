package com.robosoft.archanakumari.parser;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.robosoft.archanakumari.parser.Modal.DateComparator;
import com.robosoft.archanakumari.parser.Modal.MovieDetails;
import com.robosoft.archanakumari.parser.Modal.StatusClass;
import com.robosoft.archanakumari.parser.adapter.ListViewAdapter;
import com.robosoft.archanakumari.parser.fragments.FilterByCategory;
import com.robosoft.archanakumari.parser.fragments.SortByFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ListView mListView;

    private  boolean mActionstatus,mComedystatus,mDramastatus,mKidsstatus,mSciStatus,mThrillerStatus;
    private boolean mTitleStatus,mArtistStatus,mDateStatus;
    private List<StatusClass> mStatusList = new ArrayList<>();
    public  static int countAction,countComedy,countDrama,countKids,countScFI,countThriller;
    public static String moviesAction,moviesComedy,moviesDrama,moviesKids,moviesScFi,moviesThriller;
    private List<MovieDetails> mCheckedList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListView = (ListView)findViewById(R.id.listview);
        Downloader downloader = new Downloader();
        downloader.execute();
        Intent intent = getIntent();
        if(null != intent )
        {
             mActionstatus = intent.getBooleanExtra("Action",false);
             mComedystatus = intent.getBooleanExtra("Comedy",false);
             mDramastatus = intent.getBooleanExtra("Drama",false);
             mKidsstatus = intent.getBooleanExtra("Kids",false);
             mSciStatus = intent.getBooleanExtra("Sci-Fi",false);
             mThrillerStatus = intent.getBooleanExtra("Thriller",false);
             mTitleStatus = intent.getBooleanExtra("Title",false);
             mArtistStatus = intent.getBooleanExtra("Artist",false);
             mDateStatus = intent.getBooleanExtra("Date",false);
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

            FilterByCategory filterByCategory = new FilterByCategory();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,filterByCategory);
            fragmentTransaction.commit();
            return true;
        }
        if(id == R.id.sortby){

            SortByFragment sortByFragment = new SortByFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,sortByFragment);
            fragmentTransaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Downloader extends AsyncTask<Void,Void,List<MovieDetails>>{

       private List<MovieDetails> mList = new ArrayList<>();
       private String mJsonData;
       private JSONObject mJsonRootObject;
       private JSONArray mJsonArray;

       @Override
       protected List<MovieDetails> doInBackground(Void... params) {

           try {

               URL url = new URL("https://itunes.apple.com/us/rss/topvideorentals/limit=40/json");
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               InputStream inputStream = httpURLConnection.getInputStream();
               mJsonData = readStream(inputStream);
               mJsonRootObject = new JSONObject(mJsonData);
               //for getting root object
               JSONObject jsonFeed = mJsonRootObject.getJSONObject("feed");
               mJsonArray = jsonFeed.getJSONArray("entry");
               for (int i = 0; i<mJsonArray.length(); i++) {

                   JSONObject jsonObject = mJsonArray.getJSONObject(i);
                   //for getting title object
                   JSONObject jsonTitleObject = jsonObject.getJSONObject("title");
                   String title = jsonTitleObject .getString("label");
                   //for getting im:artist object
                   JSONObject jsonObjectImArtist = jsonObject.getJSONObject("im:artist");
                   String artist = jsonObjectImArtist.getString("label");
                   //for getting releaseDateObject
                   JSONObject jsonObjectDate = jsonObject.getJSONObject("im:releaseDate");
                   String releaseDate = jsonObjectDate.getString("label");
                  //String manipulation for changing  date into dd//mm//yyyyy format
                   String dd = releaseDate.substring(8,10);
                   String mm = releaseDate.substring(5,7);
                   String yyyy = releaseDate.substring(0,4);
                   releaseDate = dd+":"+mm+":"+yyyy;
                   //for getting im:price object
                   JSONObject jsonObjectPrice = jsonObject.getJSONObject("im:price");
                   String price = jsonObjectPrice.getString("label");
                   //for category
                   JSONObject jsonObjectCategory = jsonObject.getJSONObject("category");
                   JSONObject jsonObjectAttribute = jsonObjectCategory.getJSONObject("attributes");
                   String category = jsonObjectAttribute .getString("label");
                   //for counting no of data into  categories
                   if (category.equals("Action & Adventure")) {
                           moviesAction = category;

                           countAction++;
                       } else if (category.equals("Comedy")) {
                           moviesComedy = category;
                           countComedy++;
                       } else if (category.equals("Drama")) {
                           moviesDrama = category;
                           countDrama++;
                       } else if (category.equals("Kids & Family")) {
                           moviesKids = category;
                           countKids++;
                       } else if (category.equals("Sci-Fi & Fantasy")) {
                           moviesScFi = category;
                           countScFI++;
                       } else if (category.equals("Thriller")) {
                           moviesThriller = category;
                           countThriller++;
                       }

                   String image = null;
                   //for getting image object
                   JSONArray jsonArrayImage = jsonObject.getJSONArray("im:image");
                   //for getting last image url
                   for(int j= 0;j<jsonArrayImage.length();j++){
                       if(j==2){
                           JSONObject jsonObjectImageLabel = jsonArrayImage.getJSONObject(j);
                         String   imageofLastIndex = jsonObjectImageLabel.getString("label");
                           image = imageofLastIndex;
                       }

                   }
                   //adding downloaded data into list based on checked checkbox
                   if(category.equals("Action & Adventure")&&mActionstatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   if(category.equals("Comedy")&&mComedystatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   if(category.equals("Drama")&&mDramastatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   if(category.equals("Kids & Family")&&mKidsstatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   if(category.equals("Sci-Fi & Fantasy")&&mSciStatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   if(category.equals("Thriller")&&mThrillerStatus){
                       mCheckedList.add(new MovieDetails(title,artist,releaseDate,price,category,image));
                   }
                   //adding whole data into list
                   mList.add(new MovieDetails(title, artist, releaseDate, price, category, image));

               }
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (JSONException e) {
               e.printStackTrace();
           }
           return mList;


       }

       @Override
       protected void onPostExecute(List<MovieDetails> movieDetailses) {
           super.onPostExecute(movieDetailses);
           //set listAdapter
           ListViewAdapter listViewAdapter = new ListViewAdapter(getApplicationContext(),-1,movieDetailses);
           mListView.setAdapter(listViewAdapter);
           //set list Adapter based on checked checkbox
           if(mActionstatus||mComedystatus||mDramastatus||mKidsstatus||mSciStatus||mThrillerStatus){
               if(mActionstatus){
                   toolbar.setTitle("Action & Adventure");
               }
               else if(mComedystatus){
                   toolbar.setTitle("Comedy");
               }
               else  if(mDateStatus){
                   toolbar.setTitle("Drama");
               }
               else if(mKidsstatus){
                   toolbar.setTitle("Kids & Family");
               }
               else if(mSciStatus){
                   toolbar.setTitle("Sci-Fi & Fantasy");
               }
               else if(mThrillerStatus){
                   toolbar.setTitle("Thriller");
               }
               ListViewAdapter listViewCheckAdapter = new ListViewAdapter(getApplicationContext(),-1,mCheckedList);
               mListView.setAdapter(listViewCheckAdapter);

           }
           if(mActionstatus&&(mComedystatus)&&mDramastatus&&mKidsstatus&&mSciStatus&&mThrillerStatus){
               toolbar.setTitle("Action & Adventure"+","+"Comedy"+","+"Drama"+","+"Kids & Family"+","+"Sci-Fi & Fantasy"+","+"Thriller");
           }
           //populate sorted list based on title
           if(mTitleStatus){
               ListViewAdapter adaptersortedbytitle = new ListViewAdapter(getApplicationContext(),-1,movieDetailses);
               Collections.sort(movieDetailses, new ByTitleComparator());
               mListView.setAdapter(adaptersortedbytitle);
           }
           //populate sorted list based on Artist
           if(mArtistStatus){
               ListViewAdapter adaptersortedbyArtist = new ListViewAdapter(getApplicationContext(),-1,movieDetailses);
               Collections.sort(movieDetailses, new ByArtistComparator());
               mListView.setAdapter(adaptersortedbyArtist);
           }
           //populate sorted list based on date
           if(mDateStatus){
               ListViewAdapter adaptersortedbyDate = new ListViewAdapter(getApplicationContext(),-1,movieDetailses);
               Collections.sort(movieDetailses, new DateComparator());
               mListView.setAdapter( adaptersortedbyDate);
           }
       }

      public  String readStream(InputStream inputStream){

          StringBuffer stringBuffer = new StringBuffer();
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
           String line = null;
           try {
               while ((line = bufferedReader.readLine()) != null) {
                   stringBuffer.append(line);
               }
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               try {
                   inputStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           return stringBuffer.toString();

       }

   }


}

