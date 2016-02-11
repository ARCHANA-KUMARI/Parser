package com.robosoft.archanakumari.parser.Modal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by archanakumari on 9/2/16.
 */
public class JsonParser {

    private JSONObject mJsonRootObject;
    private JSONArray mJsonArray;
    MovieDetails movieDetails;

    private HashMap<String, ArrayList<MovieDetails>> hashMap = new HashMap<>();

    public HashMap<String, ArrayList<MovieDetails>> parseDataintoJson(String mJsonData) {


        try {
            mJsonRootObject = new JSONObject(mJsonData);
            //for getting root object
            JSONObject jsonFeed = mJsonRootObject.getJSONObject("feed");
            mJsonArray = jsonFeed.getJSONArray("entry");
            //  Log.i("Hello", "List size of entry" + mJsonArray.length());
            for (int i = 0; i < mJsonArray.length(); i++) {

                JSONObject jsonObject = mJsonArray.getJSONObject(i);
                //for getting title object
                JSONObject jsonTitleObject = jsonObject.getJSONObject("title");
                String title = jsonTitleObject.getString("label");
                //for getting im:artist object
                JSONObject jsonObjectImArtist = jsonObject.getJSONObject("im:artist");
                String artist = jsonObjectImArtist.getString("label");
                //for getting releaseDateObject
                JSONObject jsonObjectDate = jsonObject.getJSONObject("im:releaseDate");
                String releaseDate = jsonObjectDate.getString("label");
                //String manipulation for changing  date into dd/mm/yyyyy format
                String dd = releaseDate.substring(8, 10);
                String mm = releaseDate.substring(5, 7);
                String yyyy = releaseDate.substring(0, 4);
                releaseDate = dd + "/" + mm + "/" + yyyy;
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date date = format.parse(releaseDate);

                String price = null;
                if (jsonObject.has("im:price")) {
                    JSONObject jsonObjectPrice = jsonObject.getJSONObject("im:price");
                    price = jsonObjectPrice.getString("label");
                }

                //for category
                JSONObject jsonObjectCategory = jsonObject.getJSONObject("category");
                JSONObject jsonObjectAttribute = jsonObjectCategory.getJSONObject("attributes");
                String category = jsonObjectAttribute.getString("label");

                String image = null;
                //for getting image object
                JSONArray jsonArrayImage = jsonObject.getJSONArray("im:image");
                //for getting last image url
                for (int j = 0; j < jsonArrayImage.length(); j++) {
                    if (j == 2) {
                        JSONObject jsonObjectImageLabel = jsonArrayImage.getJSONObject(j);
                        String imageofLastIndex = jsonObjectImageLabel.getString("label");
                        image = imageofLastIndex;
                    }

                }
                movieDetails = new MovieDetails(title, artist, date, price, category, image);
                if (hashMap.containsKey(movieDetails.getmCategory())) {

                    ArrayList<MovieDetails> arrayList = hashMap.get(movieDetails.getmCategory());
                    arrayList.add(new MovieDetails(title, artist, date, price, category, image));
                    hashMap.put(movieDetails.getmCategory(), arrayList);


                } else {

                    ArrayList<MovieDetails> movieDetailsArrayList = new ArrayList<>();
                    movieDetailsArrayList.add(movieDetails);
                    hashMap.put(movieDetails.getmCategory(), movieDetailsArrayList);


                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}

