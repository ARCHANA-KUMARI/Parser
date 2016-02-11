package com.robosoft.archanakumari.parser.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.robosoft.archanakumari.parser.Modal.JsonParser;
import com.robosoft.archanakumari.parser.Modal.MovieDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by archanakumari on 9/2/16.
 */
public class Downloader extends AsyncTask<Void, Void, HashMap<String, ArrayList<MovieDetails>>> {


    private String mJsonData;
    private JSONObject mJsonRootObject;
    private JSONArray mJsonArray;
    private String mUrl;
    private ListView mListView;
    private Context mContext;

    HashMap<String, ArrayList<MovieDetails>> hashMap;

    public Downloader(String mUrl, ListView mListView) {
        this.mUrl = mUrl;
        this.mListView = mListView;

    }

    @Override
    protected HashMap<String, ArrayList<MovieDetails>> doInBackground(Void... params) {

        try {

            URL url = new URL(mUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            mJsonData = readStream(inputStream);
            JsonParser jsonParser = new JsonParser();
            hashMap = jsonParser.parseDataintoJson(mJsonData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;


    }

    public String readStream(InputStream inputStream) {

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

    @Override
    protected void onPostExecute(HashMap<String, ArrayList<MovieDetails>> s) {
        super.onPostExecute(s);


    }
}
