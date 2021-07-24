package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class Metode {

    @SerializedName("id_pembayaran")
    private int id_pembayaran;
    @SerializedName("nama")
    private String nama;
    @SerializedName("no_tujuan")
    private String no_tujuan;

    public int getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(int id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_tujuan() {
        return no_tujuan;
    }

    public void setNo_tujuan(String no_tujuan) {
        this.no_tujuan = no_tujuan;
    }
}
