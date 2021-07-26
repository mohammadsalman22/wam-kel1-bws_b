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
    @SerializedName("nama_wisata")
    private String nama_wisata;
    @SerializedName("nama_travel_homestay")
    private String nama_travel_homestay;
    @SerializedName("jumlah_pesan")
    private int jumlah_pesan;
    @SerializedName("harga_wisata")
    private int harga_wisata;
    @SerializedName("harga_travel_homestay")
    private int harga_travel_homestay;

    public Booking (int kode_booking, String waktu_mulai, String waktu_akhir, int total_harga,
                    String status, int id_pembayaran, int id_user, String nama_wisata,
                    String nama_travel_homestay, int jumlah_pesan, int harga_wisata,
                    int harga_travel_homestay) {

        this.kode_booking = kode_booking;
        this.waktu_mulai = waktu_mulai;
        this.waktu_akhir = waktu_akhir;
        this.total_harga = total_harga;
        this.status = status;
        this.id_pembayaran = id_pembayaran;
        this.id_user = id_user;
        this.nama_wisata = nama_wisata;
        this.nama_travel_homestay = nama_travel_homestay;
        this.jumlah_pesan = jumlah_pesan;
        this.harga_wisata = harga_wisata;
        this.harga_travel_homestay = harga_travel_homestay;
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

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getNama_travel_homestay() {
        return nama_travel_homestay;
    }

    public void setNama_travel_homestay(String nama_travel_homestay) {
        this.nama_travel_homestay = nama_travel_homestay;
    }

    public int getJumlah_pesan() {
        return jumlah_pesan;
    }

    public void setJumlah_pesan(int jumlah_pesan) {
        this.jumlah_pesan = jumlah_pesan;
    }

    public int getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(int harga_wisata) {
        this.harga_wisata = harga_wisata;
    }

    public int getHarga_travel_homestay() {
        return harga_travel_homestay;
    }

    public void setHarga_travel_homestay(int harga_travel_homestay) {
        this.harga_travel_homestay = harga_travel_homestay;
    }
}