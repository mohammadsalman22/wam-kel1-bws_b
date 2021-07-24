package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class DetailBooking {

    @SerializedName("kode_booking")
    public int kode_booking;
    @SerializedName("id_wisata")
    public int id_wisata;
    @SerializedName("id_travelhomestay")
    public int id_travelhomestay;
    @SerializedName("jumlah_pesan")
    public int jumlah_pesan;

    public DetailBooking (int kode_booking, int id_wisata, int id_travelhomestay, int jumlah_pesan) {

        this.kode_booking = kode_booking;
        this.id_wisata = id_wisata;
        this.id_travelhomestay = id_travelhomestay;
        this.jumlah_pesan = jumlah_pesan;

    }

    public int getKode_booking() {
        return kode_booking;
    }

    public void setKode_booking(int kode_booking) {
        this.kode_booking = kode_booking;
    }

    public int getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(int id_wisata) {
        this.id_wisata = id_wisata;
    }

    public int getId_travelhomestay() {
        return id_travelhomestay;
    }

    public void setId_travelhomestay(int id_travelhomestay) {
        this.id_travelhomestay = id_travelhomestay;
    }

    public int getJumlah_pesan() {
        return jumlah_pesan;
    }

    public void setJumlah_pesan(int jumlah_pesan) {
        this.jumlah_pesan = jumlah_pesan;
    }
}
