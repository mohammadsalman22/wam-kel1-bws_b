package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMetode {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Metode> mData;

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

    public List<Metode> getmData() {
        return mData;
    }

    public void setmData(List<Metode> mData) {
        this.mData = mData;
    }

}