package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login {

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private User mData;

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

    public User getmData() {
        return mData;
    }

    public void setmData(User mData) {
        this.mData = mData;
    }
}
