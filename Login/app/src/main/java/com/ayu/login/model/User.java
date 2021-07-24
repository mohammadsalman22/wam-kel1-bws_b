package com.ayu.login.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_user")
    public int id_user;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("nama")
    public String nama;
    @SerializedName("jk")
    public String jk;
    @SerializedName("alamat")
    public String alamat;
    @SerializedName("no_hp")
    public String no_hp;
    @SerializedName("email")
    public String email;

    public User (int id_user, String username, String password, String nama, String jk,
                 String alamat, String no_hp, String email) {

        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.jk = jk;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}