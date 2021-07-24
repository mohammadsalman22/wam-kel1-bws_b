package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class TravelHomestay {

    @SerializedName("id_travel_homestay")
    private int id_travel_homestay;
    @SerializedName("nama_travel_homestay")
    private String nama_travel_homestay;
    @SerializedName("alamat_travel_homestay")
    private String alamat_travel_homestay;
    @SerializedName("gambar_travel_homestay")
    private String gambar_travel_homestay;
    @SerializedName("deskripsi_travel_homestay")
    private String deskripsi_travel_homestay;
    @SerializedName("harga_travel_homestay")
    private int harga_travel_homestay;
    @SerializedName("tag")
    private String tag;
    @SerializedName("slug")
    private String slug;

    public TravelHomestay(int id_travel_homestay, String nama_travel_homestay, String alamat_travel_homestay,
                          String gambar_travel_homestay, String deskripsi_travel_homestay, int harga_travel_homestay, String tag, String slug) {

        this.id_travel_homestay = id_travel_homestay;
        this.nama_travel_homestay = nama_travel_homestay;
        this.alamat_travel_homestay = alamat_travel_homestay;
        this.gambar_travel_homestay = gambar_travel_homestay;
        this.deskripsi_travel_homestay = deskripsi_travel_homestay;
        this.harga_travel_homestay = harga_travel_homestay;
        this.tag = tag;
        this.slug = slug;

    }

    public int getId_travel_homestay() {
        return id_travel_homestay;
    }

    public void setId_travel_homestay(int id_travel_homestay) {
        this.id_travel_homestay = id_travel_homestay;
    }

    public String getNama_travel_homestay() {
        return nama_travel_homestay;
    }

    public void setNama_travel_homestay(String nama_travel_homestay) {
        this.nama_travel_homestay = nama_travel_homestay;
    }

    public String getAlamat_travel_homestay() {
        return alamat_travel_homestay;
    }

    public void setAlamat_travel_homestay(String alamat_travel_homestay) {
        this.alamat_travel_homestay = alamat_travel_homestay;
    }

    public String getGambar_travel_homestay() {
        return gambar_travel_homestay;
    }

    public void setGambar_travel_homestay(String gambar_travel_homestay) {
        this.gambar_travel_homestay = gambar_travel_homestay;
    }

    public String getDeskripsi_travel_homestay() {
        return deskripsi_travel_homestay;
    }

    public void setDeskripsi_travel_homestay(String deskripsi_travel_homestay) {
        this.deskripsi_travel_homestay = deskripsi_travel_homestay;
    }

    public int getHarga_travel_homestay() {
        return harga_travel_homestay;
    }

    public void setHarga_travel_homestay(int harga_travel_homestay) {
        this.harga_travel_homestay = harga_travel_homestay;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}