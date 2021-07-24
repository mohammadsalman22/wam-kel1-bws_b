package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWisata {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Wisata> mData;

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

    public List<Wisata> getmData() {
        return mData;
    }

    public void setmData(List<Wisata> mData) {
        this.mData = mData;
    }
}
