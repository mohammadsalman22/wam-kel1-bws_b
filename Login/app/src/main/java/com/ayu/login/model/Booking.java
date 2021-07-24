package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("kode_booking")
    private int kode_booking;
    @SerializedName("waktu_mulai")
    private String waktu_mulai;
    @SerializedName("waktu_akhir")
    private String waktu_akhir;
    @SerializedName("total_harga")
    private int total_harga;
    @SerializedName("status")
    private String status;
    @SerializedName("id_pembayaran")
    private int id_pembayaran;
    @SerializedName("id_user")
    private int id_user;

    public Booking (int kode_booking, String waktu_mulai, String waktu_akhir, int total_harga,
                    String status, int id_pembayaran, int id_user) {

        this.kode_booking = kode_booking;
        this.waktu_mulai = waktu_mulai;
        this.waktu_akhir = waktu_akhir;
        this.total_harga = total_harga;
        this.status = status;
        this.id_pembayaran = id_pembayaran;
        this.id_user = id_user;

    }

    public int getKode_booking() {
        return kode_booking;
    }

    public void setKode_booking(int kode_booking) {
        this.kode_booking = kode_booking;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public String getWaktu_akhir() {
        return waktu_akhir;
    }

    public void setWaktu_akhir(String waktu_akhir) {
        this.waktu_akhir = waktu_akhir;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(int id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

}