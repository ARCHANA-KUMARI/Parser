package com.robosoft.archanakumari.parser.Modal;

/**
 * Created by archanakumari on 4/2/16.
 */
public class StatusClass {
    private boolean mChecked;
    private boolean mNotChecked;

    public StatusClass(boolean mChecked) {
        this.mChecked = mChecked;

    }

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }

    public boolean ismNotChecked() {
        return mNotChecked;
    }

    public void setmNotChecked(boolean mNotChecked) {
        this.mNotChecked = mNotChecked;
    }
}
