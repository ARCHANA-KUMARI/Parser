package com.robosoft.archanakumari.parser.Modal;

/**
 * Created by archanakumari on 5/2/16.
 */
public class CountCategory {
    public CountCategory(int mCountAction, int mCountComedy, int mCountDrama, int mCountKids, int mCountSci, int mCountThriller) {
        this.mCountAction = mCountAction;
        this.mCountComedy = mCountComedy;
        this.mCountDrama = mCountDrama;
        this.mCountKids = mCountKids;
        this.mCountSci = mCountSci;
        this.mCountThriller = mCountThriller;
    }

    public int getmCountThriller() {
        return mCountThriller;
    }

    public void setmCountThriller(int mCountThriller) {
        this.mCountThriller = mCountThriller;
    }

    public int getmCountAction() {
        return mCountAction;
    }

    public void setmCountAction(int mCountAction) {
        this.mCountAction = mCountAction;
    }

    public int getmCountComedy() {
        return mCountComedy;
    }

    public void setmCountComedy(int mCountComedy) {
        this.mCountComedy = mCountComedy;
    }

    public int getmCountDrama() {
        return mCountDrama;
    }

    public void setmCountDrama(int mCountDrama) {
        this.mCountDrama = mCountDrama;
    }

    public int getmCountKids() {
        return mCountKids;
    }

    public void setmCountKids(int mCountKids) {
        this.mCountKids = mCountKids;
    }

    public int getmCountSci() {
        return mCountSci;
    }

    public void setmCountSci(int mCountSci) {
        this.mCountSci = mCountSci;
    }

    private int mCountAction,mCountComedy,mCountDrama,mCountKids,mCountSci,mCountThriller;

}
