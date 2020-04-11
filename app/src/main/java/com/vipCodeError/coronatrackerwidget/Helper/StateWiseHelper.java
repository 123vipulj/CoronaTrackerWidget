package com.vipCodeError.coronatrackerwidget.Helper;

import android.os.Parcel;
import android.os.Parcelable;

public class StateWiseHelper{

    String mActive, mConfirmed, mDeaths, mRecovered;
    String mDeltaActive, mDeltaConfirmed, mDeltaDeaths, mDeltaRecovered, state;

    public StateWiseHelper(String mActive, String mConfirmed, String mDeaths, String mRecovered,
                           String mDeltaConfirmed, String mDeltaDeaths,
                           String mDeltaRecovered,String state) {
        this.mActive = mActive;
        this.mConfirmed = mConfirmed;
        this.mDeaths = mDeaths;
        this.mRecovered = mRecovered;

        this.mDeltaConfirmed = mDeltaConfirmed;
        this.mDeltaDeaths = mDeltaDeaths;
        this.mDeltaRecovered = mDeltaRecovered;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }

    public String getmConfirmed() {
        return mConfirmed;
    }

    public void setmConfirmed(String mConfirmed) {
        this.mConfirmed = mConfirmed;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public void setmDeaths(String mDeaths) {
        this.mDeaths = mDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public String getmDeltaActive() {
        return mDeltaActive;
    }

    public void setmDeltaActive(String mDeltaActive) {
        this.mDeltaActive = mDeltaActive;
    }

    public String getmDeltaConfirmed() {
        return mDeltaConfirmed;
    }

    public void setmDeltaConfirmed(String mDeltaConfirmed) {
        this.mDeltaConfirmed = mDeltaConfirmed;
    }

    public String getmDeltaDeaths() {
        return mDeltaDeaths;
    }

    public void setmDeltaDeaths(String mDeltaDeaths) {
        this.mDeltaDeaths = mDeltaDeaths;
    }

    public String getmDeltaRecovered() {
        return mDeltaRecovered;
    }

    public void setmDeltaRecovered(String mDeltaRecovered) {
        this.mDeltaRecovered = mDeltaRecovered;
    }


}
