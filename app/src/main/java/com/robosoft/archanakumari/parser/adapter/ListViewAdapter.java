package com.robosoft.archanakumari.parser.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robosoft.archanakumari.parser.Modal.MovieDetails;
import com.robosoft.archanakumari.parser.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by archanakumari on 3/2/16.
 */
public class ListViewAdapter extends ArrayAdapter<MovieDetails> {

    private TextView mTextTitle, mTextArtist, mTextDate, mTextPrice, mTextCategory;
    private ImageView mImage;
    private View mOneRow;
    private Context mContext;
    private List<MovieDetails> mMoviesList;

    public ListViewAdapter(Context context, int resource, List<MovieDetails> mMoviesList) {
        super(context, resource, mMoviesList);
        this.mContext = context;
        this.mMoviesList = mMoviesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RelativeLayout mOneRow = (RelativeLayout) convertView;

        if (null == mOneRow) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mOneRow = (RelativeLayout) inflater.inflate(R.layout.child, null);
        }

        mTextTitle = (TextView) mOneRow.findViewById(R.id.title);
        mTextArtist = (TextView) mOneRow.findViewById(R.id.artist);
        mTextDate = (TextView) mOneRow.findViewById(R.id.date);
        mTextPrice = (TextView) mOneRow.findViewById(R.id.price);
        mTextCategory = (TextView) mOneRow.findViewById(R.id.category);
        mImage = (ImageView) mOneRow.findViewById(R.id.image);

        MovieDetails movieDetails = mMoviesList.get(position);

        mTextTitle.setText(movieDetails.getmTilte());
        mTextArtist.setText(movieDetails.getmArtist());
        mTextDate.setText(movieDetails.getmReleasedData());
        mTextPrice.setText(movieDetails.getmPrice());
        mTextCategory.setText(movieDetails.getmCategory());

        ImageDownloader imageDownloader = new ImageDownloader(movieDetails.getmImage(), mImage);
        imageDownloader.execute();
        return mOneRow;

    }

    class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {

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

}
