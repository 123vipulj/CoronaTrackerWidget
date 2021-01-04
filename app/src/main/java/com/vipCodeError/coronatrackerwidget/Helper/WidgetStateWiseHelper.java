package com.vipCodeError.coronatrackerwidget.Helper;

public class WidgetStateWiseHelper {
    private String  mConfirm, mActive, mRecovered, mDeath, mState;
    private String  mConfirmDelta, mRecoveredDelta, mDeathDelta;

    public WidgetStateWiseHelper(String mConfirm, String mActive, String mRecovered, String mDeath, String mState, String mConfirmDelta, String mRecoveredDelta, String mDeathDelta) {
        this.mConfirm = mConfirm;
        this.mActive = mActive;
        this.mRecovered = mRecovered;
        this.mDeath = mDeath;
        this.mState = mState;
        this.mConfirmDelta = mConfirmDelta;
        this.mRecoveredDelta = mRecoveredDelta;
        this.mDeathDelta = mDeathDelta;
    }

    public String getmConfirm() {
        return mConfirm;
    }

    public void setmConfirm(String mConfirm) {
        this.mConfirm = mConfirm;
    }

    public String getmActive() {
        return mActive;
    }

    public void setmActive(String mActive) {
        this.mActive = mActive;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public String getmDeath() {
        return mDeath;
    }

    public void setmDeath(String mDeath) {
        this.mDeath = mDeath;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmConfirmDelta() {
        return "↑"+mConfirmDelta;
    }

    public void setmConfirmDelta(String mConfirmDelta) {
        this.mConfirmDelta = mConfirmDelta;
    }

    public String getmRecoveredDelta() {
        return "↑"+mRecoveredDelta;
    }

    public void setmRecoveredDelta(String mRecoveredDelta) {
        this.mRecoveredDelta = mRecoveredDelta;
    }

    public String getmDeathDelta() {
        return "↑"+mDeathDelta;
    }

    public void setmDeathDelta(String mDeathDelta) {
        this.mDeathDelta = mDeathDelta;
    }
}
