package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBooking {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Booking> mData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booking> getmData() {
        return mData;
    }

    public void setmData(List<Booking> mData) {
        this.mData = mData;
    }
}
