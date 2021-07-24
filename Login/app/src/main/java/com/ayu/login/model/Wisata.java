package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class Wisata {

    @SerializedName("id_wisata")
    private int id_wisata;
    @SerializedName("nama_wisata")
    private String nama_wisata;
    @SerializedName("alamat_wisata")
    private String alamat_wisata;
    @SerializedName("gambar_wisata")
    private String gambar_wisata;
    @SerializedName("deskripsi_wisata")
    private String deskripsi_wisata;
    @SerializedName("harga_wisata")
    private int harga_wisata;
    @SerializedName("tag")
    private String tag;
    @SerializedName("slug")
    private String slug;

    public Wisata(int id_wisata, String nama_wisata, String alamat_wisata, String gambar_wisata,
                  String deskripsi_wisata, int harga_wisata, String tag, String slug) {

        this.id_wisata = id_wisata;
        this.nama_wisata = nama_wisata;
        this.alamat_wisata = alamat_wisata;
        this.gambar_wisata = gambar_wisata;
        this.deskripsi_wisata = deskripsi_wisata;
        this.harga_wisata = harga_wisata;
        this.tag = tag;
        this.slug = slug;

    }

    public int getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(int id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getAlamat_wisata() {
        return alamat_wisata;
    }

    public void setAlamat_wisata(String alamat_wisata) {
        this.alamat_wisata = alamat_wisata;
    }

    public String getGambar_wisata() {
        return gambar_wisata;
    }

    public void setGambar_wisata(String gambar_wisata) {
        this.gambar_wisata = gambar_wisata;
    }

    public String getDeskripsi_wisata() {
        return deskripsi_wisata;
    }

    public void setDeskripsi_wisata(String deskripsi_wisata) {
        this.deskripsi_wisata = deskripsi_wisata;
    }

    public int getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(int harga_wisata) {
        this.harga_wisata = harga_wisata;
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