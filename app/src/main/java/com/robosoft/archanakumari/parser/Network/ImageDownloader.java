package com.robosoft.archanakumari.parser.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by archanakumari on 10/2/16.
 */
public class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {

    private static int cacheSize  = 1024 * 1024 * 10;
    private static LruCache<String, Bitmap> mMemoryCache = new LruCache<String,Bitmap>(cacheSize);
    private String image;
    private Bitmap mPic = null;
    private ImageView mImage;

    public ImageDownloader(String image, ImageView mImage) {
        this.image = image;
        this.mImage = mImage;

    }
    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL url = new URL(image);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            mPic = BitmapFactory.decodeStream(inputStream);
            mMemoryCache.put(image,mPic);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mPic;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImage.setImageBitmap(bitmap);

    }

}