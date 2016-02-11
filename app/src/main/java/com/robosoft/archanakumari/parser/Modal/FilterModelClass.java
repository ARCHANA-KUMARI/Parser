package com.robosoft.archanakumari.parser.Modal;

/**
 * Created by archanakumari on 5/2/16.
 */
public class FilterModelClass {

    private String mCategoryname;
    private int mCount;
    boolean mSelectedValue;

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }


    public String getmCategoryname() {
        return mCategoryname;
    }

    public void setmCategoryname(String mCategoryname) {
        this.mCategoryname = mCategoryname;
    }

    public boolean ismSelectedValue() {
        return mSelectedValue;
    }

    public void setmSelectedValue(boolean mSelectedValue) {
        this.mSelectedValue = mSelectedValue;
    }
}
