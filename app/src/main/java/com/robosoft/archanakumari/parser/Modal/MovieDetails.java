package com.robosoft.archanakumari.parser.Modal;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by archanakumari on 3/2/16.
 */
public class MovieDetails implements Serializable {

    private String mTilte;
    private String mArtist;
    private Date mReleasedData;
    private String mPrice;
    private String mCategory;
    private String mImage;

    public MovieDetails(String mTilte, String mArtist, Date mReleasedData, String mPrice, String mCategory, String mImage) {
        this.mTilte = mTilte;
        this.mArtist = mArtist;
        this.mReleasedData = mReleasedData;
        this.mPrice = mPrice;
        this.mCategory = mCategory;
        this.mImage = mImage;
    }

    public String getmTilte() {
        return mTilte;
    }

    public void setmTilte(String mTilte) {
        this.mTilte = mTilte;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public Date getmReleasedData() {
        return mReleasedData;
    }

    public void setmReleasedData(Date mReleasedData) {
        this.mReleasedData = mReleasedData;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
